//package jcource.battleship.gameCore;
//
//import jcource.battleship.gameCore.Exeptions.IllegalShipPositionException;
//import jcource.battleship.gameCore.ships.FourDeckShip;
//import jcource.battleship.gameCore.ships.ShipOrientation;
//import org.junit.jupiter.api.Test;
//
//class PlayerFieldFormatterTest {
//
//    @Test
//    void getEnemyField() throws IllegalShipPositionException {
//        UserFields field = new UserFields(new IUser() {
//            @Override
//            public int hashCode() {
//                return super.hashCode();
//            }
//        });
//        field.enemyGameField.addHitShot(new GameFieldPoint(1, 1));
//        field.enemyGameField.addMissShot(new GameFieldPoint(10, 10));
//        System.out.println(PlayerFieldFormatter.getEnemyField(field));
//        field.playerGameField.getShipContainer().addFourDeckShip(new FourDeckShip(
//                new GameFieldPoint(5, 5), ShipOrientation.VERTICAL));
//        field.playerGameField.enemyHit(new GameFieldPoint(5,5));
//        System.out.println(PlayerFieldFormatter.getPlayerField(field));
//    }
//
//    @Test
//    void getPlayerField() {
//    }
//}