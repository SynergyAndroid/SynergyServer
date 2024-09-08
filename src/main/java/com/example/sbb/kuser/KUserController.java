package com.example.sbb.kuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth/kakao")
public class KUserController {

    @Autowired
    private KUserRepository kUserRepository;

    // 카카오 로그인 Redirect URI에서 호출되는 콜백
    @GetMapping("/callback")
    public ResponseEntity<String> kakaoCallback(@RequestParam String code) {
        // 1. 인증 코드로 Access Token 요청
        String accessToken = getAccessTokenFromKakao(code);

        // 2. Access Token으로 사용자 정보 요청
        KUser kUser = getUserProfileFromKakao(accessToken);

        // 3. 사용자 정보 저장
        kUserRepository.save(kUser);

        // 4. 클라이언트로 응답
        return ResponseEntity.ok("User information saved successfully");
    }

    // 카카오 서버에 Access Token 요청
    private String getAccessTokenFromKakao(String code) {
        RestTemplate restTemplate = new RestTemplate();

        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        // 요청 파라미터 구성
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("client_id", "89a1a373df888fa03ee6086aa05eb30f");  // 카카오 개발자 콘솔에서 발급받은 REST API 키
        params.put("redirect_uri", "http://localhost:9090/oauth/kakao/callback");
        params.put("code", code);

        // POST 요청으로 Access Token 받기
        Map<String, String> response = restTemplate.postForObject(tokenUrl, params, Map.class);
        return response.get("access_token");  // access_token 반환
    }

    // Access Token으로 카카오 사용자 정보 가져오기
    private KUser getUserProfileFromKakao(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        String profileUrl = "https://kapi.kakao.com/v2/user/me";

        // 요청 헤더에 Access Token 포함
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // 사용자 정보 요청
        Map<String, Object> response = restTemplate.getForObject(profileUrl, Map.class, headers);

        // 사용자 정보 추출
        Map<String, Object> kakaoAccount = (Map<String, Object>) response.get("kakao_account");

        String email = (String) kakaoAccount.get("email");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        String nickname = (String) profile.get("nickname");
        String profileImageUrl = (String) profile.get("profile_image_url");

        // KUser 객체 생성
        KUser kUser = new KUser();
        kUser.setEmail(email);
        kUser.setNickname(nickname);
        kUser.setProfileImageUrl(profileImageUrl);

        return kUser;
    }
}
