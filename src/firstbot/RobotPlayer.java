package firstbot;
import battlecode.common.*;
import java.util.*;

public strictfp class RobotPlayer {
    static RobotController rc;

    static final RobotType[] spawnableRobot = {
            RobotType.POLITICIAN,
            RobotType.SLANDERER,
            RobotType.MUCKRAKER,
    };

    static final Direction[] directions = {
            Direction.NORTH,
            Direction.NORTHEAST,
            Direction.EAST,
            Direction.SOUTHEAST,
            Direction.SOUTH,
            Direction.SOUTHWEST,
            Direction.WEST,
            Direction.NORTHWEST,
    };

    static int turnCount;

    static int left;
    static int right;
    static int top;
    static int bottom;

    public static void run(RobotController rc) throws GameActionException {

        RobotPlayer.rc = rc;
        turnCount = 0;

        System.out.println("I'm a " + rc.getType() + " and I just got created!");
        while (true) {
            turnCount += 1;
            // Try/catch blocks stop unhandled exceptions, which cause your robot to freeze
            try {
                // Here, we've separated the controls into a different method for each RobotType.
                // You may rewrite this into your own control structure if you wish.

                System.out.println("I'm a " + rc.getType() + "! Location " + rc.getLocation());
                switch (rc.getType()) {
                    case ENLIGHTENMENT_CENTER: runEnlightenmentCenter(); break;
                    case POLITICIAN:           runPolitician();          break;
                    case SLANDERER:            runSlanderer();           break;
                    case MUCKRAKER:            runMuckraker();           break;
                }

                // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                Clock.yield();

            } catch (Exception e) {
                System.out.println(rc.getType() + " Exception");
                e.printStackTrace();
            }
        }
    }

    public static void runEnlightenmentCenter() {
        if (turnCount == 0) {
            // Should try to find map border(s) if possible

        }
    }

    public static void runSlanderer() {

    }

    public static void runPolitician() {

    }

    public static void runMuckraker() {

    }

    public static void tryToFindMapBorders() throws GameActionException {

        // iterate up, down, right, and left to find the map border
        // if we find the map border, we should try to communicate this to the rest of the robots
        // (if we end up finding all of the map borders, then we will know where the enemy ec are)
        int[] boundaries = new int[4];

        Direction[] scanDirections = {
                Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.WEST
        };

        for (int i = 0; i < scanDirections.length; i++) {
            MapLocation currentLocation = rc.getLocation();
            while (rc.canSenseLocation(currentLocation)) {
                currentLocation = currentLocation.add(scanDirections[i]);
            }
            // Either ends because it is out of the robot's sensor range or the location is not on the map
            // If the location is not on the map, then we have hit the border
            if (!rc.onTheMap(currentLocation)) {
                if ((scanDirections[i] == Direction.NORTH) || (scanDirections[i] == Direction.SOUTH)) {
                    boundaries[i] = currentLocation.y;
                } else {
                    boundaries[i] = currentLocation.x;
                }
            }
        }
        for (int i = 0; i < boundaries.length; i++) {

        }

    }

}
