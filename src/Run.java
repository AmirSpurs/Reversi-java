import java.awt.desktop.SystemSleepEvent;
import java.util.Scanner;

/**
 * The Class that runs the game.
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 3/31/2020
 */
public class Run {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        int mainMenu;

        System.out.println("\u001B[37m" + "\u001B[104m");
        System.out.print("\033[H\033[2J");

        Scanner input = new Scanner(System.in);
        Player player2, player1;
        System.out.println("Main Menu\n1)MultiPlayer\n2)Single Player\n3)Exit");
        mainMenu = input.nextInt();


        if (mainMenu != 2 && mainMenu != 1)
            return;
        Map map = new Map();
        //map.print();
        System.out.print("Enter first player name :");
        player1 = new Player(input.next(), "Black");
        if (mainMenu == 1) {
            System.out.print("Enter second player name :");
            player2 = new Player(input.next(), "White");
        } else
            player2 = new Computer("COM", "White");

        GameManagement playGame = new GameManagement(map, player1, player2);
        playGame.PlayGame();

    }
}
