package kr.blackteam.dice.server.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import kr.blackteam.dice.server.dto.PlayerFullInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "players")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Player extends BaseEntity {

  private String name;
  private String email;
  @OneToMany(
      mappedBy = "player",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private List<Dice> dices = new ArrayList<>();
}
