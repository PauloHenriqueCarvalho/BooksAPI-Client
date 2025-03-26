package org.example.resources;

import jakarta.validation.Valid;
import org.example.entitys.user.RegisterDTO;
import org.example.entitys.user.User;
import org.example.entitys.user.LoginDTO;
import org.example.infra.security.TokenService;
import org.example.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/users")
public class UserResource {


    private AuthenticationManager authenticationManager;

    public UserResource(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO body) throws AuthenticationException {
        UserDetails userDetails = repository.findByLogin(body.login());
        if (userDetails != null && passwordEncoder.matches(body.password(), userDetails.getPassword())) {
            // Gera o token e retorna como resposta
            String token = tokenService.generateToken((User) userDetails);
            return ResponseEntity.ok(token);  // Retorna o token no corpo da resposta com status 200 OK
        }
        throw new AuthenticationException("Invalid login or password");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encodedPassword = customPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(),data.login(), encodedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();

    }


    @Bean
    @Qualifier("customPasswordEncoder")
    public PasswordEncoder customPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
