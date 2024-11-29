package hello.myboard.controller;

import hello.myboard.dto.BoardDto;
import hello.myboard.entity.Board;
import hello.myboard.repository.BoardRepository;
import hello.myboard.service.BoardService;
import hello.myboard.service.MemberService;
import hello.myboard.session.MemberSession;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static hello.myboard.service.MemberService.SESSION_ID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model){
        if(httpSession.getAttribute(SESSION_ID) != null){
            MemberSession memberSession =(MemberSession) httpSession.getAttribute(SESSION_ID);
            model.addAttribute("memberName",memberSession.getName());
        }
        List<BoardDto> boardDtoList = boardService.getInitBoardList();
        log.info("boardDtoList : {}",boardDtoList);
        model.addAttribute("boardDtoList",boardDtoList);
        return "home";
    }

}
