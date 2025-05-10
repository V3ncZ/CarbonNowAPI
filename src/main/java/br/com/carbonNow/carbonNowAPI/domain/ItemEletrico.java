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
@Table(name = "T_CN_ITEM_ELETRICO")
public class ItemEletrico implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ID_ITEM_ELETRICO")
    @SequenceGenerator(name = "GEN_ID_ITEM_ELETRICO", sequenceName = "GEN_ID_ITEM_ELETRICO", allocationSize = 1)
    private Long idItemEletrico;

    @ManyToOne()
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @Column(name = "NOME_ITEM")
    private String nome;

    @Column(name = "CONSUMO_KW")
    private double consumoEmKw;

    @Column(name = "DT_USO")
    private LocalDate dataUso;

    @Column(name = "EMISSAO_CALCULADA")
    private double emissaoDeCarbono;

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
