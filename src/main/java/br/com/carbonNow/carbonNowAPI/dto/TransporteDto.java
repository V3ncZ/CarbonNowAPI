package br.com.carbonNow.carbonNowAPI.dto;

import br.com.carbonNow.carbonNowAPI.domain.Transporte;

import java.time.LocalDate;

public record TransporteDto(
        Long idTransporte,
        Long idUsuario,
        String nome,
        double distanciaEmKm,
        LocalDate dataDeUso,
        double emissaoDeCarbono,
        double emissaoPermitidaIso,
        boolean conformeIso
) {
    public TransporteDto(Transporte transporte) {
        this(
                transporte.getIdTransporte(),
                transporte.getUsuario().getId(),
                transporte.getNome(),
                transporte.getDistanciaEmKm(),
                transporte.getDataDeUso(),
                transporte.getEmissaoDeCarbono(),
                transporte.getEmissaoPermitidaIso(),
                transporte.isConformeIso()
        );
    }
}
