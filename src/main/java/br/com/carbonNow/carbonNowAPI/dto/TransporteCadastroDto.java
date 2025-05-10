package br.com.carbonNow.carbonNowAPI.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TransporteCadastroDto(
        Long idTransporte,
        Long idUsuario,

        @NotBlank(message = "O nome do transporte não pode ser vazio")
        String nome,

        @NotBlank(message = "A distância não pode ser vazia")
        double distanciaEmKm,

        @NotBlank(message = "A data de uso não pode ser vazia")
        LocalDate dataDeUso,

        @NotBlank(message = "A emissão de carbono não pode ser vazia")
        double emissaoDeCarbono,

        @NotBlank(message = "A emissão permitida ISO não pode ser vazia")
        double emissaoPermitidaIso,

        @NotBlank(message = "O campo conforme ISO não pode ser vazio")
        boolean conformeIso
) {
}
