import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameController implements Initializable 
{
    @FXML
    private Canvas gameCanvas;
    private long lastPowerTime = 0;
    private static final long POWER_COOLDOWN = 200_000_000; 

    private EscapeRoomGame game;
    private AnimationTimer gameLoop;
    private boolean aPressed = false;
    private boolean dPressed = false;
    private String playerName = "Player";
    private boolean navigatedToStartAfterGameOver = false;
    // We'll store the sign: -1.0 for Left, 1.0 for Right .
    private double lastMoveDirection = 1.0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        game = new EscapeRoomGame();
        game.setPlayerName(playerName);

        //Here i attached the key-listeners to the whole scene to properly detect the key pressings
        //and releasings

        gameCanvas.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(this::handleKeyPressed);
                newScene.setOnKeyReleased(this::handleKeyReleased);
            }
        });

        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnMouseClicked(e -> gameCanvas.requestFocus());
        gameCanvas.requestFocus();

        // Game loop
        gameLoop = new AnimationTimer() 
        {
            @Override
            public void handle(long now) 
            {
                // Handle continuous movement
                if (!game.gameWon && !game.isGameOver()) 
                {
                    if (aPressed) game.player.moveLeft();
                    if (dPressed) game.player.moveRight();
                }

                game.update();

                // Render
                game.render(gameCanvas.getGraphicsContext2D());

                if (game.isGameOver() && !navigatedToStartAfterGameOver)
                {
                    navigatedToStartAfterGameOver = true;
                    EsscapeRoomApp.showStartScene();
                }
            }
        };
        gameLoop.start();
    }

private void handleKeyPressed(KeyEvent e) 
{
    KeyCode code = e.getCode();
    
    switch (code) 
    {
        case A:
        case LEFT:
            aPressed = true;
            break;
        case D:
        case RIGHT:
            dPressed = true;

            break;
        case S: 
            if (!game.isGameOver()) 
            {
                lastMoveDirection = lastMoveDirection * -1.0; 
            }
            break;
        case W:
        case UP:
            if (!game.isGameOver()) 
            {
                game.player.jump();
            }
            break;
        case SPACE:
            if (game.gameWon) 
            {
                if (game.getStarsEarnedThisLevel() <= 1) 
                {
                    game.initLevel();
                } 
                else 
                {
                    game.level++;
                    game.PLAYER_MOVE_SPEED+=0.2;
                    game.ENEMY_BASE_SPEED+=0.3; 
                    game.initLevel();
                }
            } 
            else 
            {
                if (!game.isGameOver()) 
                {
                    long now = System.nanoTime();
                    if (now - lastPowerTime >= POWER_COOLDOWN) 
                    {
                        game.player.usePower(lastMoveDirection);                          
                        lastPowerTime = now;
                    }
                }
            }
            break;
        case E:
            for (EscapeRoomGame.ColoredBlock cb : game.coloredBlocks) 
            {
                if (!cb.absorbed && Math.abs(game.player.x - cb.x) < 80 && Math.abs(game.player.y - cb.y) < 80) 
                {
                    game.player.absorbColor(cb.color);
                    cb.absorbed = true;
                    break;
                }
            }
            break;
        case L:
            if (e.isShiftDown()) 
            {
                game.grantBonusLife();
            }
            break;
    }
}

    private void handleKeyReleased(KeyEvent e) 
    {
        KeyCode code = e.getCode();
        
        switch (code) 
        {
            case A:
            case LEFT:
                aPressed = false;
                break;
            case D:
            case RIGHT:
                dPressed = false;
                break;
        }
    }

    public void setPlayerName(String name) 
    {
        if (name != null && !name.trim().isEmpty()) 
        {
            this.playerName = name.trim();
        }
        if (game != null) 
        {
            game.setPlayerName(this.playerName);
        }
    }
}

