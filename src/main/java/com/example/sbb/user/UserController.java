package com.example.sbb.user;

import com.example.sbb.DataNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.sbb.security.JwtTokenProvider;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            logger.warn("회원가입 입력값 오류: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            userService.create(signupRequest.getUsername(), signupRequest.getEmail(), signupRequest.getPassword());
        } catch(DataIntegrityViolationException e){
            logger.error("회원가입 중 데이터 무결성 오류: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("error", "이미 등록된 사용자입니다."));
        } catch(Exception e){
            logger.error("회원가입 중 서버 오류: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }

        logger.info("회원가입 성공: {}", signupRequest.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message", "회원가입 성공"));
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            logger.warn("로그인 입력값 오류: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            // 사용자 인증
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // JWT 토큰 생성
            String token = jwtTokenProvider.createToken(authentication.getName());

            Map<String, Object> response = new HashMap<>();
            response.put("username", authentication.getName());
            response.put("token", token);

            logger.info("로그인 성공: {}", authentication.getName());
            return ResponseEntity.ok(response);

        } catch (Exception e){
            logger.error("로그인 실패: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "로그인 실패"));
        }
    }

    // 위치 업데이트 API
    @PostMapping("/location")
    public ResponseEntity<?> updateLocation(@Valid @RequestBody LocationRequest locationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            logger.warn("위치 업데이트 입력값 오류: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }

        Float mapX = locationRequest.getMapX();
        Float mapY = locationRequest.getMapY();

        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        try {
            // 사용자 위치 업데이트
            userService.updateLocation(username, mapX, mapY);
            logger.info("사용자 [{}]의 위치가 업데이트되었습니다: mapX={}, mapY={}", username, mapX, mapY);
            return ResponseEntity.ok(Collections.singletonMap("message", "위치가 성공적으로 업데이트되었습니다."));
        } catch (DataNotFoundException e) {
            logger.error("사용자 [{}]를 찾을 수 없습니다.", username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "사용자를 찾을 수 없습니다."));
        } catch (Exception e) {
            logger.error("위치 업데이트 중 오류가 발생했습니다: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "위치 업데이트 중 오류가 발생했습니다."));
        }
    }
}
