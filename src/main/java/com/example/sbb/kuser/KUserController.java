package com.example.sbb.kuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class KUserController {

    @Autowired
    private KUserRepository kUserRepository;

    @PostMapping("/")
    public String kakaoLogin(@RequestBody KUser kUser) {
        kUserRepository.save(kUser);
        return "User information saved successfully";
    }
}
