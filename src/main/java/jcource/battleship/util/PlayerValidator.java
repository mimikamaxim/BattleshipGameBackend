package jcource.battleship.util;

import jcource.battleship.model.Player;
import jcource.battleship.security.PlayerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlayerValidator implements Validator {

    private final PlayerDetailsService playerDetailsService;

    @Autowired
    public PlayerValidator(PlayerDetailsService playerDetailsService) {
        this.playerDetailsService = playerDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Player.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Player player = (Player) target;

        try{
            playerDetailsService.loadUserByUsername(player.getUsername());
        }catch (UsernameNotFoundException ignored){
            return;
        }

        errors.rejectValue("username", "", "Игрок с таким никнеймом уже существует");
    }
}
