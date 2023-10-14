package jcource.battleship.gameCore.ships;

import jcource.battleship.gameCore.Exeptions.IllegalShipPositionException;
import jcource.battleship.gameCore.GameFieldPoint;

public class FourDeckShip extends Ship{
    FourDeckShip (GameFieldPoint anchor, ShipOrientation orientation) throws IllegalShipPositionException {
        this.orientation = orientation;
        if (orientation == ShipOrientation.VERTICAL)
            if (anchor.getX()>7) throw new IllegalShipPositionException(); else this.anchor = anchor;
        else
            if (anchor.getY()>7) throw new IllegalShipPositionException(); else this.anchor = anchor;
    }

    @Override
    public String toString() {
        return "FourDeckShip{" +
                "anchor=" + anchor +
                ", orientation=" + orientation +
                '}';
    }
}
