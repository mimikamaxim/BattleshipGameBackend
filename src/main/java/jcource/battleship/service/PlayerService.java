package jcource.battleship.service;

import jcource.battleship.model.Player;
import jcource.battleship.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAllPlayer(){
        return playerRepository.findAll();
    }

    public Optional<Player> findOne(int id){
        return playerRepository.findById(id);

    }

    @Transactional
    public void save(Player player){
        playerRepository.save(player);
    }

    @Transactional
    public void update(int id, Player updatedPlayer){

    }

    public Optional<Player> getPlayerByUsername(String username){
        return playerRepository.findByUsername(username);
    }

    public Optional<Player> getPlayerByEmail(String email){
        return playerRepository.findByEmail(email);
    }

    @Transactional
    public void delete(int id){
        playerRepository.deleteById(id);
    }
}
