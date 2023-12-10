package jcource.battleship.repository;

import jcource.battleship.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Optional<Player> findByUsername(String username);
    Optional<Player> findByEmail(String email);
}
