import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {
    //skärm variablar
    private static final long serialVersionUID = 1L;
    private static int unitSize = 18;  // Default unit size

    public static int getUnitSize() {
        return unitSize;
    }

    public static void setUnitSize(int newSize) {
        unitSize = newSize;
    }

    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    static final int UNIT_SIZE = 18;
    static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
// ny objekt
    final int x [] = new int[NUMBER_OF_UNITS]; //håller x,y kordinater för kroppen av ormen
    final int y [] = new int[NUMBER_OF_UNITS];
    //ormens längd

    char Direction = 'D';
    boolean running = false;
    Random random;
    Timer timer;
    int lenght = 5;
    int foodEaten;
    int foodX;
    int foodY;

    Panel() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        play();
    }
//ny objekt
    public void play() {
        addFood();
        running = true;

        timer = new Timer(70, this);
        timer.start();
    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        draw(graphics);

    }
    //ny objekt
    public void move (){
        for (int i = lenght; i > 0; i--) {
            //flytta ormen en enhet mot önskad rikting för att spela
            x[i] = x[i-1];
            y[i] = y[i-1];
        } //flytta med hjälp av vilken pilknapp som klickas
        if (Direction == 'L') {
            x[0] = x[0] - UNIT_SIZE;
        } else if (Direction =='R') {
            x[0] = x[0] + UNIT_SIZE;
        } else if (Direction =='U') {
            y[0] = y[0] - UNIT_SIZE;
        } else {
            y[0] = y[0] + UNIT_SIZE;
        }
    }
    //objekt controller
    public void checkFood() {
        if (x[0] == foodX && y[0] == foodY) {
            lenght++;
            foodEaten++;
            addFood();
        }
    }

    public void draw(Graphics graphics) {
        if (running) {
            graphics.setColor(new Color(255, 47, 0));
            graphics.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            graphics.setColor(Color.WHITE);
            graphics.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);

            for (int i = 1; i < lenght; i++) {
                graphics.setColor(new Color(12, 232, 114));
                graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Comic sans", Font.ITALIC, 20));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2, graphics.getFont().getSize());
        } else {
            GameOver(graphics);
        }
    }
    public void addFood(){
        foodX = random.nextInt((int) (WIDTH / UNIT_SIZE))*UNIT_SIZE;
        foodY = random.nextInt((int) (HEIGHT / UNIT_SIZE))*UNIT_SIZE;
    }
    public void checkHit() { //håller koll om man krockar in i kroppen
        for (int i = lenght; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }
        //håller koll om man krockar in i väggen
        if (x[0] < 0 || x[0] > WIDTH  || y[0] < 0 ||y[0] > HEIGHT) {
            running = false;
        }
        if(!running) {
            timer.stop();
        }
    }
    public void GameOver(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Comic sans", Font.ITALIC, 40));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("GAME OVER", (WIDTH - metrics.stringWidth("GAME OVER")) / 2, HEIGHT/ 2);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Comic sans", Font.ITALIC, 20));
       // FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2, graphics.getFont().getSize());
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (running) {
            move();
            checkFood();
            checkHit();
        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (Direction != 'R'){
                    Direction = 'L';
                }
                break;

                case KeyEvent.VK_RIGHT:
                    if (Direction != 'L'){
                    Direction = 'R';
                        break;
                    }
                case KeyEvent.VK_UP:
                    if (Direction != 'D'){
                    Direction = 'U';
                }
                break;
                case KeyEvent.VK_DOWN:
                    if (Direction != 'U'){
                    Direction = 'D';
                }
                break;
            }
        }
    }
}
/*Thread gameThread;

    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true)
        ; //öka prestandan :)

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null) {
            update();
            repaint(); //medans spelet kör så kallar loopen update->repaint->update->repaint->update->repaint->

        }

    }
    //updatering (ändringar konstant)
public void update() {

}
    public void paintComponent(Graphics g) { //för att rita in i panelen
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //graphics2d klassen extendar vanliga graphics klassen för mer sofistikerad kontrol över estetiken(2d har mer funktioner)
        g2.setColor ()
    }*/