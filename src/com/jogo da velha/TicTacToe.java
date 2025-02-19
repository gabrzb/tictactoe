import java.util.Scanner;

public class TicTacToe {

    public static void game(Player player1, Player player2, Scanner read) {
        Player currentPlayer = null;
        Board board = new Board();
        boolean IsGameOver = false;
        int turn = (int) Math.floor(Math.random() * 2); // Escolhe aleatoriamente o primeiro jogador
        boolean draw = false;
        char victory = 'N';

        board.printBoard();

        while (!IsGameOver) {
            currentPlayer = (turn == 1) ? player1 : player2;

            System.out.println("\n----------------------------------");
            System.out.println("Vez de " + currentPlayer.getName() + ": ");
            System.out.println("----------------------------------");

            if (currentPlayer.newPlayerAction(board, read)) {
                victory = board.checkVictory();
                draw = board.checkDraw();

                if (victory == 'N' && !draw) {
                    // Troca de turno apenas se não houver vitória nem empate
                    turn = (turn == 1) ? 2 : 1;
                } else {
                    IsGameOver = true; // Se houver vitória ou empate, o jogo terminará mostrando o resultado
                }
            }

            board.printBoard();

            // Pausa para tornar o jogo mais claro
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                System.err.println("\n\nErro de delay: " + e.getMessage()+"\n");
            }

            // Verifica se houve vencedor ou empate
            if (victory != 'N') {
                String winnerName = (victory == player1.getSymbol()) ? player1.getName() : player2.getName();
                System.out.println(winnerName + " venceu!");
            } else if (draw) {
                System.out.println("O jogo terminou em empate.");
            }
        }
    }

    public static void main(String[] args) {
        String name;
        char symbol, repeat;
        Player player1 = null;
        Player player2 = null;
        boolean IsGameOver = false, IsInputRegistered = false;

        try (Scanner read = new Scanner(System.in)) {

            System.out.println("\n---------------------------\n");
            System.out.println("Bem-vindo ao jogo da velha!");
            System.out.println("\n---------------------------\n");

            while (!IsGameOver) {

                while (!IsInputRegistered) {

                    System.out.print("Digite o nome do primeiro jogador: ");
                    name = read.nextLine();
                    System.out.print("Digite qual símbolo você quer ser (O|X): ");
                    symbol = read.nextLine().charAt(0);

                    if (symbol != 'X' && symbol != 'O') {
                        System.out.println("Símbolo inválido. Tente novamente.\n");
                        continue;
                    }

                    player1 = new Player(name, symbol);

                    System.out.print("Digite o nome do segundo jogador (ou deixe em branco para IA): ");
                    name = read.nextLine();

                    symbol = (symbol == 'X') ? 'O' : 'X';

                    if (name.trim().isEmpty()) {
                        player2 = new AIPlayer("IA", symbol);
                    } else {
                        player2 = new Player(name, symbol);
                    }

                    IsInputRegistered = true;
                }

                System.out.println("\n---------------------------\n");

                game(player1, player2, read); // Realiza o jogo

                System.out.println("\n---------------------------\n");

                System.out.print("Deseja jogar novamente? (S/N): ");
                repeat = Character.toLowerCase(read.nextLine().charAt(0));

                if (repeat == 's') {
                    System.out.print("Deseja mudar os jogadores? (S/N): ");
                    repeat = Character.toLowerCase(read.nextLine().charAt(0));

                    IsInputRegistered = !(repeat == 's'); // Caso verdadeiro, será necessário novo input. Por isso é invertido o valor.

                    System.out.println("\nReiniciando jogo...");

                    // Pausa realizada para simular uma reinicialização não imediata
                    try {
                        Thread.sleep(2000);  
                    } catch (InterruptedException e) {
                        System.out.println("!");
                    }

                    System.out.println("---------------------------");

                } else {
                    System.out.println("\n---------------------------\n");
                    System.out.println("Encerrando jogo. Obrigado por jogar!");
                    System.out.println("\n---------------------------\n");

                    IsGameOver = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e + "\nEncerrando jogo.");
            System.out.println("---------------------------\n");
        }
    }
}
