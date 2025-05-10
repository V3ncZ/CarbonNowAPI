package br.com.carbonNow.carbonNowAPI.dto;

import br.com.carbonNow.carbonNowAPI.domain.Usuario;

public record UsuarioDto (
        Long id,
        String nome,
        String email
){
    public UsuarioDto(Usuario usuario) {
        this (
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
