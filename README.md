## Escape Room V2.0 (JavaFX)

A JavaFX escape-room style platformer with color-based powers and multiple levels.

### Gameplay
<p align="center">
  <img src="https://raw.githubusercontent.com/0xGhost63/Escape-Room-V2.0/main/src/resources/images/gameplay_screenshots/Screenshot%20from%202025-12-14%2011-46-42.png" width="700"/>
</p>

### Game Won
<p align="center">
  <img src="https://raw.githubusercontent.com/0xGhost63/Escape-Room-V2.0/main/src/resources/images/gameplay_screenshots/Screenshot%20from%202025-12-14%2011-48-17.png" width="700"/>
</p>


### Controls
- `A / Left Arrow` – Move left  
- `D / Right Arrow` – Move right  
- `W / Up Arrow` – Jump  
- `SPACE` – Shoot or trigger the currently held power  
- `E` – Absorb the closest color block in range  
- `S` – Switch firing direction  
- `SHIFT + L` – Secret back door that grants one extra life (up to three)

## Building and Running
Requirements: Java 11+ and Maven 3.6+

```bash
# Compile the project
mvn clean compile

# Launch the JavaFX application
mvn javafx:run
