package jcource.battleship.gameCore;

import jcource.battleship.gameCore.Exeptions.IllegalShipPositionException;
import jcource.battleship.gameCore.ships.*;
import jcource.battleship.gameCore.GameObjectEnums.*;
import lombok.Getter;

public class GameObject {
    private UserFields user1Fields;
    private UserFields user2Fields;
    @Getter
    private GameState gameState = GameState.USERS_UNDEFINED;

    public GameObject() {
        gameState = GameState.USERS_UNDEFINED;
    }

    public GameObject(IUser user) {
        user1Fields = new UserFields(user);
        gameState = GameState.USER2_UNDEFINED;
    }

    public GameObject(IUser user1, IUser user2) {
        user1Fields = new UserFields(user1);
        user2Fields = new UserFields(user2);
        gameState = GameState.USERS_DEFINED;
    }

    /**
     * Добавляет пользователя для экземпляра игры
     *
     * @param user - Добавляемый пользователь
     * @return - Состояние экземлпяра объекта
     * @throws IllegalStateException - если пользователи уже определены
     */
    public GameState addUser(IUser user) throws IllegalStateException {
        if (user1Fields == null) {
            user1Fields = new UserFields(user);
            return gameState = GameState.USER2_UNDEFINED;
        }
        if (user2Fields == null) {
            user2Fields = new UserFields(user);
            return gameState = GameState.USERS_DEFINED;
        }
        throw new IllegalStateException("Both users already set");
    }

    public ShotResult userShot(int x, int y, IUser user) throws IllegalArgumentException, IllegalStateException {
        if (gameState == GameState.GAME_STARTED) {
            GameFieldPoint shot = new GameFieldPoint(x, y);
            ShotResult result = getEnemyUserFields(user).playerGameField.enemyHit(shot);
            if (result != ShotResult.MISS) getThisUserFields(user).enemyGameField.addHitShot(shot);
            else getThisUserFields(user).enemyGameField.addMissShot(shot);
            return result;
        } else throw new IllegalStateException(gameState.toString());
    }

    public void setShip(int x, int y, boolean isHorizontal, int capacity, IUser user) throws IllegalShipPositionException {
        ShipOrientation shipOrientation;
        GameFieldPoint shipAnchor = new GameFieldPoint(x, y);
        ShipContainer shipContainer = getThisUserFields(user).playerGameField.getShipContainer();
        if (isHorizontal) shipOrientation = ShipOrientation.HORIZONTAL;
        else shipOrientation = ShipOrientation.VERTICAL;
        switch (capacity) {
            case 1 -> {
                shipContainer.addOneDeckShip(new OneDeckShip(shipAnchor));
            }
            case 2 -> {
                shipContainer.addTwoDeckShip(new TwoDeckShip(shipAnchor, shipOrientation));
            }
            case 3 -> {
                shipContainer.addThreeDeckShip(new ThreeDeckShip(shipAnchor, shipOrientation));
            }
            case 4 -> {
                shipContainer.addFourDeckShip(new FourDeckShip(shipAnchor, shipOrientation));
            }
            default -> throw new IllegalArgumentException("Capacity is incorrect");
        }
    }

    private UserFields getThisUserFields(IUser user) {
        if (user == user1Fields.user) return user1Fields;
        if (user == user2Fields.user) return user2Fields;
        throw new IllegalArgumentException("There is no such user in this game instance");
    }

    private UserFields getEnemyUserFields(IUser user) {
        if (user == user1Fields.user) return user2Fields;
        if (user == user2Fields.user) return user1Fields;
        throw new IllegalArgumentException("There is no such user in this game instance");
    }

    //Todo возможно вызывать автоматом если не будет механики изменения поля пока игра не началась
    /**
     * @param user
     * @return - сосояние игры
     */
    public GameState userReady(IUser user) {
        UserFields userFields = getThisUserFields(user);
        userFields.playerGameField.startGame();
        if (user1Fields.playerGameField.getPlayerFieldState() == PlayerFieldState.READY_FOR_GAME
                && user2Fields.playerGameField.getPlayerFieldState() == PlayerFieldState.READY_FOR_GAME)
            gameState = GameState.GAME_STARTED;
        return gameState;
    }
}