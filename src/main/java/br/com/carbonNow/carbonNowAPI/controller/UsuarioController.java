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

// A marcação precisa ser RestController para conseguirmos enviar um retorno de OBJ, caso contrário
// O spring ira tratar esse controler como modelo MVC tentando retornar um HTML por exemplo
@RestController
@RequestMapping("/carbonnow")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/buscarUsuario/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarUsuario(@PathVariable String email) {
        return usuarioService.buscarUsuarioPorEmail(email);
    }

    @GetMapping("/buscarUsuarioPorId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

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
