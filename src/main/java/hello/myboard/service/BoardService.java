package hello.myboard.service;

import hello.myboard.dto.BoardDto;
import hello.myboard.dto.borad.BoardWriteDto;
import hello.myboard.entity.Board;
import hello.myboard.entity.Member;
import hello.myboard.repository.BoardRepository;
import hello.myboard.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    public final BoardRepository boardRepository;
    private final HttpSession httpSession;
    private final MemberRepository memberRepository;

    public List<BoardDto> getInitBoardList(){
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .title(board.getTitle())
                    .createdAt(board.getCreatedAt())
                    .likes(board.getLikes())
                    .views(board.getViews())
                    .member(board.getMember())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    public Boolean write(BoardWriteDto boardWriteDto) {
        //httpSession.getAttribute()
        Optional<Member> findMember = memberRepository.findById(102L);
        Board board = new Board(boardWriteDto.getTitle(), boardWriteDto.getContent(),LocalDateTime.now(),findMember.get());
        boardRepository.save(board);
        return true;
    }

    public Page<Board> getContents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Board> contents = boardRepository.findAll(pageable);
        return contents;
    }
}
