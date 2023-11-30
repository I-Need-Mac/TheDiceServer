package kr.blackteam.dice.server.repository;

import java.util.UUID;
import kr.blackteam.dice.server.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {

}
