package br.com.clinicawszd.clinica.model;

import br.com.clinicawszd.clinica.enums.Procedimento;
import br.com.clinicawszd.clinica.enums.StatusAgendamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

}
