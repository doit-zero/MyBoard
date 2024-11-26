package hello.myboard.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Entity
@Getter
// 외부에서 직접 객체 생성을 못하도록 객체생성 접근 제어를 함,
// JPA는 스펙상 기본 생성자가 필요한데, 같은 패키지가 아니지만 JPA는 리플렉션 기술을 사용하여 엔터티를 기본 생성자로 생성하여 사용할 수 있음
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "BIGINT DEFAULT 0")
    private int views;

    @Column(columnDefinition = "BIGINT DEFAULT 0")
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false) // 외래 키 명시
    private Member member;

    public Board(String content, Member member) {
        this.content = content;
        this.member = member;
    }

    public Board(String title, String content,LocalDateTime createdAt, Member member) {
       this.title = title;
       this.content = content;
       this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
