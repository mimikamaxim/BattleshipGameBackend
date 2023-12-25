package jcource.battleship.gameCore;

import jakarta.persistence.*;
import jcource.battleship.gameCore.GameObjectEnums.PlayerFieldState;
import jcource.battleship.gameCore.GameObjectEnums.ShotResult;
import jcource.battleship.gameCore.ships.ShipContainer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Entity
public class PlayerGameField {
    @Id
    private int id;

    public PlayerGameField() {
    }


    @Getter
    @OneToOne
    private final ShipContainer shipContainer = new ShipContainer();

    @Getter
    @OneToOne
    private PlayerFieldState playerFieldState = PlayerFieldState.INIT_STAGE;

    /**
     * set true if its hit false if its alive
     */
    @OneToMany
    private ArrayList<FieldPointBoolean> shipHitCells = new ArrayList<>();

    @Getter
    @OneToMany
    private final ArrayList<GameFieldPoint> enemyMissShots = new ArrayList<>();

    /**
     * Checks enemy shot and adds the shot to relevant <code>PlayerGameField</code> list.
     *
     * @param target it's enemy shot.
     * @return <code>true</code> if enemy shot is hit a player ship.
     * <code>false</code> if the shot is miss.
     * @throws IllegalStateException if the player game field is not accomplished
     */
    //TODO механика повторного удара по уже отмеченной клетке
    public ShotResult enemyHit(GameFieldPoint target) throws IllegalStateException {
        if (playerFieldState == PlayerFieldState.READY_FOR_GAME) {
            AtomicBoolean res = new AtomicBoolean(false);
            shipHitCells.forEach( it -> {
                if (it.gameFieldPoint.equals(target)) {
                    res.set(true);
                    it.bool = true;
                }
            });
            if (!res.get()){
                enemyMissShots.add(target);
                return ShotResult.MISS;
            }
            if (checkItsLose()) return ShotResult.HIT_AND_LOSE;
            else return ShotResult.HIT;
        }
        else throw new IllegalStateException("Player game field is not accomplished");
    }

    public ArrayList<GameFieldPoint> getShipsPoints() {
        return shipContainer.getShipsCells();
    }

    public ArrayList<GameFieldPoint> getEnemyHitShots() {
        ArrayList<GameFieldPoint> res = new ArrayList<>();
        shipHitCells.forEach( it -> {
            if (it.bool) res.add(it.gameFieldPoint);
        });
        return res;
    }

    private boolean checkItsLose() {
        AtomicBoolean res = new AtomicBoolean(true);
        shipHitCells.forEach(it -> {
            if (!it.bool) {
                res.set(false);
            }
        });
        return res.get();
    }

    /**
     * Change instance of the class state to in game state
     *
     * @throws IllegalStateException - if instance is not ready for start game
     * @throws NullPointerException  - if you trying start game twice or more
     */
    public void startGame() throws IllegalStateException, NullPointerException {
        if (shipContainer.isComplete()) {
            shipContainer.getShipsCells().forEach(it -> shipHitCells.add(new FieldPointBoolean(it,false)));
            playerFieldState = PlayerFieldState.READY_FOR_GAME;
        } else {
            throw new IllegalStateException("Not all ships were defined");
        }
    }

    public ArrayList<GameFieldPoint> getEnemyMissShots() {
        return enemyMissShots;
    }

    public ShipContainer getShipContainer() {
        return shipContainer;
    }

    public PlayerFieldState getPlayerFieldState() {
        return playerFieldState;
    }

//    /**
//     * Use this method for get <code>ShipContainerBuilder</code> and set ships with it.
//     *
//     * @return ShipContainerBuilder
//     * @throws IllegalStateException - if game is started and no way to change player field
//     */
//    public ShipContainerBuilder getShipContainerBuilder() throws IllegalStateException {
//        if (playerFieldState == PlayerFieldState.READY_FOR_GAME)
//            throw new IllegalStateException("Init complete, the builder is unavailable");
//        else return this.shipContainerBuilder;
//    }
}
