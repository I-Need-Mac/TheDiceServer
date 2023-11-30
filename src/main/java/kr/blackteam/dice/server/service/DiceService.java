package kr.blackteam.dice.server.service;

import java.util.UUID;
import kr.blackteam.dice.server.dto.DiceCreationDto;
import kr.blackteam.dice.server.dto.DiceFullInfoDto;
import kr.blackteam.dice.server.exception.DiceDurabilityException;
import kr.blackteam.dice.server.exception.DiceNotFoundException;
import kr.blackteam.dice.server.exception.PlayerNotFoundException;
import kr.blackteam.dice.server.model.Dice;
import kr.blackteam.dice.server.model.Player;
import kr.blackteam.dice.server.repository.DiceRepository;
import kr.blackteam.dice.server.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DiceService {

  private final DiceRepository diceRepository;
  private final PlayerRepository playerRepository;

  @Transactional
  public DiceFullInfoDto createDice(DiceCreationDto request) {
    Player player = playerRepository.findById(request.getPlayerId())
        .orElseThrow(() -> new PlayerNotFoundException(request.getPlayerId()));
    // @formatter:off
    Dice dice = Dice.builder()
        .maxDurability(request.getMaxDurability())
        .currentDurability(request.getCurrentDurability())
        .grade(request.getGrade())
        .type(request.getType())
        .player(player)
        .build();
    // @formatter:on
    return DiceFullInfoDto.fromEntity(diceRepository.save(dice));
  }

  @Transactional(readOnly = true)
  public DiceFullInfoDto getDice(UUID id) {
    Dice dice = diceRepository.findById(id)
        .orElseThrow(() -> new DiceNotFoundException(id));
    return DiceFullInfoDto.fromEntity(dice);
  }

  @Transactional
  public DiceFullInfoDto useDice(UUID id) {
    Dice dice = diceRepository.findById(id)
        .orElseThrow(() -> new DiceNotFoundException(id));
    reduceDurability(dice);
    return DiceFullInfoDto.fromEntity(diceRepository.save(dice));
  }

  public void reduceDurability(Dice dice) {
    if (dice.getCurrentDurability() >= 1) {
      dice.setCurrentDurability(dice.getCurrentDurability() - 1);
    } else {
      throw new DiceDurabilityException("Dice durability is less than one");
    }
  }

  @Transactional
  public void deleteDice(UUID id) {
    if (diceRepository.existsById(id)) {
      diceRepository.deleteById(id);
    } else {
      throw new DiceNotFoundException(id);
    }
  }
}
