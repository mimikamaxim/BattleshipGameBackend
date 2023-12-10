package jcource.battleship.gameCore.ships;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jcource.battleship.gameCore.Exeptions.IllegalShipPositionException;
import jcource.battleship.gameCore.GameFieldPoint;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class TwoDeckShip extends Ship {
    @Id
    private int id;
    public TwoDeckShip(GameFieldPoint anchor, ShipOrientation orientation) throws IllegalShipPositionException {
        this.orientation = orientation;
        if (orientation == ShipOrientation.VERTICAL)
            if (anchor.getY() > 8)
                throw new IllegalShipPositionException("The ship leaves the boundary field along the Y axis");
            else this.anchor = anchor;
        else if (anchor.getX() > 8)
            throw new IllegalShipPositionException("The ship leaves the boundary field along the X axis");
        else this.anchor = anchor;
        if (orientation == ShipOrientation.VERTICAL) {
            this.shipCells.add(anchor);
            this.shipCells.add(new GameFieldPoint(anchor.getX(), anchor.getY() + 1));
        } else {
            this.shipCells.add(anchor);
            this.shipCells.add(new GameFieldPoint(anchor.getX() + 1, anchor.getY()));
        }
        setNeighbours();
    }

    @Override
    public String toString() {
        return "TwoDeckShip{" +
                "anchor=" + anchor +
                ", orientation=" + orientation +
                '}';
    }
}
