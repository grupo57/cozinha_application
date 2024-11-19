package br.com.fiap.soat07.techchallenge.cozinha.infra.rest;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.entity.Atendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.SituacaoDoAtendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.exception.AtendimentoNotFoundException;
import br.com.fiap.soat07.techchallenge.cozinha.core.exception.PedidoJaAtendidoException;
import br.com.fiap.soat07.techchallenge.cozinha.infra.rest.dto.PedidoDTO;
import br.com.fiap.soat07.techchallenge.cozinha.infra.service.CozinhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Cozinha", description = "Cozinha")
@RestController
@RequestMapping
public class CozinhaController {

    private final CozinhaService cozinhaService;

    public CozinhaController(CozinhaService cozinhaService) {
        this.cozinhaService = cozinhaService;
    }


    @Operation(
            operationId = "criar",
            description = "Criar atendimento para o pedido",
            tags = {"Cozinha"}
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = PedidoDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid values"),
            @ApiResponse(responseCode = "400", description = "Pedido  already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorResponse.class)) }) })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<?> createAtendimento(@RequestBody final PedidoDTO pedidoDTO) {
        Atendimento atendimento = null;
        try {
            atendimento = cozinhaService.getCreateAtendimentoUseCase().execute(pedidoDTO);
        } catch (PedidoJaAtendidoException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

        return ResponseEntity.created(URI.create("/cozinha/atendimentos/"+atendimento.getId())).build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = PedidoDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid values"),
            @ApiResponse(responseCode = "400", description = "Pedido  already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorResponse.class)) }) })
    @GetMapping(value = "/atendimentos/{id}")
    @Transactional
    public ResponseEntity<?> get(@PathVariable final Long id) {
        if (id == null)
            return ResponseEntity.badRequest().build();

        Atendimento atendimento = null;
        try {
            atendimento = cozinhaService.getSearchAtendimentoUseCase()
                    .findById(id)
                    .orElseThrow(() -> new AtendimentoNotFoundException(id));
            return ResponseEntity.ok(atendimento);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = PedidoDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid values"),
            @ApiResponse(responseCode = "400", description = "Pedido  already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorResponse.class)) }) })
    @GetMapping(value = "/atendimentos/{id}/iniciado")
    @Transactional
    public ResponseEntity<?> iniciado(@PathVariable final Long id) {
        if (id == null)
            return ResponseEntity.badRequest().build();

        Atendimento atendimento = null;
        try {
            atendimento = cozinhaService.getSearchAtendimentoUseCase()
                    .findById(id)
                    .orElseThrow(() -> new AtendimentoNotFoundException(id));
            atendimento = cozinhaService.getUpdateAtendimentoUseCase().execute(atendimento, SituacaoDoAtendimento.INICIADO);

            System.err.println(atendimento);
            System.out.println(atendimento);
            return ResponseEntity.ok(atendimento);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = PedidoDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid values"),
            @ApiResponse(responseCode = "400", description = "Pedido  already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorResponse.class)) }) })
    @GetMapping(value = "/atendimentos/{id}/concluido")
    @Transactional
    public ResponseEntity<?> concluido(@PathVariable final Long id) {
        if (id == null)
            return ResponseEntity.badRequest().build();

        Atendimento atendimento = null;
        try {
            atendimento = cozinhaService.getSearchAtendimentoUseCase()
                    .findById(id)
                    .orElseThrow(() -> new AtendimentoNotFoundException(id));
            return ResponseEntity.created(URI.create("/cozinha/atendimentos/"+atendimento.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
