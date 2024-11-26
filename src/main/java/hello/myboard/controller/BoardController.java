package hello.myboard.controller;

import hello.myboard.dto.borad.BoardWriteDto;
import hello.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/write")
    public String getWriteForm(Model model) {
        model.addAttribute("boardWriteDto",new BoardWriteDto());
        return "board/write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute("boardWriteDto") BoardWriteDto boardWriteDto) {
        log.info("boardWriteDto: " + boardWriteDto);
        boardService.write(boardWriteDto);
        return "redirect:/";
    }

}
