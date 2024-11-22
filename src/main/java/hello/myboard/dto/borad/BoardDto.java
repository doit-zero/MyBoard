package hello.myboard.dto;

import hello.myboard.entity.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardDto {
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private int views;

    private int likes;

    private Member member;
}
