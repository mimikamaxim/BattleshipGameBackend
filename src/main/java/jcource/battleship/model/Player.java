package jcource.battleship.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@Table(name = "Player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "username")
    private String username;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "email")
    @Email
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

}
