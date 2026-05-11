package CRUD.PacientesAPI.Kotlim.Domain.Entity

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.MedicoDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.MedicoUpdateDTO
import CRUD.PacientesAPI.Kotlim.Domain.enums.Especialidade
import jakarta.persistence.*

@Entity
@Table(name = "medicos")
class MedicoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var nome: String,

    @Column(unique = true, nullable = false)
    var crm: String,

    @Column(unique = true,nullable = false)
    var email: String,

    @Column(nullable = false)
    var especialidade: Especialidade?,

    @Column(nullable = false)
    var ativa: Boolean = true

) {
    fun MedicoDTO.toEntity() = MedicoEntity(
        id = this.id,
        nome = this.nome,
        crm = this.crm,
        email = this.email,
        especialidade = this.especialidade,
        ativa = this.ativa
    )

    fun MedicoEntity.toDTO() = MedicoDTO(
        id = this.id,
        nome = this.nome,
        crm = this.crm,
        email = this.email,
        especialidade = this.especialidade,
        ativa = this.ativa
    )



}