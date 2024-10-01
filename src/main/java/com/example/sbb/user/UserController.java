// UserController.java

package com.example.sbb.user;

import com.example.sbb.DataNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    // 회원가입 API (기존 코드 그대로 유지)
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            userService.create(signupRequest.getUsername(), signupRequest.getEmail(), signupRequest.getPassword());
        } catch(DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("error", "이미 등록된 사용자입니다."));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message", "회원가입 성공"));
    }

    // 로그인 API 수정
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
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

            return ResponseEntity.ok(response);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "로그인 실패"));
        }
    }

    // 위치 업데이트 API 추가
    @PostMapping("/location")
    public ResponseEntity<?> updateLocation(@RequestBody Map<String, Float> locationMap) {
        Float mapX = locationMap.get("mapX");
        Float mapY = locationMap.get("mapY");

        // 입력값 검증
        if (mapX == null || mapY == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "mapX와 mapY는 필수 항목입니다."));
        }

        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        try {
            // 사용자 위치 업데이트
            userService.updateLocation(username, mapX, mapY);
            return ResponseEntity.ok(Collections.singletonMap("message", "위치가 성공적으로 업데이트되었습니다."));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "사용자를 찾을 수 없습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "위치 업데이트 중 오류가 발생했습니다."));
        }
    }
}
