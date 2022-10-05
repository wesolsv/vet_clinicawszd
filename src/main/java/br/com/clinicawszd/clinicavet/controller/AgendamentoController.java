package br.com.clinicawszd.clinicavet.controller;

import br.com.clinicawszd.clinicavet.dto.AgendamentoDTO;
import br.com.clinicawszd.clinicavet.model.Agendamento;
import br.com.clinicawszd.clinicavet.service.AgendamentoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<Agendamento>(service.createdNewAg(novo), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Retorna todos os agendamentos")
    @GetMapping
    public ArrayList<Agendamento> getAllAg(){
        return service.getAllAg();
    }

    @ApiOperation(value = "Retorna todos os agendamentos por ano e mês")
    @GetMapping("/ano/mes")
    public ArrayList<AgendamentoDTO> getAllAgByYearMonth(@RequestParam Integer ano, Integer mes){
        return service.getAllAgByYearMonth(ano, mes);
    }

    @ApiOperation(value = "Retorna um agendamento")
    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getOneAg(@PathVariable Long id){
        return ResponseEntity.ok(service.getOneAg(id));
    }

    @ApiOperation(value = "Altera um agendamento")
    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> updateAg(@PathVariable Long id, @RequestBody  Agendamento novo){
        return new ResponseEntity<Agendamento>(service.updateAg(id, novo), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Altera o status de um agendamento")
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestBody String status) throws ChangeSetPersister.NotFoundException {
        Agendamento res = service.updateStatus(status, id);
        return new ResponseEntity<String>("Status de agendamento alterado para " + status,
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deleta um agendamento")
    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteAg(@PathVariable Long id){
        ResponseEntity.status(401);
        return "Não é possível deletar um agendamento, é importante manter o histórico";
    }
}
