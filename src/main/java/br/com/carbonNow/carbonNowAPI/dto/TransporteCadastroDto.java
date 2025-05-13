package br.com.carbonNow.carbonNowAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record TransporteCadastroDto(
        Long idTransporte,
        Long idUsuario,

        @NotBlank(message = "O nome do transporte não pode ser vazio")
        String nome,

        @Positive(message = "A distância tem que ser maior que zero")
        Double distanciaEmKm,

        @NotNull(message = "A data de uso não pode ser vazia")
        LocalDate dataDeUso,

        @Positive(message = "A emissão de carbono não pode ser vazia")
        Double emissaoDeCarbono,

        @Positive(message = "A emissão permitida ISO não pode ser vazia")
        Double emissaoPermitidaIso,

        @NotNull(message = "O campo conforme ISO não pode ser vazio")
        Boolean conformeIso
) {
}
