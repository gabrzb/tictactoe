import java.util.Scanner;

public class Player {
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public boolean newPlayerAction(Board board, Scanner read) {
        int row = 0, column = 0;
        String[] pos;

        System.out.print(name + ", digite a posição (linha coluna): ");
        pos = read.nextLine().split(" ");

        // Verifica se a entrada está no formato correto 
        // Exemplo: pos = {"1","2"} está correto, enquanto pos = {"1","2","3"} não está
        if (pos.length != 2) {
            System.out.println("Entrada incorreta, tente novamente.");
            System.out.println("---------------------------\n");
            return false;
        }

        try {
            // Converte as posições para inteiros 
            row = Integer.parseInt(pos[0]) - 1;
            column = Integer.parseInt(pos[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, use números.");
            return false;
        }

        // Verifica se as posições estão dentro dos limites do tabuleiro
        if (row < 0 || row >= Board.SIZE || column < 0 || column >= Board.SIZE) {
            System.out.println("Posição inválida, tente novamente.");
            return false;
        }

        return board.newAction(row, column, symbol);
    }

    public String getName() { return name; }
    public char getSymbol() { return symbol; }

    public void setName(String name) { this.name = name; }
    public void setSymbol(char symbol) { this.symbol = symbol; }
}
