package hello.myboard.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    // cascade = CascadeType.ALL는 영속성 전이임 member.addBoard(borad) 시에 member와 board 모두 영속성컨텍스트에 등록되며 sql문이나감
    //orphanRemoval = true 설정은 연관된 엔티티가 고아 상태(즉, 부모 엔티티와의 관계가 끊어진 상태)가 되면 자동으로 삭제 쿼리를 발생시키는 설정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boardList;

    public Member(String name) {
        this.name = name;
    }

    // 연관관계 편의 메서드
    public void addBoard(Board board) {
        boardList.add(board);
        board.setMember(this);
    }
}
