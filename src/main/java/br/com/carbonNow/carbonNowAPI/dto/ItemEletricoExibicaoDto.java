package br.com.carbonNow.carbonNowAPI.dto;

import br.com.carbonNow.carbonNowAPI.domain.ItemEletrico;

public record ItemEletricoExibicaoDto(
        Long idItemEletrico,
        Long idUsuario,
        String nome,
        double consumoEmKw,
        String dataUso,
        double emissaoDeCarbono
) {
    public ItemEletricoExibicaoDto(ItemEletrico itemEletrico) {
        this(
                itemEletrico.getIdItemEletrico(),
                itemEletrico.getUsuario().getId(),
                itemEletrico.getNome(),
                itemEletrico.getConsumoEmKw(),
                itemEletrico.getDataUso().toString(),
                itemEletrico.getEmissaoDeCarbono()
        );
    }
}
