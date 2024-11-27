package hello.myboard.dto.borad;

import hello.myboard.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class BoardWriteDto {
    private String title;

    private String content;

}
