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
public class DiceBasicInfoDto {

  private UUID id;
  private int maxDurability;
  private int currentDurability;
  private int grade;
  private DiceType type;

  public static DiceBasicInfoDto fromEntity(Dice dice) {
    // @formatter:off
    return DiceBasicInfoDto.builder()
        .id(dice.getId())
        .maxDurability(dice.getMaxDurability())
        .currentDurability(dice.getCurrentDurability())
        .grade(dice.getGrade())
        .type(dice.getType())
        .build();
    // @formatter:on
  }
}
