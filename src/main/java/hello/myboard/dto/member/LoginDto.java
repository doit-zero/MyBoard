package hello.myboard.dto.member;

import hello.myboard.validation.annotation.LoginValidation;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@LoginValidation // 데이터베이스에 로그인 정보가 있는지 확인하는 어노테이션
public class LoginDto {
    @NotEmpty(message = "이름을 입력하세요.")
    private String name;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;

}
