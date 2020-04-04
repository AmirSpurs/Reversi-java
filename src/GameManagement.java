import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.util.Scanner;

public class GameManagement {
    private Map map;
    private Player[] players;
    public GameManagement(Map map, Player player1, Player player2) {
        this.map = map;
        players = new Player[2];
        players[0] = player1;
        players[1] =  player2;
    }

    public void PlayGame() throws InterruptedException, IOException {
        Scanner input = new Scanner(System.in);
        while (!endGame()) {
            for (int i = 0; i < 2; i++) {
               // System.out.print('\u000C');
                map.print();
                scoreBoard();
                int x, y;
                if (!anyAvailableMove(players[i])) {
                    System.out.println(players[i].getName() + " Turn");
                    System.out.println("PASS");
                    continue;
                }
                int flag = 0;
                if (!(players[i] instanceof Computer)) {
                    do {
                        if (flag==1)
                            System.out.println("Invalid Input Try Again");
                        flag = 1;
                        System.out.println(players[i].getName() + " Turn");
                          System.out.println("Enter disk coordinate :");
                        x = input.nextInt() - 1;
                        y = map.converter(input.next().charAt(0));
                    } while (!placeDisk(x, y, players[i]) && !endGame());
                }
                else
                {
                    System.out.println(players[i].getName() + " Turn");
                    //Thread.sleep(1140);
                    Computer ai = (Computer )players[i] ;
                    int [] coordinates = ai.bestMove(map);
                    System.out.println((coordinates[0]+1)+"  "+(coordinates[1]+1));
                    placeDisk(coordinates[0],coordinates[1],players[i]);
                }

                if (endGame()) {
                    System.out.println("Game Ended");
                    if (players[0].getDiskNumber()>players[1].getDiskNumber())
                      System.out.println(players[0].getName() + " IS THE  !!!!WINNER!!!");
                    else
                        System.out.println(players[1].getName() + " IS THE  !!!!WINNER!!!");
                    break;
                }
            }
        }

    }

    private boolean validInput(int x, int y, Player playerToPlace) {
        if (x >= map.getROW() || x < 0 || y >= map.getCOLUMN() || y < 0 || map.getBoard()[x][y] != ' ')
            return false;
        char colorToCheck;
        if (playerToPlace.getColor() == 'W')
            colorToCheck = 'B';
        else
            colorToCheck = 'W';
        int i = x - 1;
        while (i > 0 && map.getBoard()[i][y] == colorToCheck ) {
            i--;
            if (map.getBoard()[i][y] == playerToPlace.getColor())
                return true;
        }
        int j = y - 1;
        while ( j > 0 && map.getBoard()[x][j] == colorToCheck ) {
            j--;
            if (map.getBoard()[x][j] == playerToPlace.getColor())
                return true;
        }
        i = x + 1;
        while ( i < map.getROW() - 1 && map.getBoard()[i][y] == colorToCheck ) {
            i++;
            if (map.getBoard()[i][y] == playerToPlace.getColor())
                return true;

        }
        j = y + 1;
        while ( j < map.getCOLUMN() - 1 && map.getBoard()[x][j] == colorToCheck) {
            j++;
            if (map.getBoard()[x][j] == playerToPlace.getColor())
                return true;
        }
        i = x + 1;
        j = y + 1;
        while (j < map.getCOLUMN() - 1 && i < map.getROW() - 1 && map.getBoard()[i][j] == colorToCheck ) {
            j++;
            i++;
            if (map.getBoard()[i][j] == playerToPlace.getColor())
                return true;

        }
        i = x - 1;
        j = y - 1;
        while ( j > 0 && i > 0 && map.getBoard()[i][j] == colorToCheck ) {
            j--;
            i--;
            if (map.getBoard()[i][j] == playerToPlace.getColor())
                return true;

        }
        i = x - 1;
        j = y + 1;
        while (j < map.getCOLUMN() - 1 && i > 0 && map.getBoard()[i][j] == colorToCheck ) {
            j++;
            i--;
            if (map.getBoard()[i][j] == playerToPlace.getColor())
                return true;
        }
        i = x + 1;
        j = y - 1;
        while ( j > 0 && i < map.getROW()-1 && map.getBoard()[i][j] == colorToCheck ) {
            j--;
            i++;
            if (map.getBoard()[i][j] == playerToPlace.getColor())
                return true;

        }
        return false;
    }

    private boolean placeDisk(int x, int y, Player playerToPlace) {
        int diff = 0;
        if (!validInput(x, y, playerToPlace))
            return false;
        char colorToCheck;
        if (playerToPlace.getColor() == 'W')
            colorToCheck = 'B';
        else
            colorToCheck = 'W';
        int i = x - 1;
        while ( i > 0 && map.getBoard()[i][y]==colorToCheck) {
            i--;
            if (map.getBoard()[i][y] == playerToPlace.getColor()) {
                i++;
                while (i < x) {
                    map.flipDisk(i, y);
                    i++;
                    diff++;
                }
            }
        }
        int j = y - 1;
        while ( j > 0 && map.getBoard()[x][j] == colorToCheck ) {
            j--;
            if (map.getBoard()[x][j] == playerToPlace.getColor()) {
                j++;
                while (j < y) {
                    map.flipDisk(x, j);
                    j++;
                    diff++;
                }
            }

        }
        i = x + 1;
        while (  i < map.getROW() - 1 && map.getBoard()[i][y] == colorToCheck) {
            i++;
            if (map.getBoard()[i][y] == playerToPlace.getColor()) {
                i--;
                while (i > x) {
                    map.flipDisk(i, y);
                    i--;
                    diff++;
                }
            }
        }
        j = y + 1;
        while ( j < map.getCOLUMN() - 1 && map.getBoard()[x][j] == colorToCheck ) {
            j++;
            if (map.getBoard()[x][j] == playerToPlace.getColor()) {
                j--;
                while (j > y) {
                    map.flipDisk(x, j);
                    j--;
                    diff++;
                }
            }
        }
        i = x + 1;
        j = y + 1;
        while ( j < map.getCOLUMN() - 1 && i < map.getROW() - 1 && map.getBoard()[i][j] == colorToCheck ) {
            j++;
            i++;
            if (map.getBoard()[i][j] == playerToPlace.getColor()) {
                j--;
                i--;
                while (j > y && i > x) {
                    map.flipDisk(i, j);
                    j--;
                    i--;
                    diff++;
                }
            }
        }
        i = x - 1;
        j = y - 1;
        while ( j > 0 && i > 0 && map.getBoard()[i][j] == colorToCheck ) {
            j--;
            i--;
            if (map.getBoard()[i][j] == playerToPlace.getColor()) {
                j++;
                i++;
                while (j < y && i < x) {
                    map.flipDisk(i, j);
                    j++;
                    i++;
                    diff++;
                }
            }
        }
        i = x - 1;
        j = y + 1;
        while ( j < map.getCOLUMN() - 1 && i > 0 && map.getBoard()[i][j] == colorToCheck ) {
            j++;
            i--;
            if (map.getBoard()[i][j] == playerToPlace.getColor()) {
                j--;
                i++;
                while (j > y && i < x) {
                    map.flipDisk(i, j);
                    j--;
                    i++;
                    diff++;
                }
            }
        }
        i = x + 1;
        j = y - 1;
        while ( j > 0 && i < map.getROW() -1 && map.getBoard()[i][j] == colorToCheck ) {
            j--;
            i++;
            if (map.getBoard()[i][j] == playerToPlace.getColor()) {
                j++;
                i--;
                while (j < y && i > x) {
                    map.flipDisk(i, j);
                    j++;
                    i--;
                    diff++;
                }
            }
        }
        map.putDisk(x, y, playerToPlace.getColor());
        diff++;
        playerToPlace.setDiskNumber(playerToPlace.getDiskNumber() + diff);
        if (playerToPlace==players[0])
            players[1].setDiskNumber(players[1].getDiskNumber() - diff + 1);
        else
            players[0].setDiskNumber( players[0].getDiskNumber() - diff +1);


        return true;
    }

    private boolean endGame() {
        for (int i =0 ;i<2;i++)
            if (anyAvailableMove(players[i]))
                return false;
            return true ;


    }

    private boolean anyAvailableMove(Player playerToPlace) {
        for (int i = 0; i < map.getROW(); i++)
            for (int j = 0; j < map.getCOLUMN(); j++)
                if (validInput(i, j, playerToPlace))
                    return true;
        return false;

    }
    public void scoreBoard()
    {
        System.out.println("SCORE BOARD");
        for (int i=0;i<2;i++)
            System.out.println(players[i].getName()+": "+players[i].getDiskNumber());

    }
    public void clearConsole() throws IOException {


            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }


    }

}
