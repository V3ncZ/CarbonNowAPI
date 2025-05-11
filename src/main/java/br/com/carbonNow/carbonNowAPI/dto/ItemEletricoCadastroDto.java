package br.com.carbonNow.carbonNowAPI.dto;

import jakarta.validation.constraints.NotBlank;

public record ItemEletricoCadastroDto(
        Long idItemEletrico,
        Long idUsuario,

        @NotBlank(message = "Nome não pode ser vazio")
        String nome,

        @NotBlank(message = "Consumo não pode ser vazio")
        double consumoEmKw,

        @NotBlank(message = "Data de uso não pode ser vazio")
        String dataUso,

        @NotBlank(message = "Emissão de carbono não pode ser vazio")
        double emissaoDeCarbono
) {
}
