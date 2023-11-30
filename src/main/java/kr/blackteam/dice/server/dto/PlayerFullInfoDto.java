package kr.blackteam.dice.server.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import kr.blackteam.dice.server.model.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerFullInfoDto {

  private UUID id;
  private String name;
  private String email;
  private LocalDateTime created;
  private LocalDateTime modified;

  public static PlayerFullInfoDto fromEntity(Player entity) {
    // @formatter:off
    return PlayerFullInfoDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .email(entity.getEmail())
        .created(entity.getCreatedAt())
        .modified(entity.getModifiedAt())
        .build();
    // @formatter:on
  }
}
