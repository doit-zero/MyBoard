package hello.myboard.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignupDto {

    @NotEmpty
    @Size(min = 2,max = 10,message = "이름은 2자 이상, 10자 이하로 입력해주세요.")
    private String name;

    @NotEmpty
    @Size(min = 4,max = 16,message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

}
