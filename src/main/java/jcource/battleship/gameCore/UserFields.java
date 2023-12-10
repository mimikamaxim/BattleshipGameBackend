package jcource.battleship.gameCore;

import jakarta.persistence.*;
import jcource.battleship.model.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UserFields {
    @Id
    private int id;
    @OneToOne
    Player user;
    @OneToOne
    final PlayerGameField playerGameField = new PlayerGameField();
    @OneToOne
    final EnemyGameField enemyGameField = new EnemyGameField();
    public UserFields (Player user) {
        this.user= user;
    }
}
