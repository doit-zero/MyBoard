package hello.myboard.controller;

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
        model.addAttribute("signupDto",new SignupDto());
        return "members/signup";
    }

    @PostMapping("/members")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "members/signup";
        }
        memberService.addMember(signupDto);
        return "home";
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("loginDto",new LoginDto());
        return "members/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            log.info("bindingResult.hasErrors() : {}",bindingResult.getGlobalError().toString());
            return "members/login";
        }
        return "home";

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
    @GetMapping("/members/{id}/boarList")
    public String getBoarList(@PathVariable Long id,Model model) {
        Optional<Member> findMember = memberRepository.findById(id);
        List<Board> boarList = memberService.getBoarList(id);
        model.addAttribute("boarList", boarList);
        return "members/boarList";
    }

}
