package hello.myboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Getter
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false) // 외래 키 명시
    private Member member;

    public Board() {
    }

    public Board(String content, Member member) {
        this.content = content;
        this.member = member;
    }
}
