package jcource.battleship.gameCore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jcource.battleship.gameCore.Exeptions.OutOfBoundsPointArgumentException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameFieldPoint implements Comparable<GameFieldPoint> {
    @Id
    private int id;
    private static final byte SIZE_UP = 10;
    private static final byte SIZE_BOTTOM = 1;
    private int x = 0;
    private int y = 0;

    public GameFieldPoint(int x, int y) throws OutOfBoundsPointArgumentException {
        if (x >= SIZE_BOTTOM && x <= SIZE_UP) this.x = x;
        else throw new OutOfBoundsPointArgumentException("Arguments must be [" + SIZE_BOTTOM + ";" + SIZE_UP + "]");
        if (y >= SIZE_BOTTOM && y <= SIZE_UP) this.y = y;
        else throw new OutOfBoundsPointArgumentException("Arguments must be [" + SIZE_BOTTOM + ";" + SIZE_UP + "]");
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

    @Override
    public int compareTo(GameFieldPoint o) {
        return equals(o) ? 0 : 1;
    }
}
