package aiss.videominer.controller;

import aiss.videominer.model.Channel;
import aiss.videominer.model.User;
import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.VideoRepository;
import aiss.videominer.model.Video;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/v1")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    ChannelRepository channelRepository;

    @GetMapping("/videos")
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @GetMapping("/videos/{id}")
    public Video findById(@PathVariable String id) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        return video.get();
    }

    // Obtener todos los videos de un canal
    @GetMapping("/channels/{channelId}/videos")
    public List<Video> findByChannel(@PathVariable String channelId) {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found");
        return channel.get().getVideos();
    }

    @PostMapping("/channels/{channelId}/videos")
    @ResponseStatus(HttpStatus.CREATED)
    public Video create(@PathVariable String channelId, @RequestBody @Valid Video video) {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found");
        channel.get().getVideos().add(video);
        channelRepository.save(channel.get());
        return video;
    }

    @PutMapping("/videos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody @Valid Video updatedVideo) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        Video existing = video.get();
        existing.setName(updatedVideo.getName());
        existing.setDescription(updatedVideo.getDescription());
        existing.setReleaseTime(updatedVideo.getReleaseTime());
        videoRepository.save(existing);
    }

    @DeleteMapping("/videos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        if (!videoRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        videoRepository.deleteById(id);
    }
    // Obtener el usuario de un video concreto
    @GetMapping("/videos/{id}/user")
    public User findUserByVideo(@PathVariable String id) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        return video.get().getAuthor();
    }

    // Actualizar el usuario de un video
    @PutMapping("/videos/{id}/user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String id, @RequestBody @Valid User updatedUser) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        video.get().setAuthor(updatedUser);
        videoRepository.save(video.get());
    }
}
