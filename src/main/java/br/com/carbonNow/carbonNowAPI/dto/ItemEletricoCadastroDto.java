package br.com.carbonNow.carbonNowAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record ItemEletricoCadastroDto(
        Long idUsuario,

        @NotBlank(message = "Nome não pode ser vazio")
        String nome,

        @Positive(message = "Consumo não pode ser vazio")
        Double consumoEmKw,

        @NotNull(message = "A data de uso não pode ser vazia")
        LocalDate dataUso,

        @Positive(message = "Emissão de carbono não pode ser vazio")
        Double emissaoDeCarbono
) {
}
