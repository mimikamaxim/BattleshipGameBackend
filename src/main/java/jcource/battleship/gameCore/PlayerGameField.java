package jcource.battleship.gameCore;

import jcource.battleship.gameCore.GameObjectEnums.PlayerFieldState;
import jcource.battleship.gameCore.GameObjectEnums.ShotResult;
import jcource.battleship.gameCore.ships.ShipContainer;
import jcource.battleship.gameCore.ships.ShipContainerBuilder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerGameField {
    public PlayerGameField() {
    }

    @Getter
    private ShipContainer shipContainer;
    @Getter
    private PlayerFieldState playerFieldState = PlayerFieldState.INIT_STAGE;

    /**
     * set true if its hit false if its alive
     */
    private final HashMap<GameFieldPoint, Boolean> shipHitCells = new HashMap<>();

    private ShipContainerBuilder shipContainerBuilder = new ShipContainerBuilder();
    final ArrayList<GameFieldPoint> enemyMissShots = new ArrayList<>();
    final ArrayList<GameFieldPoint> enemyHitShots = new ArrayList<>();

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
        if (playerFieldState == PlayerFieldState.READY_FOR_GAME)
            if (shipHitCells.containsKey(target)) {
                shipHitCells.replace(target, true);
                if (checkItsLose()) return ShotResult.HIT_AND_LOSE;
                else return ShotResult.HIT;
            } else {
                enemyMissShots.add(target);
                return ShotResult.MISS;
            }
        else throw new IllegalStateException("Player game field is not accomplished");
    }

    private boolean checkItsLose() {
        AtomicBoolean res = new AtomicBoolean(true);
        shipHitCells.values().forEach(it -> {
            if (!it) {
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
        shipContainer = shipContainerBuilder.build();
        shipContainer.getShipsCells().forEach(it -> shipHitCells.put(it, false));
        playerFieldState = PlayerFieldState.READY_FOR_GAME;
        shipContainerBuilder = null;
    }

    /**
     * Use this method for get <code>ShipContainerBuilder</code> and set ships with it.
     *
     * @return ShipContainerBuilder
     * @throws IllegalStateException - if game is started and no way to change player field
     */
    public ShipContainerBuilder getShipContainerBuilder() throws IllegalStateException {
        if (playerFieldState == PlayerFieldState.READY_FOR_GAME)
            throw new IllegalStateException("Init complete, the builder is unavailable");
        else return this.shipContainerBuilder;
    }
}
