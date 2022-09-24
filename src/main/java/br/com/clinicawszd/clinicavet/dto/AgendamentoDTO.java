package br.com.clinicawszd.clinicavet.dto;

import br.com.clinicawszd.clinicavet.util.Procedimento;
import br.com.clinicawszd.clinicavet.util.StatusAgendamento;
import jdk.jshell.Snippet;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class AgendamentoDTO {

//    @NonNull
//    private Integer ano;
//    @NonNull
//    private Integer mes;

    @Enumerated(EnumType.STRING)
    private Procedimento procedimento;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    private Long total;

    public AgendamentoDTO() {
    }

    public AgendamentoDTO(Procedimento procedimento, StatusAgendamento status, Long total) {
        this.procedimento = procedimento;
        this.status = status;
        this.total = total;
    }
}
