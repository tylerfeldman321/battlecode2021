package firstbot;

import battlecode.common.*;

public class Vector {
    private int x;
    private int y;
    private final int maxGridSize = 64;

    public Vector(int x, int y) {
        this.x = x;
        this.y = x;
    }

    public Vector(int value) {
        // 0 <= value < 16,129, given maxGridSize==64
        // We will have a max x range and y range of maxGridSize * 2 - 1 to account for all possible vectors
        int numRowCol = maxGridSize * 2 - 1;
        int offset = maxGridSize - 1;

        this.x = value % numRowCol - offset;
        this.y = value / numRowCol - offset;
    }

    /*
    Used to create a vector after detecting an enemy. Want to convert the difference between their positions to a vector
    and then to an integer and then put up a flag showing this information. This vector is then from the perspective of
    the current robot that senses the other robot
     */
    public Vector(MapLocation currentLocation, MapLocation enemyLocation) {
        this.x = enemyLocation.x - currentLocation.x;
        this.y = enemyLocation.y - currentLocation.y;
    }

    /*
    Will use to communicate the vector information using flags.
     */
    public int toInt() {
        int numRowCol = maxGridSize * 2 - 1;
        int offset = maxGridSize - 1;

        return ((this.y + offset) * numRowCol + (this.x + offset));
    }

    // Add two vectors together
    public static Vector add(Vector one, Vector two) {
        return new Vector(one.x + two.x, one.y + two.y);
    }

    // Add another vector to this vector
    public void addTogether(Vector other) {
        this.x += other.x;
        this.y += other.y;
    }

    public int toColor(RobotType rt) {
        // Using RBG values 1 to 64516 to communicate both enemy robot type and position
        // Need to return 24 bit integer to represent a color to set the flag to

        int val = this.toInt();
        int possibleValues = (maxGridSize * 2 - 1) * (maxGridSize * 2 - 1);
        int offset = 0;

        /*
        switch (rt) {
            case MUCKRAKER: offset = 1; break;
            case SLANDERER: offset = possibleValues+1; break;
            case POLITICIAN: offset = possibleValues*2+1; break;
            case ENLIGHTENMENT_CENTER: offset = possibleValues*3+1; break;
            default: System.out.println("NOT A VALID ROBOT TYPE");
        }*/

        return offset + val;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

}
