// Förbättringar i HighScoreManager.java
import java.io.*;
import java.nio.file.*;

public class HighScoreManager {
    private static final String HIGHSCORE_FILE = "highscore.txt";

    public static int loadHighScore() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(HIGHSCORE_FILE)));
            return Integer.parseInt(content.trim());
        } catch (IOException | NumberFormatException e) {
            return 0; // Ingen highscore finns, returnera 0
        }
    }

    public static void saveHighScore(int score) {
        try (PrintWriter out = new PrintWriter(new FileWriter(HIGHSCORE_FILE))) {
            out.println(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

