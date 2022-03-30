package com.sparta.mini_prj.validator;

import com.sparta.mini_prj.dto.SignupRequestDto;
import com.sparta.mini_prj.models.User;
import com.sparta.mini_prj.repositoty.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;


@Component
public class SignupValidator {

    private final UserRepository userRepository;

    public SignupValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void validateSignup(SignupRequestDto requestDto, Optional<User> found, Optional<User> email) {
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        // 비밀번호에 아이디값 금지
        if (requestDto.getUsername().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호에는 아이디값을 사용할 수 없습니다");
        }
        // 비밀번호 재확인
        if (!requestDto.getPassword().equals(requestDto.getPassword2())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 이메일 중복
        if (email.isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 Email 입니다.");
        }
    }

    //test
    public String vaildSignupResult(Optional<User> found, Optional<User> email){
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 이메일 중복
        if (email.isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 Email 입니다.");
        }
        return "회원가입 성공";
    }

    //Test
    public String getValidMessageForTest(SignupRequestDto requestDto) {
        if (!Pattern.matches("^[A-Za-z0-9]{3,}", requestDto.getUsername())) {
            return "닉네임은 특수문자와 한글을 제외한 3~10자리여야 합니다.";
        } else if (!Pattern.matches("^[A-Za-z0-9]{4,}", requestDto.getPassword())) {
            return "비밀번호를 확인하세요.";
        } else if (!requestDto.getPassword().equals(requestDto.getPassword2())) {
            return "비밀번호가 일치하지 않습니다.";
        } else if (requestDto.getPassword().contains(requestDto.getUsername())) {
            return "비밀번호는 닉네임을 포함할 수 없습니다.";
        } else
            return "회원가입 성공";
    }
}
