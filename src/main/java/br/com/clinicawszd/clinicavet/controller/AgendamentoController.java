package br.com.clinicawszd.clinicavet.controller;

import br.com.clinicawszd.clinicavet.dto.AgendamentoDTO;
import br.com.clinicawszd.clinicavet.model.Agendamento;
import br.com.clinicawszd.clinicavet.service.AgendamentoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;
    @ApiOperation(value = "Adiciona um agendamento")
    @PostMapping
    private ResponseEntity<Agendamento> createdNewAg(@RequestBody Agendamento novo){
        Agendamento res = service.createNewAg(novo);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().build();
    }
    @ApiOperation(value = "Retorna todos os agendamentos")
    @GetMapping
    public ArrayList<Agendamento> getAllAg(){
        return service.getAllAg();
    }

    @ApiOperation(value = "Retorna todos os agendamentos por mês")
    @GetMapping("/anoMes")
    public ArrayList<AgendamentoDTO> getAllAgByYearMonth(@RequestParam Integer ano, Integer mes){
        return service.getAllAgByYearMonth(ano, mes);
    }

    @ApiOperation(value = "Retorna um agendamento")
    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getOneAg(@PathVariable Long id){
        Agendamento res = service.getOneAg(id);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }


    @ApiOperation(value = "Altera um agendamento")
    @PutMapping
    public ResponseEntity<Agendamento> updateAg(@RequestBody  Agendamento novo){
        Agendamento res = service.updateAg(novo);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }
    @ApiOperation(value = "Altera o status de um agendamento")
    @PatchMapping
    public ResponseEntity<String> updateStatus(@RequestParam Long id, String status) throws ChangeSetPersister.NotFoundException {
        Agendamento res = service.updateStatus(status, id);

        if(res != null){
            return  ResponseEntity.ok("Status de agendamento alterado para " + status);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Deleta um agendamento")
    @DeleteMapping("/{id}")
    public void deleteAg(@PathVariable Long id){
        service.deleteAgById(id);
    }
}
