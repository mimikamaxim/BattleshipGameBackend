package jcource.battleship.gameCore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
@Entity
public class EnemyGameField {
    @Id
    private int id;
    @OneToMany
    @Getter
    private final ArrayList<GameFieldPoint> missedShots = new ArrayList<>();
    @OneToMany
    @Getter
    private final ArrayList<GameFieldPoint> hitShots = new ArrayList<>();

    public void addMissShot(GameFieldPoint shot) {
        missedShots.add(shot);
    }

    public void addHitShot(GameFieldPoint shot) {
        hitShots.add(shot);
    }

    public ArrayList<GameFieldPoint> getHitShots() {
        return hitShots;
    }

    public ArrayList<GameFieldPoint> getMissedShots() {
        return missedShots;
    }
}
