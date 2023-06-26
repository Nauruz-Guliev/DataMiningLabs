package ru.kpfu.itis.gnt.hwpebble.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gnt.hwpebble.constants.ErrorMessageConstants;
import ru.kpfu.itis.gnt.hwpebble.exception.DatabaseException;
import ru.kpfu.itis.gnt.hwpebble.exception.ResourceNotFoundException;
import ru.kpfu.itis.gnt.hwpebble.mapper.PostMapper;
import ru.kpfu.itis.gnt.hwpebble.model.PostDto;
import ru.kpfu.itis.gnt.hwpebble.repository.PostRepository;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
    private final PostMapper mapper;

    public List<PostDto> getAllPosts() {
        try {
            return repository.findAll().stream().map(mapper::mapTo).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new DatabaseException(ErrorMessageConstants.GET_LIST_ERROR);
        }
    }

    public PostDto createPost(PostDto post) {
        post.setPublicationDate(Calendar.getInstance().getTimeInMillis());
        System.out.println(post.getPublicationDate());

        try {
            return mapper.mapTo(repository.save(mapper.mapFrom(post)));
        } catch (Exception ex) {
            throw new DatabaseException(ErrorMessageConstants.CREATION_ERROR);

        }
    }

    public PostDto updatePost(PostDto post) {
        try {
            return createPost(post);
        } catch (Exception ex) {
            throw new DatabaseException(ErrorMessageConstants.UPDATE_ERROR);
        }
    }

    public void deletePost(PostDto post) {
        try {
            repository.delete(mapper.mapFrom(post));
        } catch (Exception ex) {
            throw new DatabaseException(ErrorMessageConstants.DELETION_ERROR);
        }
    }

    public PostDto getPostById(Long postId) throws ResourceNotFoundException {
        return mapper.mapTo(repository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(ErrorMessageConstants.getPostNotFoundMessage(String.valueOf(postId)))
        ));
    }

    public Page<PostDto> findAll(Pageable pageable) {
        try {
            return repository.findAll(pageable).map(mapper::mapTo);
        } catch (Exception ex) {
            throw new DatabaseException(ErrorMessageConstants.GET_LIST_ERROR);
        }
    }

}
