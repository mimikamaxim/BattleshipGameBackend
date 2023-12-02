package jcource.battleship.gameCore;

import lombok.Getter;

import java.util.ArrayList;

public class EnemyGameField {
    @Getter
    private final ArrayList<GameFieldPoint> missedShots = new ArrayList<>();
    @Getter
    private final ArrayList<GameFieldPoint> hitShots = new ArrayList<>();

    public void addMissShot(GameFieldPoint shot) {
        missedShots.add(shot);
    }

    public void addHitShot(GameFieldPoint shot) {
        hitShots.add(shot);
    }
}
