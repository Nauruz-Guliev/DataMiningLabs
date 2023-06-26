package ru.kpfu.itis.gnt.hwpebble.model;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private Long publicationDate;

}
