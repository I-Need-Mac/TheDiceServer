package kr.blackteam.dice.server.dto;

import java.util.UUID;
import kr.blackteam.dice.server.model.Dice;
import kr.blackteam.dice.server.model.DiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiceFullInfoDto {

  private UUID id;
  private int maxDurability;
  private int currentDurability;
  private int grade;
  private DiceType type;
  private UUID playerId;

  public static DiceFullInfoDto fromEntity(Dice dice) {
    // @formatter:off
    return DiceFullInfoDto.builder()
        .id(dice.getId())
        .maxDurability(dice.getMaxDurability())
        .currentDurability(dice.getCurrentDurability())
        .grade(dice.getGrade())
        .type(dice.getType())
        .playerId(dice.getPlayer().getId())
        .build();
    // @formatter:on
  }
}
