package CRUD.PacientesAPI.Kotlim.Controller

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Service.ConsultaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/consultas")
class ConsultaController(private val service: ConsultaService){

    @PostMapping
    fun agendarConsulta(@RequestBody dto: AgendamentoConsultaDTO) : ResponseEntity<AgendamentoConsultaDTO>{

        var consulta = service.agendarConsulta(dto)
        return ResponseEntity.ok(consulta)

    }



}