package br.com.carbonNow.carbonNowAPI.dto;

import br.com.carbonNow.carbonNowAPI.domain.Transporte;

import java.time.LocalDate;

public record TransporteExibicaoDto(
        Long idTransporte,
        Long idUsuario,
        String nome,
        Double distanciaEmKm,
        LocalDate dataDeUso,
        Double emissaoDeCarbono,
        Double emissaoPermitidaIso,
        Boolean conformeIso
) {
    public TransporteExibicaoDto(Transporte transporte) {
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
