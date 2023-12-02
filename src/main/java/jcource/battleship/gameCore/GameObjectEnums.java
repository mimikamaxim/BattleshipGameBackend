package jcource.battleship.gameCore;

public class GameObjectEnums {
    public enum GameState {
        USERS_UNDEFINED, USER2_UNDEFINED, USERS_DEFINED, GAME_STARTED, GAME_FINISHED;

        @Override
        public String toString() {
            switch (this) {
                case USERS_UNDEFINED -> {
                    return "users undefined";
                }
                case USER2_UNDEFINED -> {
                    return "user 2 undefined";
                }
                case USERS_DEFINED -> {
                    return "users defined";
                }
                case GAME_STARTED -> {
                    return "game started";
                }
                case GAME_FINISHED -> {
                    return "game finished";
                }
            }
            return null;
        }
    }

    public enum PlayerFieldState {
        INIT_STAGE, READY_FOR_GAME
    }

    public enum ShotResult {
        MISS, HIT, HIT_AND_LOSE
    }
}
