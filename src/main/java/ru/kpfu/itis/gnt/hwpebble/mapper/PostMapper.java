package ru.kpfu.itis.gnt.hwpebble.mapper;

import org.springframework.stereotype.Component;
import ru.kpfu.itis.gnt.hwpebble.entity.PostEntity;
import ru.kpfu.itis.gnt.hwpebble.model.PostDto;

import java.util.Date;

@Component
public class PostMapper implements BaseMapper<PostEntity, PostDto> {
    @Override
    public PostDto mapTo(PostEntity model) {
        return PostDto.builder()
                .id(model.getId())
                .content(model.getContent())
                .title(model.getTitle())
                .publicationDate(model.getPublicationDate())
                .build();
    }

    @Override
    public PostEntity mapFrom(PostDto model) {
        return PostEntity.builder()
                .id(model.getId())
                .content(model.getContent())
                .title(model.getTitle())
                .publicationDate(model.getPublicationDate())
                .build();
    }
}
