import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Game extends JPanel {

    int speed = 1;	//variable speed initialized to 1
    Ball ball = new Ball(this);	//created an instance of a ball
    Racquet racquet = new Racquet(this,115,323,60,5);	//created an instance of a racquet which is receives the following parameter: game, location with reagrds to x, location with regards to y, width, lastly height
    Racquet racquett = new Racquet(this,115,35,60,5);	//created an instance of a racquet which is receives the following parameter: game, location with reagrds to x, location with regards to y, width, lastly height which the two of them differ with the location of their y's

    public Game() {
        addKeyListener(new KeyListener() {	//new instance of listener is registered in the Jpanel using the addKeyListener(Keylistener listener) method
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) { //would help us determnine whether the key pressed is released
                racquet.keyReleased(e);
                racquett.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT)	//if the left arrow key is pressed is then the lower racquet would move to the left
                    racquet.xa = -speed;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)	//if the right arrow key is pressed is then the lower racquet would move to the right
                    racquet.xa = speed;
                if (e.getKeyCode() == KeyEvent.VK_A)	//if the key A is pressed is then the upper racquet would move to the left
                    racquett.xa = -speed;
                if (e.getKeyCode() == KeyEvent.VK_D)	//if the key D is pressed is then the upper racquet would move to the right
                    racquett.xa = speed;

                //power-ups
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    speed++;
                if (e.getKeyCode() == KeyEvent.VK_W)
                    speed++;
            }
        });
        setFocusable(true); //for the JPanel object to receive keyboard notifications it is necessary to include the instruction setFocusable(true), which allows us to receive the focus
        Sound.BACK.loop();	//for the implementation of the background sound
    }

    private void move() throws InterruptedException {
        ball.move();	//for the ball to move
        racquet.move();	//for the lower racquet to move
        racquett.move();	//for the upper racquet to move
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //cleans the screen, if commented out then we can see its trace
        g.setColor(Color.black); //setting the background into back
        g.fillRect(0, 0, 300, 400); //filling it up

        Graphics2D g2d = (Graphics2D) g; //Graphics2D is a library where there are a lot of functions we can find. Function paint passed a parameter in a form of Graphics and is typecasted into the form of Graphics2D
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	//makes the borders of the figures smoother

        ball.paint(g2d);
        racquet.paint(g2d);
        racquett.paint(g2d);

        g2d.setColor(Color.pink); //setting the color
        g2d.setFont(new Font("Century Gothic", Font.BOLD, 20)); //setting the font
        g2d.drawString("P1: "+String.valueOf(racquet.getScore()), 230, 350); //displays the score of the first player which is the lower racquet
        g2d.drawString("P2: "+String.valueOf(racquett.getScore()), 10, 30); //displays the score of the second player which is the upper racquet
    }

    public void gameOver() throws InterruptedException {
        Sound.BACK.stop(); //background music is stopped since the game is over
        Sound.GAMEOVER.play(); //game over music is played

        JOptionPane.showMessageDialog(this, "PLAYER 1: " + racquet.getScore() + " | PLAYER 2: " + racquett.getScore(),"GAME OVER", JOptionPane.YES_NO_OPTION);

        int reply = JOptionPane.showConfirmDialog(this,"Do you wish to continue?","GAME OVER",JOptionPane.YES_NO_OPTION); //variable reply receives the value the player has chosen, whether yes or no
        if(reply == JOptionPane.YES_OPTION){ //if reply equals to yes, then the game would restart
            newGame();
        }
        else
            System.exit(ABORT); //if no, then the game will exit
    }

    public static void newGame() throws InterruptedException {
        JFrame frame = new JFrame("Mini Tennis"); //This code would create a window named "Mini Tennis" in the size of 300 pixels by 300 pixels
        Game game = new Game();
        frame.add(game);
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//If this line is not included then most probably, in closing the window the program won't finish and will continue running taking up too much space
        frame.setVisible(true); //But Mini Tennis window would not be visible until setVisible(true).

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);	//tells the processor that the thread which is being run must sleep for 10 milliseconds
        }
    }

    public static void main(String[] args) throws InterruptedException {
        newGame();
    }
}