package kr.blackteam.dice.server.dto;

import java.util.UUID;
import kr.blackteam.dice.server.model.DiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiceCreationDto {

  private int maxDurability;
  private int currentDurability;
  private int grade;
  private DiceType type;
  private UUID playerId;
}
