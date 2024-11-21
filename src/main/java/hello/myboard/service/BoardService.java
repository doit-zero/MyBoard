package hello.myboard.service;

import hello.myboard.dto.BoardDto;
import hello.myboard.entity.Board;
import hello.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    public final BoardRepository boardRepository;

    public List<BoardDto> getInitBoardList(){
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
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

}
