package br.com.carbonNow.carbonNowAPI.controller;

import br.com.carbonNow.carbonNowAPI.dto.UsuarioCadastroDto;
import br.com.carbonNow.carbonNowAPI.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/carbonnow")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/cadastrarUsuario")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
        usuarioService.salvarUsuario(usuarioCadastroDto);
    }
}
