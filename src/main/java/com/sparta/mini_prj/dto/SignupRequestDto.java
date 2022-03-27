package com.sparta.mini_prj.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@Data
public class SignupRequestDto {

    @NotBlank(message ="아이디는 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-z0-9-_]{3,10}$", message = "닉네임은 영문자와 숫자를 포함,3~10자리여야 합니다.")
    private String username;

    @NotBlank(message ="비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-z0-9-_]{4,10}$", message = "비밀번호는 특수문자와 한글을 제외한 4~10자리여야 합니다.")
    private String password;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotBlank
    private String password2;

    private boolean admin = false;
    private String adminToken = "";
}
