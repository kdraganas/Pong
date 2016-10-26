import java.awt.*;
import javax.swing.*;

public class Ball extends JPanel{
    private static final int DIAMETER = 15;

    int x = 0, y = 0;	//initailization of the x and y
    int xa = 1, ya = 1;
    private Game game;

    public Ball(Game game) {
        this.game = game;
    } //overloaded constructor of Ball

    void move() throws InterruptedException {
        boolean changeDirection = true;
        if (game.racquet.getScore()==3 || game.racquett.getScore()==3) {
            game.gameOver();
        } //since the game is modified into somewhat a race of the players who gets three points first, so this will be the check
        else if (x + xa < 0){
            xa = game.speed;
        }
        else if (x + xa > game.getWidth() - DIAMETER){
            xa = -game.speed;
        }
        else if (y + ya < 0){
            game.racquet.setScore();
            game.racquet.setSize(60,5);
            game.racquett.setSize(60,5);
            game.speed++;
            x = 150;
            y = 200;
        }
        else if (y + ya > game.getHeight() - DIAMETER){
            game.racquett.setScore();
            game.racquett.setSize(60,5);
            game.racquet.setSize(60,5);
            game.speed++;
            x = 150;
            y = 200;
        }
        else if (collision()){ //to check whether there exist a collision on the lower racquet and the ball
            ya = -game.speed;
            y = game.racquet.getTopY() - DIAMETER;
        }
        else if (collisionn()){	////to check whether there exist a collision on the upper racquet and the ball
            ya = game.speed;
            y = game.racquett.getBotY() + DIAMETER;
        }
        else
            changeDirection = false;

        String win = new String();

        if (changeDirection)
            Sound.BALL.play(); //this plays when the ball is hit which means it has change its direction
        x = x + xa;
        y = y + ya;
    }

    private boolean collision() { //returns a value of 1 if the lower racquet and the ball has collide
        return game.racquet.getBounds().intersects(getBounds());
    }

    private boolean collisionn() {	//returns a value of 1 if the upper racquet and the ball has collide
        return game.racquett.getBounds().intersects(getBounds());
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.PINK); //setting the color of the ball
        g.fillOval(x, y, DIAMETER, DIAMETER); //filling the ball with the color set through the parameters given
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER); //returns the boundaries of the ball
    }
}
