package hello.myboard.controller;

import hello.myboard.entity.Member;
import hello.myboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members")
    public String getMemberForm() {
        return "members/members";
    }

    @PostMapping("/members")
    public String addMember(@RequestParam("name") String name, Model model) {
        Member member = new Member(name);
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "members/result";
    }
}
