package br.com.carbonNow.carbonNowAPI.controller;

import br.com.carbonNow.carbonNowAPI.config.security.TokenService;
import br.com.carbonNow.carbonNowAPI.domain.Usuario;
import br.com.carbonNow.carbonNowAPI.dto.LoginDto;
import br.com.carbonNow.carbonNowAPI.dto.TokenDto;
import br.com.carbonNow.carbonNowAPI.dto.UsuarioCadastroDto;
import br.com.carbonNow.carbonNowAPI.dto.UsuarioExibicaoDto;
import br.com.carbonNow.carbonNowAPI.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                loginDto.email(),
                loginDto.senha()
        );

        // Essa linha utiliza a implementação da classe user details do nosso obj Usuario, e automaticamente busca no banco
        // o usuario informado. Com a senha criptografa implementada no registro, ao salvar o usuário
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDto(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@Valid @RequestBody UsuarioCadastroDto usuarioCadastroDto) {
        return usuarioService.salvarUsuario(usuarioCadastroDto);
    }
}
