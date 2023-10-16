package jcource.battleship.gameCore;

import jcource.battleship.gameCore.ships.OneDeckShip;
import jcource.battleship.gameCore.ships.Ship;
import jcource.battleship.gameCore.ships.ShipOrientation;
import jcource.battleship.gameCore.ships.TwoDeckShip;

public class PG {
    public static void main(String[] args) throws Exception {
        GameFieldPoint gameFieldPoint = new GameFieldPoint(5, 5);
        System.out.println("origin is "+gameFieldPoint);
//        System.out.println(new Ship.getNeighbours(gameFieldPoint));
        System.out.println();
        TwoDeckShip test1 = new TwoDeckShip(gameFieldPoint,ShipOrientation.VERTICAL);
        System.out.println(test1.getLockedCells());
        System.out.println(test1.getShipCells());
    }
}
