package aiss.videominer.controller;

import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CommentRepository;
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
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    VideoRepository videoRepository;

    @GetMapping("/comments")
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @GetMapping("/comments/{id}")
    public Comment findById(@PathVariable String id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        return comment.get();
    }

    // Comentarios de un video concreto
    @GetMapping("/videos/{videoId}/comments")
    public List<Comment> findByVideo(@PathVariable String videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        if (video.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        return video.get().getComments();
    }

    @PostMapping("/videos/{videoId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@PathVariable String videoId, @RequestBody @Valid Comment comment) {
        Optional<Video> video = videoRepository.findById(videoId);
        if (video.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        video.get().getComments().add(comment);
        videoRepository.save(video.get());
        return comment;
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        if (!commentRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        commentRepository.deleteById(id);
    }
}
