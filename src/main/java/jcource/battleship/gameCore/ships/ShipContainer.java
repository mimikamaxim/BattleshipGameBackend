package jcource.battleship.gameCore.ships;

import jcource.battleship.gameCore.GameFieldPoint;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShipContainer {
    OneDeckShip[] oneDeckShip = new OneDeckShip[4];
    TwoDeckShip[] twoDeckShip = new TwoDeckShip[3];
    ThreeDeckShip[] threeDeckShips = new ThreeDeckShip[2];
    FourDeckShip fourDeckShip;

    private int counterOneDeckShip = 0;
    private int counterTwoDeckShip = 0;
    private int counterThreeDeckShip = 0;

    public ShipContainer() {
    }


    public void addOneDeckShip(OneDeckShip oneDeckShip) throws IllegalStateException {
        if (isUnavailablePosition(oneDeckShip))
            throw new IllegalStateException("The ship touches another ship safe area");
        if (counterOneDeckShip < 4) {
            this.oneDeckShip[counterOneDeckShip++] = oneDeckShip;
        } else throw new IllegalStateException("All one-deck ships is already set");
    }

    public void addTwoDeckShip(TwoDeckShip twoDeckShip) throws IllegalStateException {
        if (isUnavailablePosition(twoDeckShip))
            throw new IllegalStateException("The ship touches another ship safe area");
        if (counterTwoDeckShip < 3) {
            this.twoDeckShip[counterTwoDeckShip++] = twoDeckShip;
        } else throw new IllegalStateException("All two-deck ships is already set");
    }

    public void addThreeDeckShip(ThreeDeckShip threeDeckShip) throws IllegalStateException {
        if (isUnavailablePosition(threeDeckShip))
            throw new IllegalStateException("The ship touches another ship safe area");
        if (counterThreeDeckShip < 2) {
            this.threeDeckShips[counterThreeDeckShip++] = threeDeckShip;
        } else throw new IllegalStateException("All three-deck ships is already set");
    }

    public void addFourDeckShip(FourDeckShip fourDeckShip) throws IllegalStateException {
        if (isUnavailablePosition(fourDeckShip))
            throw new IllegalStateException("The ship touches another ship safe area");
        if (this.fourDeckShip != null) this.fourDeckShip = fourDeckShip;
        else throw new IllegalStateException("Four-deck ship is already set");
    }

    public Ship[] getShipsArray() {
        return new Ship[]{
                oneDeckShip[0],
                oneDeckShip[1],
                oneDeckShip[2],
                oneDeckShip[3],
                twoDeckShip[0],
                twoDeckShip[1],
                twoDeckShip[2],
                threeDeckShips[0],
                threeDeckShips[1],
                fourDeckShip
        };
    }

    public boolean isComplete() {
        for (Ship ship : getShipsArray()) {
            if (ship == null) return false;
        }
        return true;
    }

    private boolean isUnavailablePosition(Ship ship) {
        AtomicBoolean result = new AtomicBoolean(false);
        ship.getShipCells().forEach(cell -> {
            for (GameFieldPoint cell2 : getCurrentLockedCells())
                if (cell == cell2) {
                    result.set(true);
                    return;
                }
        });
        return result.get();
    }

    private ArrayList<GameFieldPoint> getCurrentLockedCells() {
        ArrayList<GameFieldPoint> result = new ArrayList<>();
        for (Ship ship : getShipsArray()) {
            if (ship != null)
                result.addAll(ship.lockedCells);
        }
        return result;
    }

    private ArrayList<GameFieldPoint> getShipsCells() {
        ArrayList<GameFieldPoint> result = new ArrayList<>();
        for (Ship ship : getShipsArray()) {
            result.addAll(ship.shipCells);
        }
        return result;
    }

    public boolean isHitEnemyShot (GameFieldPoint target) {
        for (GameFieldPoint gfp : getShipsCells())
            if (gfp==target) return true;
        return false;
    }
}
