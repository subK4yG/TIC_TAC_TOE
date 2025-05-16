import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {

    private JButton[][] buttons;
    private JLabel resultLabel;
    private int[][] board;
    private int currentPlayer;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        board = new int[3][3];
        currentPlayer = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(this);
                gamePanel.add(buttons[i][j]);
            }
        }

        resultLabel = new JLabel("Player 1's turn");
        add(resultLabel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("")) {
            button.setText(currentPlayer == 1 ? "X" : "O");
            button.setEnabled(false);
            int row = -1, col = -1;

            // Update the board
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j] == button) {
                        row = i;
                        col = j;
                        board[i][j] = currentPlayer;
                        break;
                    }
                }
            }

            if (checkWin(row, col)) {
                resultLabel.setText("Player " + currentPlayer + " wins!");
                disableAllButtons();
            } else if (checkDraw()) {
                resultLabel.setText("It's a draw!");
            } else {
                currentPlayer = currentPlayer == 1 ? 2 : 1;
                resultLabel.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }

    private boolean checkWin(int row, int col) {
        return (board[row][0] == currentPlayer         // Check row
                && board[row][1] == currentPlayer
                && board[row][2] == currentPlayer
                || board[0][col] == currentPlayer      // Check column
                && board[1][col] == currentPlayer
                && board[2][col] == currentPlayer
                || row == col                          // Check diagonal
                && board[0][0] == currentPlayer
                && board[1][1] == currentPlayer
                && board[2][2] == currentPlayer
                || row + col == 2                      // Check opposite diagonal
                && board[0][2] == currentPlayer
                && board[1][1] == currentPlayer
                && board[2][0] == currentPlayer);
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}