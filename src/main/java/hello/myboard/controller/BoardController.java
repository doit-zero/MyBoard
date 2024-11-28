package hello.myboard.controller;

import hello.myboard.dto.BoardDto;
import hello.myboard.dto.borad.BoardWriteDto;
import hello.myboard.entity.Board;
import hello.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/contents")
    public String getContents(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "50") int size,Model model) {
        Page<Board> contentPage = boardService.getContents(page, size);
        model.addAttribute("contentPage",contentPage);
        model.addAttribute("contentPage.getContent",contentPage.getContent());
        model.addAttribute("contentPage.getTotalPAges",contentPage.getTotalPages());
        model.addAttribute("contentPage.getTotalEle",contentPage.getTotalElements());
        model.addAttribute("contentPage.getNumber",contentPage.getNumber());



        return "board/board";
    }

}
