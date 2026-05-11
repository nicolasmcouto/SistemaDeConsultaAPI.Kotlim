package CRUD.PacientesAPI.Kotlim.Infra

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.RestControllerAdvice

data class erroValidacao(
        val campo: String,
        val mensagem: String
)

@RestControllerAdvice
class Exceptions {

    fun TratarErrosDeValidacao(ex: MethodArgumentNotValidException): ResponseEntity<List<erroValidacao>>{
           val erros = ex.bindingResult.fieldErrors
               .map { erroValidacao(campo = it.field, mensagem = it.defaultMessage ?: "invalid") }

        return ResponseEntity.badRequest().body(erros)
    }


}