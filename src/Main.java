//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

class TicTacToe extends JFrame implements ActionListener{
    @Serial
    private static final long serialVersionUID=1L;
    private JButton[] buttons=new JButton[9];//array for 9 buttons
    private String currentPlayer="X";//X always starts first
    private boolean gameWon=false;//to track game state

    public TicTacToe(){
        //setting up the JFrame
        setTitle("Tic-Tac-Toe Game");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,3));//3*3 grid for the game
        //Initializing the buttons and adding them to the JFrame
        for(int i=0;i<9;i++){
            buttons[i]=new JButton("");//initially the button is empty
            buttons[i].setFont(new Font("Arial",Font.PLAIN,60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);//add buttons to the grid
        }


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton=(JButton) e.getSource();
        if(clickedButton.getText().equals("")&& !gameWon){
            clickedButton.setText((currentPlayer));//set the current players mark (X or O)
            checkForWinner();
            currentPlayer=(currentPlayer.equals("X")) ? "O":"X";
        }
    }

    //checking for the winner
    public void checkForWinner(){
        String [][] board =new String[3][3];
        for(int i=0;i<9;i++){
            board[i/3][i%3]=buttons[i].getText();
        }
        if(checkRows(board) || checkColumns(board) || checkDiagonals(board)){
            gameWon=true;
            JOptionPane.showMessageDialog(this,currentPlayer+"wins!!");

        }
        else if(isBoardFull()){
            JOptionPane.showMessageDialog(this,"Its is a draw!!");
        }
    }

    //checking rows, columns and diagonals
    private boolean checkRows(String[][] board) {
        for (int row = 0; row < 3; row++) {
            if (!board[row][0].equals("") && board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(String[][] board) {
        for (int col = 0; col < 3; col++) {
            if (!board[0][col].equals("") && board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(String[][] board) {
        if (!board[0][0].equals("") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return true;
        }
        if (!board[0][2].equals("") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false; // If any button is empty, the board is not full
            }
        }
        return true; // All buttons are filled
    }



}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true); // Make the game window visible
        });
    }
}