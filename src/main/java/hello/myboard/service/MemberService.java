package hello.myboard.service;

import hello.myboard.dto.MemberDto;
import hello.myboard.entity.Board;
import hello.myboard.entity.Member;
import hello.myboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void addMember(MemberDto memberDto) {
        Member member = new Member(memberDto.getName(),memberDto.getPassword());
        memberRepository.save(member);
    }

    public List<Board> getBoarList(Long id) {
        Optional<Member> findMember = memberRepository.findById(id);
        return findMember.isPresent() ? findMember.get().getBoardList() : null;
    }


//    public boolean isNameExists(){
//        return memberRepository.exists(memberDto);
//    }
}
