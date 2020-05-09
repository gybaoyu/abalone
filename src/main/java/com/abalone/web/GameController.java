package com.abalone.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 集成了google的小恐龙
 */
@Controller
public class GameController {
    @GetMapping("/game")
    public String game(){
        return "game";
    }
}
