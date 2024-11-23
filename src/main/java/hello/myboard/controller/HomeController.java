package hello.myboard.controller;

import hello.myboard.dto.BoardDto;
import hello.myboard.entity.Board;
import hello.myboard.repository.BoardRepository;
import hello.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @GetMapping("/")
    public String home(@RequestParam(required = false) String memberName, Model model){
        // 쿼리 파라미터로 전달된 memberName을 model에 추가
        model.addAttribute("memberName", memberName);

        List<BoardDto> boardDtoList = boardService.getInitBoardList();

        model.addAttribute("boardDtoList",boardDtoList);
        return "home";
    }
    @GetMapping("/boards")
    public String boards(Model model){

        return "board";
    }
}
