package jcource.battleship.gameCore.ships;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jcource.battleship.gameCore.GameFieldPoint;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class OneDeckShip extends Ship {
    @Id
    private int id;
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
