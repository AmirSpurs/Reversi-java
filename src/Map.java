import java.awt.*;

/**
 * The Map class implant a class to manege the game's map
 * it contains 8 rows and 8 columns rows are shown with 1,2,3,..
 * columns are shown with A,B,C,...
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 3/31/2020
 */
public class Map {
    //The row of the board.
    private final int ROW;
    //The column of the board.
    private final int COLUMN;
    //An 2D array the implants board of the game
    private char[][] board;


    /**
     * Instantiates a new Map 4 cells are filled at start of the game .
     */
    public Map() {
        this.ROW = 8;
        this.COLUMN = 8;
        board = new char[8][8];
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COLUMN; j++)
                if ((i == 3 && j == 3) || (i == 4 && j == 4))
                    board[i][j] = 'W';
                else if ((i == 4 && j == 3) || (i == 3 && j == 4))
                    board[i][j] = 'B';
                else board[i][j] = ' ';

    }

    /**
     * Puts a single disk of the given color to given coordinates.
     *
     * @param x     the x (row)
     * @param y     the y (column)
     * @param color the disk's color
     */
    public void putDisk(int x, int y, char color) {
        board[x][y] = color;
    }

    /**
     * Changes the color of disk in board and implants disk flipping.
     *
     * @param x the x (row)
     * @param y the y (column)
     */
    public void flipDisk(int x, int y) {
        if (board[x][y] == 'W')
            board[x][y] = 'B';
        else
            board[x][y] = 'W';
    }

    /**
     * Gets row numbers of board
     *
     * @return the row
     */
    public int getROW() {
        return ROW;
    }

    /**
     * Gets column number of board.
     *
     * @return the column
     */
    public int getCOLUMN() {
        return COLUMN;
    }

    /**
     * Get board.
     *
     * @return the char[ ][ ] that implants board
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Converter the A,B,C to a number y column coordinate
     * A to 0 ,B to 1 and so on.
     *
     * @param y the y
     * @return the int
     */
    public int converter(char y) {
        //also could've used casting.
        switch (y) {
            case 'A':
                return 0;
            case 'B':
                return 1;

            case 'C':
                return 2;

            case 'D':
                return 3;

            case 'E':
                return 4;

            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            default:
                return -1;


        }
    }

    /**
     * Print the board of the game.
     */
    public void print() {
        //important note : the user terminal must support ansi color and unicode other wise game won't run correctly.
        for (char i = 'A'; i <= 'H'; i++)
            System.out.print("\u001B[93m" + "     " + i);
        System.out.println();
        System.out.println("\u001B[31m" + "  _________________________________________________");

        for (int i = 0; i < ROW; i++) {
            System.out.print("\u001B[93m" + (i + 1) + " ");
            for (int j = 0; j < COLUMN; j++) {
                System.out.print("\u001B[31m" + "|  ");
                if (board[i][j] == 'W')
                    System.out.print("\u001B[97m" + '⬤' + "\u001B[31m" + "  ");
                else if (board[i][j] == 'B')
                    System.out.print("\u001B[30m" + '⬤' + "\u001B[31m" + "  ");
                else
                    System.out.print("\u001B[94m" + '⬤' + "\u001B[31m" + "  ");


            }
            System.out.println("|");
            System.out.println("  _________________________________________________" + "\u001B[37m" + "\u001B[104m");

        }
    }
}
