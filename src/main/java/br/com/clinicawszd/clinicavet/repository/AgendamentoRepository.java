package br.com.clinicawszd.clinicavet.repository;

import br.com.clinicawszd.clinicavet.dto.AgendamentoDTO;
import br.com.clinicawszd.clinicavet.model.Agendamento;
import br.com.clinicawszd.clinicavet.util.Procedimento;
import br.com.clinicawszd.clinicavet.util.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {


    @Query("SELECT "
            + "new br.com.clinicawszd.clinicavet.dto.AgendamentoDTO(ag.procedimento, ag.agStatus , COUNT(ag.agStatus)) "
            + "FROM Agendamento ag "
            + "WHERE year(ag.dtAgendamento) = :ano "
            + "AND month(ag.dtAgendamento) = :mes "
            + "GROUP BY ag.procedimento")
    public ArrayList<AgendamentoDTO> returnAgendYearMonth(
            @Param ("ano")Integer ano,
            @Param ("mes") Integer mes);
}
