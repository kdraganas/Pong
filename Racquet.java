import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Racquet extends JPanel {
    int y = 0;
    int width = 0;
    int height = 0;

    int x = 0;
    int xa = 0;

    private Game game;
    private int score = 0;

    public Racquet(Game game, int x, int y, int width, int height) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    } //an overloaded contructer of a racquet

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - width)
            x = x + xa;
    } //for the racquet to move sideways

    public void paint(Graphics2D g) {
        g.setColor(Color.white); //setting its color to black
        g.drawRect(x, y, width, height); //outlining the rectangle with the color set to it
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }	//returns the boundaries of the rectangle

    public int getScore() {
        return score;
    } //returns the score of the players

    public void setScore() {
        score+=1;
    }	//setting the score of the players

    public int getTopY() {
        return y - height;
    }

    public int getBotY() {
        return y;
    }
}