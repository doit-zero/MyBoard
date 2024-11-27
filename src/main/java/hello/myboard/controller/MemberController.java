package hello.myboard.controller;

import hello.myboard.dto.BoardDto;
import hello.myboard.dto.SignupDto;
import hello.myboard.dto.member.LoginDto;
import hello.myboard.entity.Board;
import hello.myboard.entity.Member;
import hello.myboard.repository.MemberRepository;
import hello.myboard.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/members")
    public String getSignupForm(Model model) {
        model.addAttribute("signupDto", new SignupDto());
        return "members/signup";
    }

    @PostMapping("/members")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "members/signup";
        }
        memberService.signup(signupDto);
        return "home";
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "members/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "members/login";
        }

        memberService.login(loginDto);
        redirectAttributes.addAttribute("memberName", loginDto.getName());
        return "redirect:/";
    }

    @GetMapping("/logout/{memberName}")
    public String logout(@PathVariable("memberName") String memberName) {
        System.out.println(memberName);
        memberService.logout(memberName);
        return "redirect:/";
    }

    // 멤버 조회
    @GetMapping("/members/{name}")
    public void findMember(@PathVariable Long id, Model model) {
        Optional<Member> findMember = memberRepository.findById(id);
        if (findMember.isPresent()) {
            model.addAttribute("member", findMember.get());
        }
    }

    // 멤버가 쓴 글 가져오기
    @GetMapping("/members/boarList")
    //public String getMyBoarList(@RequestParam String memberName,Model model) {
    public String getMyBoarList(Model model) {
        //test용
        String memberName = "test4";

        List<BoardDto> myBoardList = memberService.getMyBoardList(memberName);
        model.addAttribute("myBoardList", myBoardList);
        return "members/boardList";
    }

}
