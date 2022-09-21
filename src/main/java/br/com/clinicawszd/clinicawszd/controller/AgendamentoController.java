package br.com.clinicawszd.clinicawszd.controller;

import br.com.clinicawszd.clinicawszd.model.Agendamento;
import br.com.clinicawszd.clinicawszd.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping
    private ResponseEntity<Agendamento> createdNewAg(Agendamento novo){
        Agendamento res = service.createNewAg(novo);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().build();
    }
}
