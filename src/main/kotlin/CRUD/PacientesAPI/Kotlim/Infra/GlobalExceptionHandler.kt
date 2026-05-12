package CRUD.PacientesAPI.Kotlim.Infra

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


class ResourceNotFoundException(message: String): RuntimeException(message)

class BusinessException(message: String): RuntimeException(message)

class ConflictException(message: String): RuntimeException(message)

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException :: class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<List<ErroValidacaoDTO>>{
        val erros = ex.bindingResult.fieldErrors
                      .map {ErroValidacaoDTO(campo = it.field, mensagem = it.defaultMessage ?: "invalido")  }
            return ResponseEntity.badRequest().body(erros)
    }

    @ExceptionHandler(ResourceNotFoundException:: class)
    fun handleNotFound(ex: ResourceNotFoundException): ResponseEntity<MensagemDeErroDTO>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MensagemDeErroDTO(ex.message ?: "invalido"))
    }

    @ExceptionHandler(BusinessException ::class)
    fun handleBusiness(ex: BusinessException): ResponseEntity<MensagemDeErroDTO>{
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(MensagemDeErroDTO(ex.message ?: "invalido"))
    }
    @ExceptionHandler(ConflictException:: class)
    fun handleConflict(ex: ConflictException): ResponseEntity<MensagemDeErroDTO>{
        return ResponseEntity.status(HttpStatus.CONFLICT).body(MensagemDeErroDTO(ex.message ?: "Conflito de dados"))
    }
}