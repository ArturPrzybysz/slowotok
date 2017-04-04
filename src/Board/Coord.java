package Board;

/**
 * Created by Artur on 2017-01-31.
 */
public class Coord {

    public Coord(int x, int y) {
        i = x;
        j = y;
    }

    public int getI() {
        return i;
    }

    boolean isWithinBorders() {
        if ((i >= 0 && i <= 3) && (j >= 0 && j <= 3)) return true;
        else return false;
    }

    public int getJ() {
        return j;
    }

    private int i;
    private int j;

}
