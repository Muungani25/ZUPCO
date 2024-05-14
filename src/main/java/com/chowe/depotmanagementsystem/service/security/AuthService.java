package com.chowe.depotmanagementsystem.service.security;



import com.chowe.depotmanagementsystem.service.security.dto.LoginRequest;
import com.chowe.depotmanagementsystem.service.security.dto.LoginResponse;
import org.springframework.http.ResponseEntity;



public interface AuthService {
    ResponseEntity<LoginResponse> login(LoginRequest request);
}
