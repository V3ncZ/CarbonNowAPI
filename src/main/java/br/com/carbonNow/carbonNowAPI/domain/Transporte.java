package br.com.carbonNow.carbonNowAPI.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "T_CN_TRANSPORTE")
public class Transporte{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRANSPORTE")
    private Long idTransporte;

    @ManyToOne()
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @Column(name = "TIPO_TRANSPORTE")
    private String nome;

    @Column(name = "DISTANCIA_KM")
    private Double distanciaEmKm;

    @Column(name = "DT_USO")
    private LocalDate dataDeUso;

    @Column(name = "EMISSAO_CALCULADA")
    private Double emissaoDeCarbono;

    @Column(name = "EMISSAO_PERMITIDA_ISO")
    private Double emissaoPermitidaIso;

    @Column(name = "CONFORME_ISO")
    private Boolean coformeIso;

    public boolean isConformeIso() {
        return this.emissaoDeCarbono <= this.emissaoPermitidaIso;
    }

}
