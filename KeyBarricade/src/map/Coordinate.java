package map;

public class Coordinate {

    private final int X, Y;

    public Coordinate(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinate) {
            Coordinate c = (Coordinate) o;
            return c.getX() == this.X && c.getY() == this.Y;
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.X;
        hash = 37 * hash + this.Y;
        return hash;
    }

}
