package br.com.clinicawszd.clinicavet.dto;

import br.com.clinicawszd.clinicavet.util.Procedimento;
import br.com.clinicawszd.clinicavet.util.StatusAgendamento;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {

    @Enumerated(EnumType.STRING)
    private Procedimento procedimento;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    private Long total;
}
