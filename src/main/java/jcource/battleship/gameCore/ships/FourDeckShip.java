package jcource.battleship.gameCore.ships;

import jcource.battleship.gameCore.Exeptions.IllegalShipPositionException;
import jcource.battleship.gameCore.GameFieldPoint;

public class FourDeckShip extends Ship {
    FourDeckShip(GameFieldPoint anchor, ShipOrientation orientation) throws IllegalShipPositionException {
        this.orientation = orientation;
        if (orientation == ShipOrientation.VERTICAL)
            if (anchor.getX() > 7)
                throw new IllegalShipPositionException("The ship leaves the boundary field along the X axis");
            else this.anchor = anchor;
        else if (anchor.getY() > 7)
            throw new IllegalShipPositionException("The ship leaves the boundary field along the Y axis");
        else this.anchor = anchor;
        if (orientation == ShipOrientation.VERTICAL) {
            this.shipCells.add(anchor);
            this.shipCells.add(new GameFieldPoint(anchor.getX(), anchor.getY() + 1));
            this.shipCells.add(new GameFieldPoint(anchor.getX(), anchor.getY() + 2));
            this.shipCells.add(new GameFieldPoint(anchor.getX(), anchor.getY() + 3));
        } else {
            this.shipCells.add(anchor);
            this.shipCells.add(new GameFieldPoint(anchor.getX() + 1, anchor.getY()));
            this.shipCells.add(new GameFieldPoint(anchor.getX() + 2, anchor.getY()));
            this.shipCells.add(new GameFieldPoint(anchor.getX() + 3, anchor.getY()));
        }
        setNeighbours();
    }

    @Override
    public String toString() {
        return "FourDeckShip{" +
                "anchor=" + anchor +
                ", orientation=" + orientation +
                '}';
    }
}
