package jcource.battleship.gameCore.ships;

import jcource.battleship.gameCore.Exeptions.IllegalShipPositionException;
import jcource.battleship.gameCore.GameFieldPoint;

public class ThreeDeckShip extends Ship {
    ThreeDeckShip(GameFieldPoint anchor, ShipOrientation orientation) throws IllegalShipPositionException {
        this.orientation = orientation;
        if (orientation == ShipOrientation.VERTICAL)
            if (anchor.getY() > 9) throw new IllegalShipPositionException(); else this.anchor = anchor;
        else
            if (anchor.getX() > 9) throw new IllegalShipPositionException(); else this.anchor = anchor;
    }

    @Override
    public String toString() {
        return "ThreeDeckShip{" +
                "anchor=" + anchor +
                ", orientation=" + orientation +
                '}';
    }
}
