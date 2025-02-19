import java.util.Scanner;

public class AIPlayer extends Player{
    public AIPlayer(String _name, char _symbol) {
        super(_name, _symbol);
    }

    @Override
    public boolean newPlayerAction(Board board, Scanner read) {
        int row, column;

        // IA básica para o jogo
        do {
            // A IA escolhe uma posição aleatória
            row = (int) Math.floor(Math.random() * Board.SIZE);
            column = (int) Math.floor(Math.random() * Board.SIZE);
        } while (board.board[row][column] != '_'); // Garante que a posição esteja livre

        return board.newAction(row, column, getSymbol());
    }
}
