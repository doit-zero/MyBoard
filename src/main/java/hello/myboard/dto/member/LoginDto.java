package hello.myboard.dto.member;

import hello.myboard.validation.annotation.LoginValidation;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@LoginValidation
public class LoginDto {
    @NotEmpty(message = "이름을 입력하세요.")
    private String name;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;

}
