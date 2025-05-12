package br.com.carbonNow.carbonNowAPI.repository;

import br.com.carbonNow.carbonNowAPI.domain.ItemEletrico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEletricoRepository extends JpaRepository<ItemEletrico, Long> {
    ItemEletrico findByNome(String nome);
}
