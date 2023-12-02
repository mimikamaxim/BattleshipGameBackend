package jcource.battleship.gameCore;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

public class PlayerFieldFormatter {
    public static String getEnemyField (UserFields userFields) {
        LinkedHashMap<GameFieldPoint,EnemyGameFieldPointTypes> field = new LinkedHashMap<>();
        for (int i=1; i<=10;i++)
            for (int j=1; j<=10; j++)
                field.put(new GameFieldPoint(j,i),EnemyGameFieldPointTypes.CLEAR);
        userFields.enemyGameField.getHitShots().forEach(
                it -> field.replace(it,EnemyGameFieldPointTypes.HIT)
        );
        userFields.enemyGameField.getMissedShots().forEach(
                it -> field.replace(it,EnemyGameFieldPointTypes.MISS)
        );
        StringBuilder res = new StringBuilder();
        field.forEach(new BiConsumer<GameFieldPoint, EnemyGameFieldPointTypes>() {
            @Override
            public void accept(GameFieldPoint gameFieldPoint, EnemyGameFieldPointTypes enemyGameFieldPointTypes) {
                switch (enemyGameFieldPointTypes){
                    case CLEAR -> {
                        res.append("⬜");
                    }
                    case MISS -> {
                        res.append("✖");
                    }
                    case HIT -> {
                        res.append("✴");
                    }
                }
                if (gameFieldPoint.getX()==10) res.append('\n');
            }
        });
        return res.toString();
    }

    public static String getPlayerField (UserFields userFields) {
        LinkedHashMap<GameFieldPoint,PlayerGameFieldPointTypes> field = new LinkedHashMap<>();
        for (int i=1; i<=10;i++)
            for (int j=1; j<=10; j++)
                field.put(new GameFieldPoint(j,i),PlayerGameFieldPointTypes.CLEAR);
        userFields.playerGameField.getShipContainer().getShipsCells().forEach(
                it -> field.replace(it,PlayerGameFieldPointTypes.SHIP)
        );
        userFields.playerGameField.getEnemyMissShots().forEach(
                it -> field.replace(it,PlayerGameFieldPointTypes.MISS)
        );
        userFields.playerGameField.getEnemyHitShots().forEach(
                it -> field.replace(it,PlayerGameFieldPointTypes.HIT_SHIP)
        );
        StringBuilder res = new StringBuilder();
        field.forEach((gameFieldPoint, playerGameFieldPointTypes) -> {
            switch (playerGameFieldPointTypes) {
                case CLEAR -> {
                    res.append("⬜");
                }
                case MISS -> {
                    res.append("✖");
                }
                case SHIP -> {
                    res.append("⬛");
                }
                case HIT_SHIP -> {
                    res.append("✴");
                }
            }
            if (gameFieldPoint.getX() == 10) res.append('\n');
        });
        return res.toString();
    }

    private enum EnemyGameFieldPointTypes {
        CLEAR, MISS, HIT
    }

    private enum PlayerGameFieldPointTypes {
        CLEAR, MISS, SHIP, HIT_SHIP
    }
}
