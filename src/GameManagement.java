import java.lang.management.PlatformLoggingMXBean;
import java.util.Scanner;

public class GameManagement {
    private Map map;
    private Player[] players;
    // private Player player2;

    public GameManagement(Map map, Player player1, Player player2) {
        this.map = map;
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;
    }

    public void PlayGame() {
        Scanner input = new Scanner(System.in);
        map.print();
        while (!endGame()) {
            for (int i = 0; i < 2; i++) {
                int x, y;
                if (!anyAvailableMove(players[i])) {
                    System.out.println(players[i].getName() + " Turn");
                    System.out.println("PASS");
                    continue;
                }
                do {
                    System.out.println(players[i].getName() + " Turn");
                    System.out.println("Enter disk coordinate :");
                    x = input.nextInt()-1;
                    y = map.converter(input.next().charAt(0));
                } while (!placeDisk(x, y, players[i]) && !endGame());
                map.print();
                if (endGame()) {
                    System.out.println("Game Ended");
                    if (players[0].getDiskNumber()>players[1].getDiskNumber())
                      System.out.println(players[0].getName() + "IS THE  !!!!WINNER!!!");
                    else
                        System.out.println(players[1].getName() + "IS THE  !!!!WINNER!!!");
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
        while ( j > 0 && i > map.getROW() && map.getBoard()[i][j] == colorToCheck ) {
            j--;
            i++;
            if (map.getBoard()[i][j] == playerToPlace.getColor())
                return true;

        }
        return false;
    }

    private boolean placeDisk(int x, int y, Player playerToPlace) {
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
                    playerToPlace.setDiskNumber(playerToPlace.getDiskNumber() + 1);
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
                    playerToPlace.setDiskNumber(playerToPlace.getDiskNumber() + 1);
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
                    playerToPlace.setDiskNumber(playerToPlace.getDiskNumber() + 1);
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
                    playerToPlace.setDiskNumber(playerToPlace.getDiskNumber() + 1);
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
                    playerToPlace.setDiskNumber(playerToPlace.getDiskNumber() + 1);
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
                    playerToPlace.setDiskNumber(playerToPlace.getDiskNumber() + 1);
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
                    playerToPlace.setDiskNumber(playerToPlace.getDiskNumber() + 1);
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
                    playerToPlace.setDiskNumber(playerToPlace.getDiskNumber() + 1);
                }
            }
        }
        map.putDisk(x, y, playerToPlace.getColor());
        playerToPlace.setDiskNumber(playerToPlace.getDiskNumber() + 1);
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


}
