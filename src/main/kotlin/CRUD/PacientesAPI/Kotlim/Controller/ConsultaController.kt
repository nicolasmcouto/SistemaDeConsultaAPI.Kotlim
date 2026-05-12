package CRUD.PacientesAPI.Kotlim.Controller

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.ConsultaDTO
import CRUD.PacientesAPI.Kotlim.Service.ConsultaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/consultas")
class ConsultaController(private val service: ConsultaService){

    @PostMapping
    fun agendarConsulta(@RequestBody dto: AgendamentoConsultaDTO) : ResponseEntity<AgendamentoConsultaDTO>{ return ResponseEntity.ok(service.agendarConsulta(dto)) }

    @GetMapping
    fun AllfindConsultas():ResponseEntity<List<ConsultaDTO>>{ return ResponseEntity.ok(service.findAll()) }

    @GetMapping("/{id}")
    fun findById(@PathVariable id:Long): ResponseEntity<ConsultaDTO>{ return ResponseEntity.ok(service.findById(id)) }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit>{
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

}