package jcource.battleship.gameCore.ships;

import jcource.battleship.gameCore.Exeptions.IllegalShipPositionException;
import jcource.battleship.gameCore.GameFieldPoint;

public class TwoDeckShip extends Ship {
    TwoDeckShip(GameFieldPoint anchor, ShipOrientation orientation) throws IllegalShipPositionException {
        this.orientation = orientation;
        if (orientation == ShipOrientation.VERTICAL)
            if (anchor.getY() > 8) throw new IllegalShipPositionException(); else this.anchor = anchor;
        else
            if (anchor.getX() > 8) throw new IllegalShipPositionException(); else this.anchor = anchor;
    }

    @Override
    public String toString() {
        return "TwoDeckShip{" +
                "anchor=" + anchor +
                ", orientation=" + orientation +
                '}';
    }
}
