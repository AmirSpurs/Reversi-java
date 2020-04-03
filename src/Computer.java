public class Computer extends Player {

    int[][] valuePerCell;

    public Computer(String name, String color) throws Exception {
        super(name, color);
        valuePerCell = new int[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                valuePerCell[i][j] = 0;

    }

    private void eachMoveValue(Map map) {

        char colorToCheck;
        if (this.getColor() == 'W')
            colorToCheck = 'B';
        else
            colorToCheck = 'W';
        for (int x = 0; x < map.getROW(); x++) {
            for (int y = 0; y < map.getCOLUMN(); y++) {
                if (map.getBoard()[x][y] == ' ') {
                    int i = x - 1;
                    while (i > 0 && map.getBoard()[i][y] == colorToCheck) {
                        i--;
                        if (map.getBoard()[i][y] == this.getColor()) {
                            i++;
                            while (i < x) {
                                valuePerCell[x][y]++;
                                i++;
                            }
                        }
                    }
                    int j = y - 1;
                    while (j > 0 && map.getBoard()[x][j] == colorToCheck) {
                        j--;
                        if (map.getBoard()[x][j] == this.getColor()) {
                            j++;
                            while (j < y) {
                                valuePerCell[x][y]++;

                                j++;
                            }
                        }

                    }
                    i = x + 1;
                    while (i < map.getROW() - 1 && map.getBoard()[i][y] == colorToCheck) {
                        i++;
                        if (map.getBoard()[i][y] == this.getColor()) {
                            i--;
                            while (i > x) {
                                valuePerCell[x][y]++;

                                i--;
                            }
                        }
                    }
                    j = y + 1;
                    while (j < map.getCOLUMN() - 1 && map.getBoard()[x][j] == colorToCheck) {
                        j++;
                        if (map.getBoard()[x][j] == this.getColor()) {
                            j--;
                            while (j > y) {
                                valuePerCell[x][y]++;
                                j--;
                            }
                        }
                    }
                    i = x + 1;
                    j = y + 1;
                    while (j < map.getCOLUMN() - 1 && i < map.getROW() - 1 && map.getBoard()[i][j] == colorToCheck) {
                        j++;
                        i++;
                        if (map.getBoard()[i][j] == this.getColor()) {
                            j--;
                            i--;
                            while (j > y && i > x) {
                                valuePerCell[x][y]++;
                                j--;
                                i--;
                            }
                        }
                    }
                    i = x - 1;
                    j = y - 1;
                    while (j > 0 && i > 0 && map.getBoard()[i][j] == colorToCheck) {
                        j--;
                        i--;
                        if (map.getBoard()[i][j] == this.getColor()) {
                            j++;
                            i++;
                            while (j < y && i < x) {
                                valuePerCell[x][y]++;
                                j++;
                                i++;
                            }
                        }
                    }
                    i = x - 1;
                    j = y + 1;
                    while (j < map.getCOLUMN() - 1 && i > 0 && map.getBoard()[i][j] == colorToCheck) {
                        j++;
                        i--;
                        if (map.getBoard()[i][j] == this.getColor()) {
                            j--;
                            i++;
                            while (j > y && i < x) {
                                valuePerCell[x][y]++;
                                j--;
                                i++;
                            }
                        }
                    }
                    i = x + 1;
                    j = y - 1;
                    while (j > 0 && i < map.getROW() - 1 && map.getBoard()[i][j] == colorToCheck) {
                        j--;
                        i++;
                        if (map.getBoard()[i][j] == this.getColor()) {
                            j++;
                            i--;
                            while (j < y && i > x) {
                                valuePerCell[x][y]++;
                                j++;
                                i--;
                            }
                        }
                    }
                }
            }
        }
    }

    public int[] bestMove(Map map) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                valuePerCell[i][j] = 0;
        eachMoveValue(map);
        int maxCorner = 0;
        int i = 0;
        int [] coordinates = new int [2];
        coordinates [0] = -1;
        coordinates [1] = -1 ;
        while (i <= 7) {
            for (int j = 0; j < 8; j++) {
                if (valuePerCell[i][j] > maxCorner) {
                    maxCorner = valuePerCell[i][j];
                    coordinates[0] = i;
                    coordinates[1] = j;
                }
                if (valuePerCell[j][i] > maxCorner) {
                    maxCorner = valuePerCell[j][i];
                    coordinates[0] = j;
                    coordinates[1] = i;
                }
            }
            i += 7;
        }
        int max = maxCorner ;
        for ( i = 0;i<8;i++)
            for (int j = 0 ; j<8;j++)
            {
                if (valuePerCell[i][j] > max && (Math.abs(maxCorner- valuePerCell[i][j])>2 || maxCorner==0)) {
                    max = valuePerCell[i][j];
                    coordinates[0] = i;
                    coordinates[1] = j;
                }
            }
        return coordinates ;
    }
}


