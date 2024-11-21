package hello.myboard.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberDto {

    @NotEmpty
    @Size(min = 4,max = 16,message = "이름은 4자 이상, 16자 이하로 입력해주세요.")
    private String name;

    @NotEmpty
    @Size(min = 8,max = 16,message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @Override
    public String toString() {
        return "MemberDto{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
