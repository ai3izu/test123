import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
public class TicTacToe extends JFrame implements  ActionListener {
    private final Random random = new Random();
    private final JButton[] playerButtons = new JButton[9];
    private final JLabel title;
    private boolean playerTurn;

    public TicTacToe() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tic Tac Toe");
        this.setSize(800, 800);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        title = new JLabel("Tic Tac Toe");
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setFont(new Font("Consolas", Font.BOLD, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setBackground(Color.black);
        this.add(title, BorderLayout.NORTH);

        JButton buttonPanel = new JButton();
        buttonPanel.setLayout(new GridLayout(3, 3,10,10));
        for (int i = 0; i < 9; i++) {
            playerButtons[i] = new JButton();
            playerButtons[i].setBackground(Color.BLACK);
            playerButtons[i].setForeground(Color.WHITE);
            playerButtons[i].setFont(new Font("DynaPuff", Font.BOLD, 100));
            playerButtons[i].addActionListener(this);
            playerButtons[i].setFocusable(false);
            buttonPanel.add(playerButtons[i]);

        }
        this.add(buttonPanel, BorderLayout.CENTER);
        playerTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == playerButtons[i]) {
                if (!playerButtons[i].getText().isEmpty()) {
                    return;
                }
                if (playerTurn) {
                    playerButtons[i].setText("X");
                    title.setText("O's Turn");
                } else {
                    playerButtons[i].setText("O");
                    title.setText("X's Turn");
                }

                playerTurn = !playerTurn;

                winnerCheck();
                tieCheck();
            }
        }
    }

    public void playerTurn(){
        if (random.nextInt(2) == 0) {
            playerTurn = true;
            title.setText("X's Turn");
        } else {
            title.setText("O's Turn");
            playerTurn = false;
        }
    }


    public void winnerCheck() {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}};
        for (int[] combo : winningCombinations) {
            int a = combo[0], b = combo[1], c = combo[2];
            if (playerButtons[a].getText().equals("X") &&
                    playerButtons[b].getText().equals("X") &&
                    playerButtons[c].getText().equals("X")) {
                drawX(a, b, c);
                return;
            }
            if (playerButtons[a].getText().equals("O") &&
                    playerButtons[b].getText().equals("O") &&
                    playerButtons[c].getText().equals("O")) {
                drawO(a, b, c);
                return;
            }
        }
    }

    public void tieCheck() {
        boolean tieCheck = true;
        for (int i = 0; i < 9; i++) {
            if (playerButtons[i].getText().isEmpty()) {
                tieCheck = false;
                break;
            }
        }
        if (tieCheck) {
            title.setText("Tie!");
            for (int i = 0; i < 9; i++) {
                playerButtons[i].setEnabled(false);
                playerButtons[i].setBackground(Color.GRAY);
            }
        }
    }

    public void drawX(int x, int y, int z) {
        playerButtons[x].setBackground(Color.GREEN);
        playerButtons[y].setBackground(Color.GREEN);
        playerButtons[z].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            playerButtons[i].setEnabled(false);
        }
        title.setText("X Wins!");
    }

    public void drawO(int x, int y, int z) {
        playerButtons[x].setBackground(Color.GREEN);
        playerButtons[y].setBackground(Color.GREEN);
        playerButtons[z].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            playerButtons[i].setEnabled(false);
        }
        title.setText("O Wins!");
    }
}
