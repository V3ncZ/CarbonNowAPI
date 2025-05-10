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
public class Transporte implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ID_TRANSPORTE")
    @SequenceGenerator(name = "GEN_ID_TRANSPORTE", sequenceName = "GEN_ID_TRANSPORTE", allocationSize = 1)
    @Column(name = "ID_TRANSPORTE")
    private long idTransporte;

    @ManyToOne()
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @Column(name = "TIPO_TRANSPORTE")
    private String nome;

    @Column(name = "DISTANCIA_KM")
    private double distanciaEmKm;

    @Column(name = "DT_USO")
    private LocalDate dataDeUso;

    @Column(name = "EMISSAO_CALCULADA")
    private double emissaoDeCarbono;

    @Column(name = "EMISSAO_PERMITIDA_ISO")
    private double emissaoPermitidaIso;

    @Column(name = "CONFORME_ISO")
    private boolean coformeIso;

    public boolean isConformeIso() {
        return this.emissaoDeCarbono <= this.emissaoPermitidaIso;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
