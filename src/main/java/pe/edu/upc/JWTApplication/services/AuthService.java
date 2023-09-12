package pe.edu.upc.JWTApplication.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.edu.upc.JWTApplication.dtos.AuthResponseDTO;
import pe.edu.upc.JWTApplication.dtos.LoginRequestDTO;
import pe.edu.upc.JWTApplication.dtos.RegisterRequestDTO;
import pe.edu.upc.JWTApplication.entities.RoleEnum;
import pe.edu.upc.JWTApplication.entities.UserEntity;
import pe.edu.upc.JWTApplication.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    
    public AuthResponseDTO login(LoginRequestDTO request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.genToken(user);
        return AuthResponseDTO.builder().token(token).build();
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
        UserEntity user = UserEntity.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .names(request.getNames())
            .surnames(request.getSurnames())
            .country(request.getCountry())
            .role(RoleEnum.USER)
            .build();
        userRepository.save(user);
        return AuthResponseDTO.builder()
            .token(jwtService.genToken(user))
            .build();
    }

}
