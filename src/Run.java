import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws Exception {

        Map map = new Map();
        System.out.println("Enter first player name :");
        Scanner input = new Scanner(System.in);
        Player player1 = new Player(input.next(), "Black");
        System.out.println("Enter second player name :");
        Player player2 = new Player(input.next(), "White");
        map.print();
        GameManagement playGame = new GameManagement(map,player1,player2);
        playGame.PlayGame();

    }
}
