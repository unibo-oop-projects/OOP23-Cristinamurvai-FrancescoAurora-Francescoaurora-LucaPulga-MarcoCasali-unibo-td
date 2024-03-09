package main.java.it.unibo;

import it.unibo.controller.Controller;
import it.unibo.controller.ControllerImpl;

public final class GameLauncher{
    private GameLauncher(){}

    public static void main(final String ... args){
        Controller controller = new ControllerImpl();
        controller.startGame();
    }
}