// Huvudklassen som startar spelet
public class Main {
    public static void main(String[] args) {     //metoddeklaration i Java som tjänar som startpunkten för programkörning
        //public: Gör metoden tillgänglig överallt så att JVM kan köra den.
        //static: Metoden tillhör klassen, inte ett objekt, så ingen instans behövs för att köra den.
        //void: Metoden returnerar inget.
        //main: Den metod JVM använder för att starta programmet.
        //String[] args: Tar emot argument från kommandoraden som programmet kan använda.

        new GameFrame();         // Skapar ett nytt fönster för spelet, vilket också startar spelet

    }
}
