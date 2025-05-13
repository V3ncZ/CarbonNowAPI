package br.com.carbonNow.carbonNowAPI.dto;

import br.com.carbonNow.carbonNowAPI.domain.ItemEletrico;

import java.time.LocalDate;

public record ItemEletricoExibicaoDto(
        Long idItemEletrico,
        Long idUsuario,
        String nome,
        Double consumoEmKw,
        LocalDate dataUso,
        Double emissaoDeCarbono
) {
    public ItemEletricoExibicaoDto(ItemEletrico itemEletrico) {
        this(
                itemEletrico.getIdItemEletrico(),
                itemEletrico.getUsuario().getId(),
                itemEletrico.getNome(),
                itemEletrico.getConsumoEmKw(),
                itemEletrico.getDataUso(),
                itemEletrico.getEmissaoDeCarbono()
        );
    }
}
