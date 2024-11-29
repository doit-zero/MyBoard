package hello.myboard.service;

import hello.myboard.dto.BoardDto;
import hello.myboard.dto.SignupDto;
import hello.myboard.dto.member.LoginDto;
import hello.myboard.entity.Board;
import hello.myboard.entity.Member;
import hello.myboard.repository.MemberRepository;
import hello.myboard.session.MemberSession;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;
    public static final String SESSION_ID = "sessioIdBymemberId";
    public void signup(SignupDto signupDto) {
        Member member = new Member(signupDto.getName(),signupDto.getPassword());
        Member savedMember = memberRepository.save(member);

        MemberSession memberSession = MemberSession.builder()
                .name(savedMember.getName())
                .build();
        httpSession.setAttribute(SESSION_ID, memberSession);
    }


    public void login(LoginDto loginDto) {
        MemberSession memberSession = MemberSession.builder()
                .name(loginDto.getName())
                .build();
        httpSession.setAttribute(SESSION_ID, memberSession);
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // 기존 세션을 가져옴 (없으면 null)
        if (session != null) {
            session.invalidate();  // 세션 무효화
        }
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

    public List<BoardDto> getMyBoardList() {
        MemberSession memberSession = (MemberSession)httpSession.getAttribute(SESSION_ID);
        Member findMember = memberRepository.findByName(memberSession.getName());

        List<Board> boardList = findMember.getBoardList();
        List<BoardDto> boardDtoList = new ArrayList<>();

        if(boardList.size() > 0) {
            for (Board board : boardList) {
                BoardDto boardDto = BoardDto.builder()
                        .id(board.getId())
                        .member(board.getMember())
                        .title(board.getTitle())
                        .createdAt(board.getCreatedAt())
                        .views(board.getLikes())
                        .likes(board.getLikes())
                        .build();
                boardDtoList.add(boardDto);
            }
        }
        return boardDtoList;
    }

}
