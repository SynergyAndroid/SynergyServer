// SignupRequest.java

package com.example.sbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @Size(min=3, max=25)
    @NotEmpty(message = "사용자ID는 필수 항목입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password;

    @NotEmpty(message = "이메일은 필수 항목입니다.")
    @Email // 이메일 형식 검증
    private String email;
}
