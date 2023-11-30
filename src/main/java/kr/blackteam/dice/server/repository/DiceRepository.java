package kr.blackteam.dice.server.repository;

import java.util.UUID;
import kr.blackteam.dice.server.model.Dice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiceRepository extends JpaRepository<Dice, UUID> {

}
