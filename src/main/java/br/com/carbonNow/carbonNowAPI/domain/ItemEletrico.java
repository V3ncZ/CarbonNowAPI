package br.com.carbonNow.carbonNowAPI.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "T_CN_ITEM_ELETRICO")
public class ItemEletrico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ID_ITEM")
    @SequenceGenerator(name = "GEN_ID_ITEM", sequenceName = "GEN_ID_ITEM", allocationSize = 1)
    @Column(name = "ID_ITEM")
    private Long idItemEletrico;

    @ManyToOne()
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @Column(name = "NOME_ITEM")
    private String nome;

    @Column(name = "CONSUMO_KWH")
    private Double consumoEmKw;

    @Column(name = "DT_USO")
    private LocalDate dataUso;

    @Column(name = "EMISSAO_CALCULADA")
    private Double emissaoDeCarbono;

}
