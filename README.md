## Escape Room V2.0 (JavaFX)

A JavaFX escape-room style platformer with color-based powers and multiple levels.

### Controls
- `A / Left Arrow` – Move left  
- `D / Right Arrow` – Move right  
- `W / Up Arrow` – Jump  
- `SPACE` – Shoot or trigger the currently held power  
- `E` – Absorb the closest color block in range  
- `SHIFT + L` – Secret back door that grants one extra life (up to three)

## Building and Running
Requirements: Java 11+ and Maven 3.6+

```bash
# Compile the project
mvn clean compile

# Launch the JavaFX application
mvn javafx:run
```

## Project Layout

- `pom.xml`  
  - Declares JavaFX modules (`controls`, `fxml`, `media`) and the OpenJFX Maven
    plugin so `mvn javafx:run` works out-of-the-box.
  - Uses a simplified layout: Java sources in `src/main`, resources in `src/resources`.

- `src/main/EsscapeRoomApp.java`  
  - JavaFX `Application` entry point (also used by `mvn javafx:run`).  
  - Shows a splash screen, then a start screen with player name input and a
    leaderboard, and finally loads the main game scene.

- `src/main/GameController.java`  
  - FXML controller for `game.fxml`.  
  - Owns the `Canvas`, `EscapeRoomGame` instance, and the `AnimationTimer` loop.  
  - Handles input (movement keys, powers, absorb, bonus life) and forwards
    actions to the game engine.

- `src/main/EscapeRoomGame.java`  
  - Core gameplay engine: levels, enemies, color blocks, projectiles, particles,
    platforms, goal, lives, scoring, and leaderboard.  
  - Renders everything to a `GraphicsContext` and updates game state each frame.

- `src/main/ResourceManager.java`  
  - Singleton loader for sprites and sounds from `src/resources`.  
  - Provides drawing fallbacks when image files are missing and helper methods
    to play/stop sounds.

- `src/main/ImageGenerator.java`  
  - Optional utility to procedurally regenerate all PNG art assets under
    `src/main/resources/images/**`.

- `src/resources/game.fxml`  
  - FXML layout that declares a single `Canvas` and binds it to
    `GameController`.

- `src/resources/images/**`  
  - Generated PNG art assets grouped by entity type (players, enemies, blocks,
    platforms, projectiles, particles, goals, UI).

- `src/resources/sounds/`  
  - Drop-in folder for `.wav` or `.mp3` effects (jump, shoot, hit, etc.). Sounds
    are optional; missing files are ignored safely.

## Troubleshooting
- **No sounds**: ensure WAV files exist under `src/resources/sounds/`.  
- **Canvas not focused**: click the window once to regain keyboard focus if
  keys don’t seem responsive.

Enjoy building new levels and tweaking the Escape Room experience!
