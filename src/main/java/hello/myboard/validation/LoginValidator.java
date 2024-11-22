package hello.myboard.validation;

import hello.myboard.dto.member.LoginDto;
import hello.myboard.service.MemberService;
import hello.myboard.validation.annotation.LoginValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginValidator implements ConstraintValidator<LoginValidation, LoginDto> {

    private final MemberService memberService;

    @Override
    public void initialize(LoginValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LoginDto loginDto, ConstraintValidatorContext context) {
        if(loginDto == null){
            return false;
        }

        boolean userExists = memberService.checkUserExists(loginDto.getName());

        if(userExists){
            return memberService.validatePassword(loginDto.getName(),loginDto.getPassword());
        }

        return false;
    }
}
