package br.com.carbonNow.carbonNowAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, max = 12, message = "A senha deve ter pelo menos 6 caracteres")
        String senha
) {
}
