package aiss.videominer.controller;

import aiss.videominer.model.Caption;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/v1")
public class CaptionController {

    @Autowired
    CaptionRepository captionRepository;

    @Autowired
    VideoRepository videoRepository;

    @GetMapping("/captions")
    public List<Caption> findAll() {
        return captionRepository.findAll();
    }

    @GetMapping("/captions/{id}")
    public Caption findById(@PathVariable String id) {
        Optional<Caption> caption = captionRepository.findById(id);
        if (caption.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Caption not found");
        return caption.get();
    }

    // Captions de un video concreto
    @GetMapping("/videos/{videoId}/captions")
    public List<Caption> findByVideo(@PathVariable String videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        if (video.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        return video.get().getCaptions();
    }

    @PostMapping("/videos/{videoId}/captions")
    @ResponseStatus(HttpStatus.CREATED)
    public Caption create(@PathVariable String videoId, @RequestBody @Valid Caption caption) {
        Optional<Video> video = videoRepository.findById(videoId);
        if (video.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        video.get().getCaptions().add(caption);
        videoRepository.save(video.get());
        return caption;
    }

    @DeleteMapping("/captions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        if (!captionRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Caption not found");
        captionRepository.deleteById(id);
    }
}
