import java.lang.management.PlatformLoggingMXBean;
import java.util.Scanner;

/**
 * The GameManagement class manage players,map of the game
 * it also controls turns,inputs,scoreboard and much more.
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 3/31/2020
 */
public class GameManagement {
    // The map of the game.
    private Map map;
    //An array that implants players.
    private Player[] players;

    /**
     * Instantiates a new Game with given map and players.
     *
     * @param map     the map
     * @param player1 the player 1
     * @param player2 the player 2
     */
    public GameManagement(Map map, Player player1, Player player2) {
        this.map = map;
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;
    }

    /**
     * Runs and manage the game.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void PlayGame() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        while (!endGame()) {
            for (int i = 0; i < 2; i++) {
                //this line clear the terminal
                System.out.print("\033[H\033[2J");
                map.print();
                scoreBoard();
                int x, y;
                if (!anyAvailableMove(players[i])) {
                    System.out.println(players[i].getName() + " Turn");
                    System.out.println("PASS");
                    //sleeps to give user time to process
                    Thread.sleep(1500);
                    continue;
                }
                //if flag is true it means user has already entered and input.
                boolean flag = false;
                if (!(players[i] instanceof Computer)) {
                    do {
                        if (flag) {
                            // user has entered invalid input
                            System.out.println("Invalid Input Try Again");
                            //sleeps to give user time to process
                            Thread.sleep(800);
                            System.out.print("\033[H\033[2J");
                            map.print();
                        }
                        flag = true;
                        System.out.println(players[i].getName() + " Turn");
                        System.out.println("Enter disk coordinate :");
                        // this is because our array start with 0
                        //char charx;
                        x =   (int) input.next().charAt(0)-49 ;
                        y = map.converter(input.next().charAt(0));
                    } while (!placeDisk(x, y, players[i]) && !endGame());
                } else {
                    System.out.println(players[i].getName() + " Turn");
                    //the player is COM so we should cast it
                    Computer ai = (Computer) players[i];
                    int[] coordinates = ai.bestMove(map);
                    //this is because we should show alphabetic
                    System.out.println((coordinates[0] + 1) + "  " + ((char) (coordinates[1] + 65)));
                    //sleeps to give user time to process
                    Thread.sleep(1500);
                    placeDisk(coordinates[0], coordinates[1], players[i]);
                }

                if (endGame()) {
                    System.out.print("\033[H\033[2J");
                    map.print();
                    scoreBoard();
                    System.out.println("Game Ended");
                    if (players[0].getDiskNumber() > players[1].getDiskNumber())
                        System.out.println(players[0].getName() + " IS THE  !!!!WINNER!!!");
                    else  if (players[0].getDiskNumber() < players[1].getDiskNumber())
                        System.out.println(players[1].getName() + " IS THE  !!!!WINNER!!!");
                    else
                        System.out.println("!!!!DRAW!!!!");
                    break;
                }
            }
        }

    }

    /*Checks if it's possible to place disk in given cell for given player
    return true if it's possible and false otherwise. */
    private boolean validInput(int x, int y, Player playerToPlace) {
        if (x >= map.getROW() || x < 0 || y >= map.getCOLUMN() || y < 0 || map.getBoard()[x][y] != ' ')
            return false;

        char colorToCheck;
        if (playerToPlace.getColor() == 'W')
            colorToCheck = 'B';
        else
            colorToCheck = 'W';
        int i = x - 1;
        while (i > 0 && map.getBoard()[i][y] == colorToCheck) {
            i--;
            if (map.getBoard()[i][y] == playerToPlace.getColor())
                return true;
        }
        int j = y - 1;
        while (j > 0 && map.getBoard()[x][j] == colorToCheck) {
            j--;
            if (map.getBoard()[x][j] == playerToPlace.getColor())
                return true;
        }
        i = x + 1;
        while (i < map.getROW() - 1 && map.getBoard()[i][y] == colorToCheck) {
            i++;
            if (map.getBoard()[i][y] == playerToPlace.getColor())
                return true;

        }
        j = y + 1;
        while (j < map.getCOLUMN() - 1 && map.getBoard()[x][j] == colorToCheck) {
            j++;
            if (map.getBoard()[x][j] == playerToPlace.getColor())
                return true;
        }
        i = x + 1;
        j = y + 1;
        while (j < map.getCOLUMN() - 1 && i < map.getROW() - 1 && map.getBoard()[i][j] == colorToCheck) {
            j++;
            i++;
            if (map.getBoard()[i][j] == playerToPlace.getColor())
                return true;

        }
        i = x - 1;
        j = y - 1;
        while (j > 0 && i > 0 && map.getBoard()[i][j] == colorToCheck) {
            j--;
            i--;
            if (map.getBoard()[i][j] == playerToPlace.getColor())
                return true;

        }
        i = x - 1;
        j = y + 1;
        while (j < map.getCOLUMN() - 1 && i > 0 && map.getBoard()[i][j] == colorToCheck) {
            j++;
            i--;
            if (map.getBoard()[i][j] == playerToPlace.getColor())
                return true;
        }
        i = x + 1;
        j = y - 1;
        while (j > 0 && i < map.getROW() - 1 && map.getBoard()[i][j] == colorToCheck) {
            j--;
            i++;
            if (map.getBoard()[i][j] == playerToPlace.getColor())
                return true;

        }
        return false;
    }

    /*this method place a disk in a cell if then given cell is valid put a disk
    in the cell and return true if the input is not valid return false */
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
        while (i > 0 && map.getBoard()[i][y] == colorToCheck) {
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
        while (j > 0 && map.getBoard()[x][j] == colorToCheck) {
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
        while (i < map.getROW() - 1 && map.getBoard()[i][y] == colorToCheck) {
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
        while (j < map.getCOLUMN() - 1 && map.getBoard()[x][j] == colorToCheck) {
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
        while (j < map.getCOLUMN() - 1 && i < map.getROW() - 1 && map.getBoard()[i][j] == colorToCheck) {
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
        while (j > 0 && i > 0 && map.getBoard()[i][j] == colorToCheck) {
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
        while (j < map.getCOLUMN() - 1 && i > 0 && map.getBoard()[i][j] == colorToCheck) {
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
        while (j > 0 && i < map.getROW() - 1 && map.getBoard()[i][j] == colorToCheck) {
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
        if (playerToPlace == players[0])
            players[1].setDiskNumber(players[1].getDiskNumber() - diff + 1);
        else
            players[0].setDiskNumber(players[0].getDiskNumber() - diff + 1);


        return true;
    }

    /*Checks if there is not any available move fo none of players
    the game has finished and return true. */
    private boolean endGame() {
        for (int i = 0; i < 2; i++)
            if (anyAvailableMove(players[i]))
                return false;
        return true;


    }

    /*Checks all cells to see if there is any available move to make
        for a player return true if there is and false otherwise.*/
    private boolean anyAvailableMove(Player playerToPlace) {
        for (int i = 0; i < map.getROW(); i++)
            for (int j = 0; j < map.getCOLUMN(); j++)
                if (validInput(i, j, playerToPlace))
                    return true;
        return false;

    }

    //Prints each player score
    private void scoreBoard() {
        System.out.println("SCORE BOARD");
        for (int i = 0; i < 2; i++)
            System.out.println(players[i].getName() + ": " + players[i].getDiskNumber());

    }


}
