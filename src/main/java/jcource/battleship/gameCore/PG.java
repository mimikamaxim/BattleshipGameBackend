package jcource.battleship.gameCore;

import jcource.battleship.gameCore.Exeptions.OutOfBoundsPointArgumentException;

public class PG {
    public static void main(String[] args) throws OutOfBoundsPointArgumentException {
        GameFieldPoint gameFieldPoint = new GameFieldPoint(11, 2);
        System.out.println(gameFieldPoint);
    }
}
