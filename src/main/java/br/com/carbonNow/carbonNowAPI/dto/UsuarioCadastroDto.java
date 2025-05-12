package br.com.carbonNow.carbonNowAPI.dto;

import br.com.carbonNow.carbonNowAPI.domain.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDto(
        Long id,

        @NotBlank(message = "O nome de usuário é obrigatório!")
        String nome,

        @NotBlank(message = "O email do usuário é obrigatório!")
        @Email
        String email,

        @NotBlank(message = "A senha do usuário é obrigatório!")
        @Size(min = 6, max = 12, message = "A senha precisa conter entre 6 e 12 caracteres!")
        String senha,

        UsuarioRole role
) {
}
