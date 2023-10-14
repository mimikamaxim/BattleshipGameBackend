package jcource.battleship.gameCore.ships;

import jcource.battleship.gameCore.GameFieldPoint;

import java.util.Objects;

abstract class Ship {
    protected GameFieldPoint anchor;
    protected ShipOrientation orientation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship ship)) return false;
        return Objects.equals(anchor, ship.anchor) && orientation == ship.orientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(anchor, orientation);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "anchor=" + anchor +
                ", orientation=" + orientation +
                '}';
    }

    public GameFieldPoint getAnchor() {
        return anchor;
    }

    public ShipOrientation getOrientation() {
        return orientation;
    }
}
