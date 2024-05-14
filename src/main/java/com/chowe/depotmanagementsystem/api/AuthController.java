package com.chowe.depotmanagementsystem.api;


import com.chowe.depotmanagementsystem.service.security.AuthService;
import com.chowe.depotmanagementsystem.service.security.dto.LoginRequest;
import com.chowe.depotmanagementsystem.service.security.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

        return authService.login(loginRequest);
    }


}
