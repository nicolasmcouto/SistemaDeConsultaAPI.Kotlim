package CRUD.PacientesAPI.Kotlim.Controller

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.PacienteDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.PacienteUpdateDTO
import CRUD.PacientesAPI.Kotlim.Service.PacienteService
import jakarta.validation.Valid
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
@RequestMapping("/pacientes")
class PacienteController (private val service: PacienteService
){
   @GetMapping
    fun listar(): List<PacienteDTO>{return service.listar() }

    @GetMapping("/{id}")
    fun buscaPorId(@PathVariable id: Long): ResponseEntity<PacienteDTO>{
        return service.buscaPorId(id)
                     ?.let{ResponseEntity.ok(it) }
                     ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun CriaPaciente(@Valid@RequestBody dto: PacienteDTO): ResponseEntity<PacienteDTO>{ return ResponseEntity.ok(service.salvar(dto)) }

    @DeleteMapping ("/{id}/deletar")
    fun deletar(@PathVariable id: Long): ResponseEntity<Unit>{
        service.deletar(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun atualizaPaciente(@PathVariable id: Long,@Valid@RequestBody dto: PacienteUpdateDTO): ResponseEntity<PacienteUpdateDTO>{
        service.atualiza(id, dto)
        return ResponseEntity.ok(dto)
    }

    @DeleteMapping("/{id}")
    fun softDelete(@PathVariable id: Long): ResponseEntity<Unit>{
        service.softDelete(id)
        return ResponseEntity.noContent().build()
    }

}