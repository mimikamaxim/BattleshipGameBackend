package jcource.battleship.gameCore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class FieldPointBoolean {
    @Id
    private int id;
    @OneToOne
    GameFieldPoint gameFieldPoint;

    @OneToOne
    boolean bool;

    public FieldPointBoolean(GameFieldPoint it, boolean b) {
        gameFieldPoint = it;
        bool = b;
    }
}
