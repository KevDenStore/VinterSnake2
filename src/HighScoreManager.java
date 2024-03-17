import java.io.BufferedReader; // Importerar klassen för att läsa text från en teckenbaserad inmatningsström.
import java.io.BufferedWriter; // Importerar klassen för att skriva text till en teckenbaserad utmatningsström.
import java.io.FileReader; // Importerar klassen för att läsa filer.
import java.io.FileWriter; // Importerar klassen för att skriva till filer.
import java.io.IOException; // Importerar klassen för att hantera IO-undantag.

public class HighScoreManager {
    private static final String HIGHSCORE_FILE = "highscore.txt"; // Konstant för namnet på filen där högsta poängen sparas.

    public static int loadHighScore() { // Statisk metod för att läsa den högsta poängen från fil.
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGHSCORE_FILE))) {
            // Skapar en BufferedReader för att läsa från filen.
            String line = reader.readLine(); // Läser första raden från filen.
            return Integer.parseInt(line.trim()); // Omvandlar texten till ett heltal och returnerar det.
        } catch (IOException | NumberFormatException e) { // Fångar eventuella IO- eller formateringsfel.
            System.err.println("Failed to load high score: " + e.getMessage()); // Skriver ut felmeddelande till standardfelutmatningen.
            return 0; // Returnerar 0 om något går fel.
        }
    }

    public static void saveHighScore(int score) { // Statisk metod för att spara en ny högsta poäng i filen.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORE_FILE))) {
            // Skapar en BufferedWriter för att skriva till filen.
            writer.write(String.valueOf(score)); // Skriver poängen som en sträng till filen.
        } catch (IOException e) { // Fångar eventuella IO-fel.
            System.err.println("Failed to save high score: " + e.getMessage()); // Skriver ut felmeddelande till standardfelutmatningen.
        }
    }
}
