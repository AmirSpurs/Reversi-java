/**
 * The type Player.
 */
public class Player {

    private String name;
    private char color;
    private int diskNumber;

    /**
     * Instantiates a new Player.
     *
     * @param name  the name
     * @param color the color
     * @throws Exception the exception
     */
    public Player(String name, String color) throws Exception {
        this.name = name;
        if (color.equals("Black"))
            this.color = 'B';
        else  if (color.equals("White"))
            this.color = 'W';
        else throw new Exception ("Invalid Color");
        diskNumber = 2;
    }

    /**
     * Gets disk number.
     *
     * @return the disk number
     */
    public int getDiskNumber() {
        return diskNumber;
    }

    /**
     * Sets disk number.
     *
     * @param diskNumber the disk number
     */
    public void setDiskNumber(int diskNumber) {
        this.diskNumber = diskNumber;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public char getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(char color) {
        this.color = color;
    }
}

