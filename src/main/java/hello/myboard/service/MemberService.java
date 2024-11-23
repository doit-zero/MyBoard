package hello.myboard.service;

import hello.myboard.dto.SignupDto;
import hello.myboard.dto.member.LoginDto;
import hello.myboard.entity.Board;
import hello.myboard.entity.Member;
import hello.myboard.repository.MemberRepository;
import hello.myboard.session.MemberSession;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberService.class);
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;
    //private static final String COOKIE_SESSION_ID = "SESSION_ID";
    public void signup(SignupDto signupDto) {
        Member member = new Member(signupDto.getName(),signupDto.getPassword());
        Member savedMember = memberRepository.save(member);

        MemberSession memberSession = MemberSession.builder()
                .name(savedMember.getName())
                .build();
        httpSession.setAttribute(memberSession.getName(), memberSession);
    }

    public void login(LoginDto loginDto) {
        MemberSession memberSession = MemberSession.builder()
                .name(loginDto.getName())
                .build();
        httpSession.setAttribute(memberSession.getName(), memberSession);
    }

    public List<Board> getBoarList(Long id) {
        Optional<Member> findMember = memberRepository.findById(id);
        return findMember.isPresent() ? findMember.get().getBoardList() : null;
    }

    public boolean checkUserExists(String loginDtoName) {
        return memberRepository.findByName(loginDtoName) != null;
    }

    public boolean validatePassword(String loginDtoName, String password) {
        Member findMember = memberRepository.findByName(loginDtoName);
        return findMember.getPassword().equals(password);
    }

    public void logout(String memberName) {
        httpSession.removeAttribute(memberName);
    }
}
