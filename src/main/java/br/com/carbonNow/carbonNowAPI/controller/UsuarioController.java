package br.com.carbonNow.carbonNowAPI.controller;

import br.com.carbonNow.carbonNowAPI.domain.Usuario;
import br.com.carbonNow.carbonNowAPI.dto.UsuarioCadastroDto;
import br.com.carbonNow.carbonNowAPI.dto.UsuarioExibicaoDto;
import br.com.carbonNow.carbonNowAPI.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carbonnow")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/cadastrarUsuario")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
        return usuarioService.salvarUsuario(usuarioCadastroDto);
    }

    @GetMapping("/listarUsuarios")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibicaoDto> listarUsuarios(Pageable pageable) {
        return usuarioService.listarUsuarios(pageable);
    }

    @DeleteMapping("/deletarUsuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }

    @PutMapping("/atualizarUsuario")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizarUsuario(@RequestBody @Valid Usuario usuario) {
        return usuarioService.atualizarUsuario(usuario);
    }
}
