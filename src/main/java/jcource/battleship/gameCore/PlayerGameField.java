package jcource.battleship.gameCore;

import jcource.battleship.gameCore.ships.ShipContainer;
import jcource.battleship.gameCore.ships.ShipContainerBuilder;
import lombok.Getter;

import java.util.ArrayList;

public class PlayerGameField {
    public PlayerGameField() {
    }

    @Getter
    private ShipContainer shipContainer;

    private PlayerFieldState playerFieldState = PlayerFieldState.INIT_STAGE;

    public enum PlayerFieldState {
        INIT_STAGE,
        GAME_STARTED
    }

    private ShipContainerBuilder shipContainerBuilder = new ShipContainerBuilder();
    final ArrayList<GameFieldPoint> enemyMissShots = new ArrayList<>();
    final ArrayList<GameFieldPoint> enemyHitShots = new ArrayList<>();

    /**
     * Checks enemy shot and adds the shot to relevant <code>PlayerGameField</code> list.
     * @param target it's enemy shot.
     * @return <code>true</code> if enemy shot is hit a player ship.
     * <code>false</code> if the shot is miss.
     * @throws IllegalStateException if the player game field is not accomplished
     */
    public boolean enemyHit(GameFieldPoint target) throws IllegalStateException {
        if (playerFieldState == PlayerFieldState.GAME_STARTED)
            if (shipContainer.isHitEnemyShot(target)) {
                enemyHitShots.add(target);
                return true;
            } else {
                enemyMissShots.add(target);
                return false;
            }
        else throw new IllegalStateException("Player game field is not accomplished");
    }

    /**
     * Change instance of the class state to in game state
     *
     * @throws IllegalStateException - if instance is not ready for start game
     */
    public void startGame() throws IllegalStateException {
        shipContainer = shipContainerBuilder.build();
        playerFieldState = PlayerFieldState.GAME_STARTED;
        shipContainerBuilder = null;
    }

    /**
     * Use this method for get <code>ShipContainerBuilder</code> and set ships with it.
     *
     * @return ShipContainerBuilder
     * @throws IllegalStateException - if game is started and no way to change player field
     */
    public ShipContainerBuilder getShipContainerBuilder() throws IllegalStateException {
        if (playerFieldState == PlayerFieldState.GAME_STARTED)
            throw new IllegalStateException("Game started, the builder is unavailable");
        else return this.shipContainerBuilder;
    }
}
