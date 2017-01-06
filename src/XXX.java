import com.sun.deploy.panel.JavaPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cole Littrell on 12/5/2016.
 */
public class XXX {
    private static int width = 3, height = 3;
    private static JButton[] spaces;
    private static boolean rightDiagonalWin() { return (spaces[0].getText().equals(spaces[4].getText()) && spaces[4].getText().equals(spaces[8].getText()) && !spaces[0].getText().equals(" ")) ? true :  false;  }
    private static boolean topHorizontalWin()  { return (spaces[0].getText().equals(spaces[1].getText()) && spaces[1].getText().equals(spaces[2].getText()) && !spaces[0].getText().equals(" ")) ? true: false; }
    private static boolean middleHorizontalWin() { return (spaces[3].getText().equals(spaces[4].getText()) && spaces[4].getText().equals(spaces[5].getText()) && !spaces[5].getText().equals(" ")) ? true :  false;  }
    private static boolean bottomHorizontalWin() {return (spaces[6].getText().equals(spaces[7].getText()) && spaces[7].getText().equals(spaces[8].getText()) && !spaces[7].getText().equals(" ")) ? true :  false;  }
    private static boolean leftVerticalWin() { return (spaces[0].getText().equals(spaces[3].getText()) && spaces[3].getText().equals(spaces[6].getText()) && !spaces[0].getText().equals(" ")) ? true :  false;  }
    private static boolean middleVerticalWin() { return (spaces[1].getText().equals(spaces[4].getText()) && spaces[4].getText().equals(spaces[7].getText()) && !spaces[1].getText().equals(" ")) ? true :  false;  }
    private static boolean rightVerticalWin() { return (spaces[2].getText().equals(spaces[5].getText()) && spaces[5].getText().equals(spaces[8].getText()) && !spaces[2].getText().equals(" ")) ? true :  false;  }
    private static boolean leftDiagonalWin() { return (spaces[6].getText().equals(spaces[4].getText()) && spaces[4].getText().equals(spaces[2].getText()) && !spaces[6].getText().equals(" ")) ? true :  false;  }
    public static void main(String[] args) {
        JFrame jf = new JFrame("Title");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(width, height));
        spaces = new JButton[height * width];
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = new TTTButton();
            jp.add(spaces[i]);
        }
        jf.add(jp);
        jf.pack();
        jf.setVisible(true);
    }

    private static class TTTButton extends JButton implements ActionListener {

        private static int turn = 0;

        public TTTButton() {
            super();
            setPreferredSize(new Dimension(100, 100));
            setForeground(Color.BLACK);
            setOpaque(true);
            setFont(new Font("Helvetica", Font.PLAIN, 60));
            setText(" ");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            if (turn % 2 == 0 && getText().equals(" ")) {
                setText("X");
            } else if (turn % 2 == 1 && getText().equals(" ")) {
                setText("O");
            }
            if (isThereAWinner()) {
                showWin();
                shallWePlayAgain();
            } else if (noPlaceLeftToPlay()) {
                shallWePlayAgain();
            } else {
                turn++;
            }
        }
        // looks for a winner
        private boolean isThereAWinner() {
            if (rightDiagonalWin()){
                return true;
            } else if (rightVerticalWin()){
                return true;
            } else if (leftDiagonalWin()){
                return true;
            } else if (leftVerticalWin()) {
                return true;
            }else if (middleHorizontalWin()) {
                return true;
            } else if (middleVerticalWin()){
                return true;
            } else if (topHorizontalWin()){
                return true;
            } else if (bottomHorizontalWin()){
                return true;
            }
            else{
                return false;
            }
        }

        // Check if there is still space to play.
        private boolean noPlaceLeftToPlay() {
            if (turn == width * height - 1) {
                JOptionPane.showMessageDialog(null, "No winner:(");
                return true;
            }
            else {
                return false;
            }
        }

        // Prompt user for a new game. Else exit.
        private void shallWePlayAgain() {
            JOptionPane.showMessageDialog(null, "Shall we play again?");
            newGame();
        }

        // Mark the winning moves.
        private void showWin() {
            JOptionPane.showMessageDialog(null, "Congrats! We have a Winner!");
        }

        // Reset the game so that it can be played again.
        private void newGame(){
            turn=0;
            for (int i = 0; i < spaces.length; i++) {
                spaces[i].setText(" ");
            }
        }
    }
}