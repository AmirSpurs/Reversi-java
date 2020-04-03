import java.awt.*;

public class Map {
    private final int ROW;
    private final int COLUMN;
    private char[][] board;


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

    public void putDisk(int x,int y,char color)
    {
        board[x][y] = color;
    }
    public void flipDisk (int x,int y)
    {
       if (board[x][y] =='W')
           board[x][y] ='B' ;
       else
           board[x][y] = 'W' ;
    }
    public int getROW() {
        return ROW;
    }

    public int getCOLUMN() {
        return COLUMN;
    }

    public char[][] getBoard() {
        return board;
    }

    public int converter(char y) {
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

    public void print() {
        for (char i = 'A'; i <= 'H'; i++)
            System.out.print("\u001B[93m"+"     " + i);
        System.out.println();
        System.out.println("\u001B[31m"+"  _____________________________________________________");

        for (int i = 0; i < ROW; i++) {
            System.out.print("\u001B[93m"+(i + 1) + " ");
            for (int j = 0; j < COLUMN; j++)
            {
                System.out.print("\u001B[31m"+"|  " );
            if (board[i][j]=='W')
                System.out.print("\u001B[30m"+ '⬤' + "\u001B[31m"+"  ");
                else if (board[i][j]=='B')
                 System.out.print("\u001B[97m"+ '⬤' + "\u001B[31m"+"  ");
                else
                System.out.print("\u001B[90m"+'⬤' + "\u001B[31m"+"  ");


            }
            System.out.println("|");
            System.out.println("  _____________________________________________________"+"\u001B[0m");

        }
    }
}
