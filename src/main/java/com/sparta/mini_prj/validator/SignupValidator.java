package com.sparta.mini_prj.validator;

import com.sparta.mini_prj.dto.SignupRequestDto;
import com.sparta.mini_prj.models.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SignupValidator {

    public static void validateSignup(SignupRequestDto requestDto, Optional<User> found, Optional<User> email) {
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        // 비밀번호에 아이디값 금지
        if(requestDto.getUsername().equals(requestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호에는 아이디값을 사용할 수 없습니다");
        }
        // 비밀번호 재확인
        if(!requestDto.getPassword().equals(requestDto.getPassword2())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 이메일 중복
        if (email.isPresent()){
            throw new IllegalArgumentException("이미 사용중인 Email 입니다.");
        }
    }
}
