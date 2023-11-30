package kr.blackteam.dice.server.controller;

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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

  private final PlayerService playerService;

  @PostMapping
  public ResponseEntity<PlayerFullInfoDto> createPlayer(@RequestBody PlayerCreationDto request) {
    PlayerFullInfoDto createdPlayer = playerService.createPlayer(request);
    return ResponseEntity.ok(createdPlayer);
  }

  @GetMapping
  public ResponseEntity<List<PlayerFullInfoDto>> getAllPlayers() {
    return ResponseEntity.ok(playerService.getAllPlayers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlayerFullInfoDto> getPlayer(@PathVariable UUID id) {
    PlayerFullInfoDto requestedPlayer = playerService.getPlayer(id);
    return ResponseEntity.ok(requestedPlayer);
  }

  @GetMapping("/{id}/dices")
  public ResponseEntity<List<DiceBasicInfoDto>> getDices(@PathVariable UUID id) {
    List<DiceBasicInfoDto> dices = playerService.getDices(id);
    return ResponseEntity.ok(dices);
  }

  @PutMapping
  public ResponseEntity<PlayerFullInfoDto> updateNamePlayer(
      @RequestBody PlayerFullInfoDto request) {
    PlayerFullInfoDto updatedPlayer = playerService.updatePlayer(request);
    return ResponseEntity.ok(updatedPlayer);
  }

  @PatchMapping("/{id}/name")
  public ResponseEntity<PlayerFullInfoDto> updateNamePlayer(@PathVariable UUID id,
      @RequestBody PlayerNameUpdateDto request) {
    PlayerFullInfoDto updatedPlayer = playerService.updatePlayer(id, request);
    return ResponseEntity.ok(updatedPlayer);
  }

  @PatchMapping("/{id}/email")
  public ResponseEntity<PlayerFullInfoDto> updateNamePlayer(@PathVariable UUID id,
      @RequestBody PlayerEmailUpdateDto request) {
    PlayerFullInfoDto updatedPlayer = playerService.updatePlayer(id, request);
    return ResponseEntity.ok(updatedPlayer);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePlayer(@PathVariable UUID id) {
    playerService.deletePlayer(id);
    return ResponseEntity.noContent().build();
  }
}