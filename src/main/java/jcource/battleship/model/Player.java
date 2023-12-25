package jcource.battleship.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jcource.battleship.gameCore.IUser;
import lombok.*;

@Data
@Entity
@Table(name = "Player")
public class Player implements IUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Getter
    @Size(min = 2, max = 50)
    @Column(name = "username")
    private String username;

    @NotNull
    @Getter
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Game game;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
