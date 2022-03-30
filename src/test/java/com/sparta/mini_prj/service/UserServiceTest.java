package com.sparta.mini_prj.service;

import com.sparta.mini_prj.dto.SignupRequestDto;
import com.sparta.mini_prj.models.Board;
import com.sparta.mini_prj.models.User;
import com.sparta.mini_prj.models.UserRoleEnum;
import com.sparta.mini_prj.repositoty.UserRepository;
import com.sparta.mini_prj.validator.SignupValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.sparta.mini_prj.models.UserRoleEnum.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("서비스테스트")
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("아이디 성공케이스")
    void idVaild5() {
        //given
        SignupRequestDto requestDto = new SignupRequestDto(
                "test", "test@gmail.com", "1111", "1111"
        );

        //when
        SignupValidator vaild = new SignupValidator(userRepository);


        //then
        assertEquals("회원가입 성공", vaild.getValidMessageForTest(requestDto));

    }

    @Test
    @DisplayName("비밀번호 성공케이스")
    void pwVaild() {
        //given
        SignupRequestDto requestDto = new SignupRequestDto(
                "test", "test1@naver.com", "1111", "1111"
        );

        //when
        SignupValidator vaild = new SignupValidator(userRepository);


        //then
        assertEquals("회원가입 성공", vaild.getValidMessageForTest(requestDto));

    }

    @Test
    @DisplayName("아이디 유효성 체크 1_한글사용")
    void idVaild() {


        SignupRequestDto requestDto = new SignupRequestDto(
                "한글사용", "test1@naver.com", "1111", "1111"
        );

        //when
        SignupValidator vaild = new SignupValidator(userRepository);

        // User user1 = userRepository.save(user);
        //then
        assertEquals("회원가입 성공", vaild.getValidMessageForTest(requestDto));
        //assertEquals(user1.getUsername(),requestDto.getUsername());
    }

    @Test
    @DisplayName("아이디 유효성 체크 2_3글자 미만입력")
    void idVaild2() {
        //given
        SignupRequestDto requestDto = new SignupRequestDto(
                "33", "test1@naver.com", "1111", "1111"
        );

        //when
        SignupValidator vaild = new SignupValidator(userRepository);


        //then
        assertEquals("회원가입 성공", vaild.getValidMessageForTest(requestDto));

    }

//    @Test
//    @DisplayName("아이디 유효성 체크 통과")
//    void idVaild3() {
//        //given
//        UserService userService = new UserService(userRepository, passwordEncoder);
//        User user = new User("test", "1111", "test@naver.com", USER);
//        when(userRepository.save(user)).thenReturn(user);
//
//        SignupRequestDto requestDto = new SignupRequestDto(
//                "test", "test1@naver.com", "1111", "1111"
//        );
//        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());
//        Optional<User> email = userRepository.findByEmail(requestDto.getEmail());
//        //when
//        SignupValidator vaild = new SignupValidator(userRepository);
//        vaild.vaildSignupResult(found,email);
//        //then
//        //userService.registerUser(user.getUsername());
//        assertEquals("회원가입 성공",vaild.vaildSignupResult(found,email));
//    }

    @Test
    @DisplayName("비밀번호 유효성 테스트 실패_비밀번호3자리")
    void pwVaild2() {
        //given
        SignupRequestDto requestDto = new SignupRequestDto(
                "test", "test1@naver.com", "333", "333"
        );

        //when
        SignupValidator vaild = new SignupValidator(userRepository);


        //then
        assertEquals("회원가입 성공", vaild.getValidMessageForTest(requestDto));

    }

    @Test
    @DisplayName("비밀번호 유효성 테스트 실패_비밀번호에 아디디값포함")
    void pwVaild3() {
        //given
        SignupRequestDto requestDto = new SignupRequestDto(
                "test", "test1@naver.com", "test", "test"
        );

        //when
        SignupValidator vaild = new SignupValidator(userRepository);


        //then
        assertEquals("회원가입 성공", vaild.getValidMessageForTest(requestDto));

    }

    @Test
    @DisplayName("비밀번호 유효성 테스트 실패_비빌번호와 비밀번호확인이 다른경우")
    void pwVaild4() {
        //given
        SignupRequestDto requestDto = new SignupRequestDto(
                "test", "test1@naver.com", "test", "test1"
        );

        //when
        SignupValidator vaild = new SignupValidator(userRepository);


        //then
        assertEquals("회원가입 성공", vaild.getValidMessageForTest(requestDto));

    }

    @Test
    @DisplayName("비밀번호 유효성 테스트 실패_비빌번호와 비밀번호확인이 다른경우2")
    void pwVaild5() {
        //given
        SignupRequestDto requestDto = new SignupRequestDto(
                "test", "test1@naver.com", "test1", "test"
        );

        //when
        SignupValidator vaild = new SignupValidator(userRepository);


        //then
        assertEquals("회원가입 성공", vaild.getValidMessageForTest(requestDto));

    }

}
