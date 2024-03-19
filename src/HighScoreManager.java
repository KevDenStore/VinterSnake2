import java.io.*;
import java.nio.file.*;

public class HighScoreManager { // HighScoreManager hanterar sparande och laddning av högsta poäng
    private static final String HIGHSCORE_FILE = "highscore.txt"; // Filnamnet där den högsta poängen sparas

    public static int loadHighScore() { // Laddar den högsta poängen från filen
        try {
            String content = new String(Files.readAllBytes(Paths.get(HIGHSCORE_FILE))); // Läser filens innehåll
            return Integer.parseInt(content.trim()); // Omvandlar texten till ett heltal och returnerar det
        } catch (IOException | NumberFormatException e) { // Fångar eventuella fel
            return 0; // Returnerar 0 om ingen poäng finns eller ett fel inträffar
        }
    }

    public static void saveHighScore(int score) { // Sparar en ny högsta poäng till filen
        try (PrintWriter out = new PrintWriter(new FileWriter(HIGHSCORE_FILE))) {
            out.println(score); // Skriver poängen till filen
        } catch (IOException e) { // Fångar och loggar eventuella IO-fel
            e.printStackTrace();
        }
    }
}
