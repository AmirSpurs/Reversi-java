import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws Exception {
        
        int mainMenu = 2;
        Scanner input = new Scanner(System.in);
        Player player2,player1 ;
        System.out.println("Main Menu\n1)MultiPlayer\n2)Single Player\n3)Exit");
        mainMenu = input.nextInt();


        if (mainMenu!= 2 && mainMenu != 1)
            return;
        Map map = new Map();
       // map.print();
        System.out.print("Enter first player name :");
         player1 = new Player(input.next(), "Black");
        if (mainMenu== 1) {
            System.out.print("Enter second player name :");
             player2 = new Player(input.next(), "White");
        }
        else
            player2 = new Computer ("COM", "White") ;

        GameManagement playGame = new GameManagement(map,player1,player2);
        playGame.PlayGame();

    }
}
