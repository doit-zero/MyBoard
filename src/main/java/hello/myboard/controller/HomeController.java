package hello.myboard.controller;

import hello.myboard.entity.Board;
import hello.myboard.entity.Member;
import hello.myboard.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardRepository boardRepository;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/boards")
    public String boards(Model model){
        Member member = new Member("재준");
        String content = "test!";
        Board board = new Board(content,member);
        model.addAttribute("board", board);
        return "board";
    }
}
