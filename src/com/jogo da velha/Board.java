public class Board {
    static final int SIZE = 3;
    char[][] board = new char[SIZE][SIZE];

    public Board() {
        // Inicializa o tabuleiro com espaços vazios ('_')
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = '_';
            }
        }
    }

    public void printBoard() {
        System.out.println();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print("|" + board[row][col]);
            }
            System.out.println("|");
        }
        System.out.println();
    }

    public boolean newAction(int row, int column, char symbol) {
        // Caso a posição esteja ocupada, não permite a jogada
        if (board[row][column] != '_') {
            System.out.println("Jogada inválida, tente novamente.\n");
            return false;
        } else {
            board[row][column] = symbol;
            return true;
        }
    }

    public char checkVictory() {
        // Define as combinações vencedoras possíveis
        int[][][] winningCombinations = {
            {{0, 0}, {0, 1}, {0, 2}},
            {{1, 0}, {1, 1}, {1, 2}},
            {{2, 0}, {2, 1}, {2, 2}},
            {{0, 0}, {1, 0}, {2, 0}},
            {{0, 1}, {1, 1}, {2, 1}},
            {{0, 2}, {1, 2}, {2, 2}},
            {{0, 0}, {1, 1}, {2, 2}},
            {{0, 2}, {1, 1}, {2, 0}}
        };

        // Verifica se alguma combinação vencedora foi alcançada
        for (int[][] combination : winningCombinations) {
            char a = board[combination[0][0]][combination[0][1]];
            char b = board[combination[1][0]][combination[1][1]];
            char c = board[combination[2][0]][combination[2][1]];

            if (a != '_' && a == b && b == c) {
                return a; // Retorna o símbolo do vencedor
            }
        }

        return 'N'; // Retorna 'N' se não houver vencedor
    }

    public boolean checkDraw() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == '_') {
                    return false; // Retorna false se houver espaço vazio
                }
            }
        }
        return true; // Retorna true se não houver espaços vazios
    }
}
