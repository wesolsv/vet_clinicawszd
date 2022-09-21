package br.com.clinicawszd.clinicawszd.controller;

import br.com.clinicawszd.clinicawszd.model.Agendamento;
import br.com.clinicawszd.clinicawszd.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping
    private ResponseEntity<Agendamento> createdNewAg(@RequestBody Agendamento novo){
        Agendamento res = service.createNewAg(novo);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ArrayList<Agendamento> getAllAg(){
        return service.getAllAg();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getOneAg(@PathVariable Long id){
        Agendamento res = service.getOneAg(id);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Agendamento> updateAg(@RequestBody  Agendamento novo){
        Agendamento res = service.updateAg(novo);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteAg(@PathVariable Long id){
        service.deleteAgById(id);
    }
}
