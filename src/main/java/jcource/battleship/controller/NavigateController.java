package jcource.battleship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class NavigateController {

    @GetMapping("")
    public String helloPage(){
        return "main/hello";
    }

    @GetMapping("/play")
    public String playGame(){
        return "main/play";
    }
}
