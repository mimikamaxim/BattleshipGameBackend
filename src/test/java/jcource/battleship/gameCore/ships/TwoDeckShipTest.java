package jcource.battleship.gameCore.ships;

import jcource.battleship.gameCore.Exeptions.IllegalShipPositionException;
import jcource.battleship.gameCore.GameFieldPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TwoDeckShipTest {
    @Test
    public void creationClassTest() throws IllegalShipPositionException {
        GameFieldPoint origin = new GameFieldPoint(1,1);
        TwoDeckShip test1 = new TwoDeckShip(origin,ShipOrientation.VERTICAL);
        Assertions.assertEquals(test1.anchor, origin);
        ArrayList<GameFieldPoint> listShip = new ArrayList<>();
        listShip.add(origin);
        listShip.add(new GameFieldPoint(1,2));
        Assertions.assertEquals(test1.getShipCells(),listShip);
    }

    //todo написать другие тесты
}