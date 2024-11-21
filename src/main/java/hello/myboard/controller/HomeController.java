package hello.myboard.controller;

import hello.myboard.dto.BoardDto;
import hello.myboard.entity.Board;
import hello.myboard.repository.BoardRepository;
import hello.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @GetMapping("/")
    public String home(Model model){
        List<BoardDto> boardDtoList = boardService.getInitBoardList();
        model.addAttribute("boardDtoList",boardDtoList);
        return "home";
    }

    @GetMapping("/boards")
    public String boards(Model model){

        return "board";
    }
}
