package aiss.videominer.controller;

import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/v1/channels")
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;

    @GetMapping
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Channel findById(@PathVariable String id) {
        Optional<Channel> channel = channelRepository.findById(id);
        if (channel.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found");
        return channel.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Channel create(@RequestBody @Valid Channel channel) {
        channelRepository.save(channel);
        return channel;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody @Valid Channel updatedChannel) {
        Optional<Channel> channel = channelRepository.findById(id);
        if (channel.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found");
        Channel existing = channel.get();
        existing.setName(updatedChannel.getName());
        existing.setDescription(updatedChannel.getDescription());
        existing.setCreatedTime(updatedChannel.getCreatedTime());
        channelRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        if (!channelRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found");
        channelRepository.deleteById(id);
    }
}
