package jcource.battleship.gameCore.ships;

import jcource.battleship.gameCore.GameFieldPoint;

public class OneDeckShip extends Ship {
    public OneDeckShip(GameFieldPoint anchor) {
        this.orientation = ShipOrientation.VERTICAL;
        this.anchor = anchor;
        this.shipCells.add(anchor);
        setNeighbours();
    }

    @Override
    public String toString() {
        return "OneDeckShip{" +
                "anchor=" + anchor +
                ", orientation=" + orientation +
                '}';
    }
}
