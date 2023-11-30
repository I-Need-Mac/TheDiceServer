package kr.blackteam.dice.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dices")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Dice extends BaseEntity {

  @Column(name = "max_durability")
  private int maxDurability;

  @Column(name = "current_durability")
  private int currentDurability;

  private int grade;

  private DiceType type;

  @ManyToOne
  @JoinColumn(name = "player_id")
  private Player player;
}