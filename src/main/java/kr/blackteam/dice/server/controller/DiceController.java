package kr.blackteam.dice.server.controller;

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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/dices")
public class DiceController {

  private final DiceService diceService;

  @PostMapping
  public ResponseEntity<DiceFullInfoDto> createDice(@RequestBody DiceCreationDto request) {
    DiceFullInfoDto createdDice = diceService.createDice(request);
    return ResponseEntity.ok(createdDice);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DiceFullInfoDto> getDice(@PathVariable UUID id) {
    DiceFullInfoDto requestedDice = diceService.getDice(id);
    return ResponseEntity.ok(requestedDice);
  }

  @PutMapping("/{id}")
  public ResponseEntity<DiceFullInfoDto> useDice(@PathVariable UUID id) {
    DiceFullInfoDto usedDice = diceService.useDice(id);
    return ResponseEntity.ok(usedDice);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDice(@PathVariable UUID id) {
    diceService.deleteDice(id);
    return ResponseEntity.noContent().build();
  }
}
