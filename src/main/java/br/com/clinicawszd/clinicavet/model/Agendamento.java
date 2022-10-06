package br.com.clinicawszd.clinicavet.model;

import br.com.clinicawszd.clinicavet.util.Procedimento;
import br.com.clinicawszd.clinicavet.util.StatusAgendamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

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
    private LocalDateTime dtCriacao;

    @Column(name = "dt_agendamento")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtAgendamento;

    @Enumerated(EnumType.STRING)
    private Procedimento procedimento;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento agStatus;


    public static class Builder{
        private Pet pet;
        private LocalDateTime dtCriacao;
        private LocalDateTime dtAgendamento;
        private Procedimento procedimento;
        private StatusAgendamento agStatus;

        public Agendamento.Builder pet(Pet pet){
            this.pet = pet;
            return this;
        }
        public Agendamento.Builder dtCriacao(LocalDateTime dtCriacao){
            this.dtCriacao = dtCriacao;
            return this;
        }
        public Agendamento.Builder dtAgendamento(LocalDateTime dtAgendamento){
            this.dtAgendamento = dtAgendamento;
            return this;
        }
        public Agendamento.Builder procedimento(Procedimento procedimento){
            this.procedimento = procedimento;
            return this;
        }
        public Agendamento.Builder agStatus(StatusAgendamento agStatus) {
            this.agStatus = agStatus;
            return this;
        }

        public Agendamento build() {
            return new Agendamento(this);
        }
    }

    private Agendamento(Agendamento.Builder builder){
        pet = builder.pet;
        dtCriacao = builder.dtCriacao;
        dtAgendamento = builder.dtAgendamento;
        procedimento = builder.procedimento;
        agStatus = builder.agStatus;
    }

}
