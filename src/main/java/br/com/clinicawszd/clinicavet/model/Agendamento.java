package br.com.clinicawszd.clinicavet.model;

import br.com.clinicawszd.clinicavet.util.Procedimento;
import br.com.clinicawszd.clinicavet.util.StatusAgendamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamento")
@Data
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pet")
    private Pet idPet;

    @Column(name = "dt_criacao")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape= JsonFormat.Shape.STRING)
    private LocalDateTime dtCriacao = LocalDateTime.now();

    @Column(name = "dt_agendamento")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtAgendamento;

    @Enumerated(EnumType.STRING)
    private Procedimento procedimento;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento agStatus = StatusAgendamento.AGENDADO;

    public Agendamento(Long id, Pet idPet, LocalDateTime dtAgendamento, Procedimento procedimento, StatusAgendamento agStatus) {
        this.id = id;
        this.idPet = idPet;
        this.dtAgendamento = dtAgendamento;
        this.procedimento = procedimento;
        this.agStatus = agStatus;
    }
}
