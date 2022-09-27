package br.com.clinicawszd.clinicavet.model;

import br.com.clinicawszd.clinicavet.util.Procedimento;
import br.com.clinicawszd.clinicavet.util.StatusAgendamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pet")
    @JsonIgnoreProperties("agendamentos")
    private Pet pet;

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

}
