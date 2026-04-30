package aiss.videominer.controller;

import aiss.videominer.repository.VideoRepository;
import aiss.videominer.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/v1")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;

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
}
