package kr.blackteam.dice.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import kr.blackteam.dice.server.dto.DiceBasicInfoDto;
import kr.blackteam.dice.server.dto.PlayerCreationDto;
import kr.blackteam.dice.server.dto.PlayerEmailUpdateDto;
import kr.blackteam.dice.server.dto.PlayerFullInfoDto;
import kr.blackteam.dice.server.dto.PlayerNameUpdateDto;
import kr.blackteam.dice.server.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Player",
    description = "API functions related to player actions"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

  private final PlayerService playerService;

  @Operation(summary = "Create a new Player")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Information needed to create a new Player",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = PlayerCreationDto.class)
      )
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successfully created new Player",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = PlayerFullInfoDto.class)
              )
          )
      }
  )
  @PostMapping
  public ResponseEntity<PlayerFullInfoDto> createPlayer(@RequestBody PlayerCreationDto request) {
    PlayerFullInfoDto createdPlayer = playerService.createPlayer(request);
    return ResponseEntity.ok(createdPlayer);
  }

  @Operation(summary = "Get all Players")
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successfully fetched all Players",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(
                      schema = @Schema(implementation = PlayerFullInfoDto.class)
                  )
              )
          )
      }
  )
  @GetMapping
  public ResponseEntity<List<PlayerFullInfoDto>> getAllPlayers() {
    return ResponseEntity.ok(playerService.getAllPlayers());
  }

  @Operation(summary = "Get a Player")
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successfully fetched Player information",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = PlayerFullInfoDto.class)
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
  @Parameter(
      name = "id",
      schema = @Schema(implementation = UUID.class)
  )
  @GetMapping("/{id}")
  public ResponseEntity<PlayerFullInfoDto> getPlayer(@PathVariable UUID id) {
    PlayerFullInfoDto requestedPlayer = playerService.getPlayer(id);
    return ResponseEntity.ok(requestedPlayer);
  }

  @Operation(summary = "Get a Player's Dices")
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successfully fetched Player's Dices",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = DiceBasicInfoDto.class)
              )
          )
      }
  )
  @Parameter(
      name = "id",
      schema = @Schema(implementation = UUID.class)
  )
  @GetMapping("/{id}/dices")
  public ResponseEntity<List<DiceBasicInfoDto>> getDices(@PathVariable UUID id) {
    List<DiceBasicInfoDto> dices = playerService.getDices(id);
    return ResponseEntity.ok(dices);
  }

  @Operation(summary = "Update a Player")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Information needed to update a Player's Email",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = PlayerFullInfoDto.class)
      )
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successfully updated Player",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = PlayerFullInfoDto.class)
              )
          )
      }
  )
  @PutMapping
  public ResponseEntity<PlayerFullInfoDto> updatePlayer(@RequestBody PlayerFullInfoDto request) {
    PlayerFullInfoDto updatedPlayer = playerService.updatePlayer(request);
    return ResponseEntity.ok(updatedPlayer);
  }

  @Operation(summary = "Update a Player's Name")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Information needed to update a Player's name",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = PlayerNameUpdateDto.class)
      )
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successfully updated Player's Name",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = PlayerFullInfoDto.class)
              )
          )
      }
  )
  @Parameter(
      name = "id",
      schema = @Schema(implementation = UUID.class)
  )
  @PatchMapping("/{id}/name")
  public ResponseEntity<PlayerFullInfoDto> updateNamePlayer(@PathVariable UUID id,
      @RequestBody PlayerNameUpdateDto request) {
    PlayerFullInfoDto updatedPlayer = playerService.updatePlayer(id, request);
    return ResponseEntity.ok(updatedPlayer);
  }

  @Operation(summary = "Update a Player's Email")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Information needed to update a Player's email",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = PlayerEmailUpdateDto.class)
      )
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successfully updated Player's Email",
              responseCode = "200",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = PlayerFullInfoDto.class)
              )
          )
      }
  )
  @Parameter(
      name = "id",
      schema = @Schema(implementation = UUID.class)
  )
  @PatchMapping("/{id}/email")
  public ResponseEntity<PlayerFullInfoDto> updateEmailPlayer(@PathVariable UUID id,
      @RequestBody PlayerEmailUpdateDto request) {
    PlayerFullInfoDto updatedPlayer = playerService.updatePlayer(id, request);
    return ResponseEntity.ok(updatedPlayer);
  }

  @Operation(summary = "Delete a Player")
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successfully deleted Player",
              responseCode = "204"
          )
      }
  )
  @Parameter(
      name = "id",
      schema = @Schema(implementation = UUID.class)
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePlayer(@PathVariable UUID id) {
    playerService.deletePlayer(id);
    return ResponseEntity.noContent().build();
  }
}