package kr.blackteam.dice.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import kr.blackteam.dice.server.dto.DiceCreationDto;
import kr.blackteam.dice.server.dto.DiceFullInfoDto;
import kr.blackteam.dice.server.service.DiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Dice",
    description = "API functions related to dice actions"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/dices")
public class DiceController {

  private final DiceService diceService;

  @Operation(
      summary = "Create a new Dice",
      description = "Create a new Dice based on the request criteria"
  )
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Information needed to create a new Dice",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = DiceCreationDto.class)
      )
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successfully created new Dice",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = DiceFullInfoDto.class)
              )
          ), @ApiResponse(
          description = "Bad Request. Invalid request parameters.",
          responseCode = "400",
          content = @Content(
              mediaType = "text/plain",
              schema = @Schema(implementation = String.class)
          )
      ), @ApiResponse(
          description = "Player not found",
          responseCode = "404",
          content = @Content(
              mediaType = "text/plain",
              schema = @Schema(implementation = String.class)
          )
      )
      }
  )
  @PostMapping
  public ResponseEntity<DiceFullInfoDto> createDice(@Valid @RequestBody DiceCreationDto request) {
    DiceFullInfoDto createdDice = diceService.createDice(request);
    return ResponseEntity.ok(createdDice);
  }

  @Operation(
      summary = "Get a Dice",
      description = "Fetch a Dice based on the provided ID"
  )
  @Parameter(
      name = "id",
      description = "The ID of the Dice to be fetched",
      schema = @Schema(implementation = UUID.class)
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successfully fetched Dice information",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = DiceFullInfoDto.class)
              )
          ), @ApiResponse(
          description = "Dice not found",
          responseCode = "404",
          content = @Content(
              mediaType = "text/plain",
              schema = @Schema(implementation = String.class)
          )
      )
      }
  )
  @GetMapping("/{id}")
  public ResponseEntity<DiceFullInfoDto> getDice(@PathVariable UUID id) {
    DiceFullInfoDto requestedDice = diceService.getDice(id);
    return ResponseEntity.ok(requestedDice);
  }

  @Operation(
      summary = "Use a Dice",
      description = "Use a Dice and update durability"
  )
  @Parameter(
      name = "id",
      description = "The ID of the Dice to be used",
      schema = @Schema(implementation = UUID.class)
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successfully used Dice",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = DiceFullInfoDto.class)
              )
          ), @ApiResponse(
          description = "Bad Request. Invalid request parameters.",
          responseCode = "400",
          content = @Content(
              mediaType = "text/plain",
              schema = @Schema(implementation = String.class)
          )
      ), @ApiResponse(
          description = "Dice not found",
          responseCode = "404",
          content = @Content(
              mediaType = "text/plain",
              schema = @Schema(implementation = String.class)
          )
      )
      }
  )
  @PutMapping("/{id}")
  public ResponseEntity<DiceFullInfoDto> useDice(@PathVariable UUID id) {
    DiceFullInfoDto usedDice = diceService.useDice(id);
    return ResponseEntity.ok(usedDice);
  }

  @Operation(
      summary = "Delete a Dice",
      description = "Remove a Dice from the system"
  )
  @Parameter(
      name = "id",
      description = "The ID of the Dice to be removed",
      schema = @Schema(implementation = UUID.class)
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successfully deleted Dice",
              responseCode = "204"
          ), @ApiResponse(
          description = "Dice not found",
          responseCode = "404"
      )
      }
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDice(@PathVariable UUID id) {
    diceService.deleteDice(id);
    return ResponseEntity.noContent().build();
  }
}