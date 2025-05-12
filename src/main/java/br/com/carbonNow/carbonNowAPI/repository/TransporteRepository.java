package br.com.carbonNow.carbonNowAPI.repository;

import br.com.carbonNow.carbonNowAPI.domain.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface TransporteRepository extends JpaRepository<Transporte, Long> {
    UserDetails findByNome(String name);
}
