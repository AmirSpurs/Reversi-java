/**
 * The Player class implant a class to manege players
 * player's disk color only could be black or white
 * each player starts the game with 2 disks.
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 3/31/2020
 */
public class Player {

    // The player name.
    private String name;
    //The player's disk color could be 'B','W',or ' '.
    private char color;
    //The player disk number AKA score.
    private int diskNumber;

    /**
     * Instantiates a new Player with given player's name and disk color.
     *
     * @param name  the name
     * @param color the color
     * @throws Exception the exception in case user enter invalid color
     */
    public Player(String name, String color) throws Exception {
        this.name = name;
        if (color.equals("Black"))
            this.color = 'B';
        else if (color.equals("White"))
            this.color = 'W';
        else throw new Exception("Invalid Color");
        diskNumber = 2;
    }

    /**
     * Gets players's disks number(score).
     *
     * @return the disk number to get
     */
    public int getDiskNumber() {
        return diskNumber;
    }

    /**
     * Sets player's disks number(score).
     *
     * @param diskNumber the disk number to set
     */
    public void setDiskNumber(int diskNumber) {
        this.diskNumber = diskNumber;
    }

    /**
     * Gets player's name.
     *
     * @return the name to get
     */
    public String getName() {
        return name;
    }

    /**
     * Sets player's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets player's disk color.
     *
     * @return the color to get
     */
    public char getColor() {
        return color;
    }

    /**
     * Sets player's disk color.
     *
     * @param color the color to set
     */
    public void setColor(char color) {
        this.color = color;
    }
}

