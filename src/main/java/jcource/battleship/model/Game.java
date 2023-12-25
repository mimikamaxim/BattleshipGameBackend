package jcource.battleship.model;

import jakarta.persistence.*;
import jcource.battleship.gameCore.GameObject;
import jcource.battleship.gameCore.GameObjectEnums;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "game")
    private List<GameObject> gameObjects;
}
