package br.com.carbonNow.carbonNowAPI.dto;

import br.com.carbonNow.carbonNowAPI.domain.Usuario;

public record UsuarioExibicaoDto(
        Long id,
        String nome,
        String email
){
    public UsuarioExibicaoDto(Usuario usuario) {
        this (
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
