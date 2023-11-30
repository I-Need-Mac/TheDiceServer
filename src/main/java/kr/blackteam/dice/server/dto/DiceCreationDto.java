package kr.blackteam.dice.server.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import kr.blackteam.dice.server.model.DiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiceCreationDto {

  @Min(
      value = 0,
      message = "Max durability must be zero or positive"
  )
  private int maxDurability;
  @Min(
      value = 0,
      message = "Current durability must be zero or positive"
  )
  private int currentDurability;
  @Min(
      value = 0,
      message = "Grade must be zero or positive"
  )
  private int grade;
  @NotNull(message = "Dice type must not be null")
  private DiceType type;
  @NotNull(message = "Player ID must not be null")
  private UUID playerId;
}