package jcource.battleship.gameCore;

import jcource.battleship.gameCore.Exeptions.OutOfBoundsPointArgumentException;

import java.util.Objects;

public class GameFieldPoint {
    private final int x;
    private final int y;

    GameFieldPoint(int x, int y) throws OutOfBoundsPointArgumentException {
        if (x > 0 && x < 11) this.x = x;
        else throw new OutOfBoundsPointArgumentException();
        if (y > 0 && y < 11) this.y = y;
        else throw new OutOfBoundsPointArgumentException();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameFieldPoint that)) return false;
        return getX() == that.getX() && getY() == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "GameFieldPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
