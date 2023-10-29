package jcource.battleship.gameCore;

import jcource.battleship.gameCore.Exeptions.IllegalShipPositionException;
import jcource.battleship.gameCore.ships.*;

public class GameObject {
    UserFields user1Fields;
    UserFields user2Fields;
    UserInitState userInitState;

    GameState gameState = GameState.USERS_NOT_READY;

    public GameObject() {
        userInitState = UserInitState.USERS_NOT_SET;
    }

    public GameObject(IUser user) {
        user1Fields = new UserFields(user);
        userInitState = UserInitState.USER2_NOT_SET;
    }

    public GameObject(IUser user1, IUser user2) {
        user1Fields = new UserFields(user1);
        user2Fields = new UserFields(user2);
        userInitState = UserInitState.USERS_INIT_COMPLETE;
    }

    public UserInitState addUser(IUser user) {
        if (user1Fields == null) {
            user1Fields = new UserFields(user);
            return userInitState = UserInitState.USER2_NOT_SET;
        }
        if (user2Fields == null) {
            user2Fields = new UserFields(user);
            return userInitState = UserInitState.USERS_INIT_COMPLETE;
        }
        throw new IllegalStateException("Both users already set");
    }

    public enum UserInitState {
        USERS_NOT_SET, USER2_NOT_SET, USERS_INIT_COMPLETE
//        SHIPS_NOT_INITIALIZED_USER1, SHIPS_NOT_INITIALIZED_USER2, SHIPS_NOT_INITIALIZED_BOTH,
//        READY_FOR_START_GAME,
//        GAME_STARTED
    }

    public enum GameState {
        USERS_NOT_READY, USER1_NOT_READY, USER2_NOT_READY, USERS_READY, GAME_STARTED;

        @Override
        public String toString() {
            switch (this) {
                case USERS_NOT_READY -> {
                    return "users not ready";
                }
                case USER1_NOT_READY -> {
                    return "user1 not ready;";
                }
                case USER2_NOT_READY -> {
                    return "user2 not ready";
                }
                case USERS_READY -> {
                    return "users ready";
                }
                case GAME_STARTED -> {
                    return "game started";
                }
            }
            return null;
        }
    }

    public boolean userShot(int x, int y, IUser user) throws IllegalArgumentException, IllegalStateException {
        if (gameState == GameState.GAME_STARTED) {
            GameFieldPoint shot = new GameFieldPoint(x, y);
            boolean result = getEnemyUserFields(user).playerGameField.enemyHit(shot);
            if (result) getThisUserFields(user).enemyGameField.addHitShot(shot);
            else getThisUserFields(user).enemyGameField.addMissShot(shot);
            return result;
        } else throw new IllegalStateException("Game is not started");
    }

    public void setShip(int x, int y, boolean isHorizontal, int capacity, IUser user)
            throws IllegalShipPositionException {
        ShipOrientation shipOrientation;
        GameFieldPoint shipAnchor = new GameFieldPoint(x, y);
        ShipContainerBuilder userShipBuilder = getThisUserFields(user).playerGameField.getShipContainerBuilder();
        if (isHorizontal) shipOrientation = ShipOrientation.HORIZONTAL;
        else shipOrientation = ShipOrientation.VERTICAL;
        switch (capacity) {
            case 1 -> {
                userShipBuilder
                        .addOneDeckShip(new OneDeckShip(shipAnchor));
            }
            case 2 -> {
                userShipBuilder
                        .addTwoDeckShip(new TwoDeckShip(shipAnchor, shipOrientation));
            }
            case 3 -> {
                userShipBuilder.
                        addThreeDeckShip(new ThreeDeckShip(shipAnchor, shipOrientation));
            }
            case 4 -> {
                userShipBuilder.
                        addFourDeckShip(new FourDeckShip(shipAnchor, shipOrientation));
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

//    public EnemyGameField getEnemyGameField(IUser user) {
//        return getThisUserFields(user).enemyGameField;
//    }
//
//    public PlayerGameField getPlayerGameField(IUser user) {
//        return getThisUserFields(user).playerGameField;
//    }

    public void userReady(IUser user) {
        UserFields userFields = getThisUserFields(user);
        if (user == user1Fields.user)
            if (gameState == GameState.USER1_NOT_READY) {
                gameState = GameState.USERS_READY;
                startGame();
            } else gameState = GameState.USER2_NOT_READY;
        else if (user == user2Fields.user)
            if (gameState == GameState.USER2_NOT_READY) {
                gameState = GameState.USERS_READY;
                startGame();
            } else gameState = GameState.USER1_NOT_READY;
    }

    private void startGame() {
        if (gameState == GameState.USERS_READY) {
            user1Fields.playerGameField.startGame();
            user2Fields.playerGameField.startGame();
            gameState = GameState.GAME_STARTED;
            return;
        }
        throw new IllegalStateException("Can't run game, because " + gameState.toString());
    }
}