package br.com.clinicawszd.clinicawszd.model;

import br.com.clinicawszd.clinicawszd.enums.Procedimento;
import br.com.clinicawszd.clinicawszd.enums.StatusAgendamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "agendamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pet_id")
    private Pet idPet;

    @Column(name = "dt_criacao")
    private LocalDate dtCriacao;

    @Column(name = "dt_agendamento")
    private LocalDate dtAgendamento;

    @Enumerated(EnumType.STRING)
    private Procedimento procedimento;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento agStatus;

}
