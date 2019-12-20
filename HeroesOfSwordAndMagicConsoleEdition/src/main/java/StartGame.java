import engine.GameEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartGame {
    public static void main(String[] args) throws InterruptedException {
        GameEngine gameEngine = new GameEngine();
        gameEngine.startGame();
    }
}