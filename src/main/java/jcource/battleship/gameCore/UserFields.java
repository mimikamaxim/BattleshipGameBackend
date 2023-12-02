package jcource.battleship.gameCore;

public class UserFields {
    IUser user;
    final PlayerGameField playerGameField = new PlayerGameField();
    final EnemyGameField enemyGameField = new EnemyGameField();
    public UserFields (IUser user) {
        this.user= user;
    }
}
