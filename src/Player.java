public class Player {

    private String name;
    private char color;
    private int diskNumber;

    public Player(String name, String color) throws Exception {
        this.name = name;
        if (color.equals("Black"))
            this.color = 'B';
        else  if (color.equals("White"))
            this.color = 'W';
        else throw new Exception ("Invalid Color");
        diskNumber = 2;
    }

    public int getDiskNumber() {
        return diskNumber;
    }

    public void setDiskNumber(int diskNumber) {
        this.diskNumber = diskNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }
}

