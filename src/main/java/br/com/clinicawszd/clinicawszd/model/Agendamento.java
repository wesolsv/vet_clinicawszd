package br.com.clinicawszd.clinicawszd.model;

import br.com.clinicawszd.clinicawszd.enums.Procedimento;
import br.com.clinicawszd.clinicawszd.enums.StatusAgendamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "agendamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pet_id")
    @NotNull(message = "O Pet e obrigatorio")
    private Pet idPet;

    @Column(name = "dt_criacao")
    private LocalDate dtCriacao;

    @Column(name = "dt_agendamento")
    @NotNull(message = "A data do agendamento e obrigatorio")
    private LocalDate dtAgendamento;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O procedimento e obrigatorio")
    private Procedimento procedimento;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento agStatus = StatusAgendamento.AGENDADO;

}
