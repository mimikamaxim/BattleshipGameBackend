package jcource.battleship.controller;

import jcource.battleship.gameCore.PlayerGameField;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/game/play/api")
public class GameController {

    @GetMapping("")
    public ResponseEntity<HttpStatus> play(){
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
