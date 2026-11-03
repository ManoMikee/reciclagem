package br.com.fiap.reciclagem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // TRATAR ERROS DE VALIDAÇÃO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarErrosValidacao(
            MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(erro ->
                erros.put(erro.getField(), erro.getDefaultMessage())
        );

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", "Erro de validação");
        resposta.put("mensagens", erros);
        resposta.put("dataHora", LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(resposta);
    }

    // TRATAR REGISTROS NÃO ENCONTRADOS
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> tratarNaoEncontrado(
            ResponseStatusException ex) {

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", ex.getStatusCode().value());
        resposta.put("erro", "Recurso não encontrado");
        resposta.put("mensagem", ex.getReason());
        resposta.put("dataHora", LocalDateTime.now());

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(resposta);
    }

    // TRATAR ERROS INESPERADOS
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> tratarErroInterno(
            Exception ex) {

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        resposta.put("erro", "Erro interno do servidor");
        resposta.put("mensagem", "Ocorreu um erro inesperado.");
        resposta.put("dataHora", LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(resposta);
    }
}