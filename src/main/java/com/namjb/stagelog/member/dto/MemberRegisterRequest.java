package com.namjb.stagelog.member.dto;


import com.namjb.stagelog.member.entity.Member;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
public class MemberRegisterRequest {


    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]{5,20}",
            message = "영문, 숫자, _만을 사용하세요")
    private String loginId;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@$%^&*])[a-zA-Z0-9!@$%^&*]{8,20}",
            message = "영문, 숫자, 특수문자를 포함한 10~20자리로 입력해주세요")
    private String password;
    @NotBlank
    @Email(message = "이메일 형식을 확인하세요")
    private String email;
    @NotBlank
    @Pattern(regexp = "^[ㄱ-힣]{1,10}",
            message = "올바른 이름을 입력하세요")
    private String name;
    @NotNull
    @Past(message = "유효한 날짜를 입력하세요")
    private LocalDate birthDate;

    public Member toMemberEntity() {

        return  Member.builder()
                .loginId(this.getLoginId())
                .password(this.getPassword())
                .name(this.getName())
                .birthDate(this.getBirthDate())
                .email(this.email)
                .build();

    }

}
