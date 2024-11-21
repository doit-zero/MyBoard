package hello.myboard.controller;

import hello.myboard.entity.Board;
import hello.myboard.entity.Member;
import hello.myboard.repository.MemberRepository;
import hello.myboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
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
