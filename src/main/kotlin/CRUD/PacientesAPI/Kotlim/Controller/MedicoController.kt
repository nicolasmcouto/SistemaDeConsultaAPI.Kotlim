package CRUD.PacientesAPI.Kotlim.Controller

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.MedicoDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.MedicoUpdateDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.PacienteDTO
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toEntity
import CRUD.PacientesAPI.Kotlim.Domain.repository.MedicoRepository
import CRUD.PacientesAPI.Kotlim.Domain.repository.PacienteRepository
import CRUD.PacientesAPI.Kotlim.Service.MedicoService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/medicos")
class MedicoController(private val service : MedicoService) {

    @PostMapping
    fun CreateMedico(@Valid @RequestBody dto: MedicoDTO) : ResponseEntity<MedicoDTO> { return ResponseEntity.ok(service.salvar(dto)) }

    @GetMapping("/{id}")
    fun findById(@Valid @PathVariable id: Long): ResponseEntity<MedicoDTO> {
        return service.findById(id)
                     ?.let { ResponseEntity.ok(it)}
                     ?: ResponseEntity.notFound().build()
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<MedicoDTO>> { return ResponseEntity.ok(service.findAll()) }

    @PutMapping("/{id}")
    fun alterar(@PathVariable id: Long, @Valid @RequestBody dto: MedicoUpdateDTO) : ResponseEntity<MedicoUpdateDTO> { return ResponseEntity.ok(service.alterar(id, dto)) }

    @DeleteMapping("/{id}/deletar")
    fun delete(@PathVariable id: Long) : ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
    @DeleteMapping("/{id}")
    fun softDelete(@PathVariable id: Long): ResponseEntity<Unit>{
        service.softDelete(id)
        return ResponseEntity.noContent().build()
    }

}