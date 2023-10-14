package jcource.battleship.gameCore.ships;

import jcource.battleship.gameCore.GameFieldPoint;

public class OneDeckShip extends Ship {
    OneDeckShip(GameFieldPoint anchor, ShipOrientation orientation) {
        this.orientation = orientation;
        this.anchor = anchor;
    }

    @Override
    public String toString() {
        return "OneDeckShip{" +
                "anchor=" + anchor +
                ", orientation=" + orientation +
                '}';
    }
}
