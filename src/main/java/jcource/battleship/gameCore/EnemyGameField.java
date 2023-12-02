package jcource.battleship.gameCore;

import lombok.Getter;

import java.util.ArrayList;

public class EnemyGameField {
    @Getter
    public ArrayList<GameFieldPoint> missedShots = new ArrayList<>();
    @Getter
    public ArrayList<GameFieldPoint> hitShots = new ArrayList<>();

    public void addMissShot(GameFieldPoint shot) {
        missedShots.add(shot);
    }

    public void addHitShot(GameFieldPoint shot) {
        hitShots.add(shot);
    }
}
