package jcource.battleship.gameCore.ships;

import jcource.battleship.gameCore.Exeptions.OutOfBoundsPointArgumentException;
import jcource.battleship.gameCore.GameFieldPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public abstract class Ship {
    protected GameFieldPoint anchor;
    protected ShipOrientation orientation;
    protected TreeSet<GameFieldPoint> lockedCells = new TreeSet<>();
    protected ArrayList<GameFieldPoint> shipCells = new ArrayList<>();

    protected void setNeighbours() {
        for (GameFieldPoint shipCell : shipCells) {
            int x = shipCell.getX();
            int y = shipCell.getY();
            for (int i = y - 1; i <= y + 1; i++)
                for (int j = x - 1; j <= x + 1; j++)
                    try {
                        GameFieldPoint neighbour = new GameFieldPoint(j, i);
                        lockedCells.add(neighbour);
                    } catch (OutOfBoundsPointArgumentException ignored) {
                    }
        }
    }

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

    public TreeSet<GameFieldPoint> getLockedCells() {
        return lockedCells;
    }

    public List<GameFieldPoint> getShipCells() {
        return shipCells;
    }
}
