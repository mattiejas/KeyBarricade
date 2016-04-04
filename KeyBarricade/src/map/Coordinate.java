package map;

public class Coordinate {

    private final int X, Y;

    /**
     * Initializes a newly created <code>Coordinate</code> object.
     * 
     * @param x     the x-coordinate starting from 0
     * @param y     the y-coordinate starting from 0
     */
    public Coordinate(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    /**
     * Returns the integer value of coordinate x
     * 
     * @return the x value of this coordinate
     */
    public int getX() {
        return X;
    }

    /**
     * Returns the integer value of coordinate y
     * 
     * @return the y value of this coordinate
     */
    public int getY() {
        return Y;
    }
    
    /**
     * Indicates whether some other coordinate is "equal to" this one.
     * 
     * @param obj     the reference object with which to compare.   
     * @return <code>true</code> if this coordinate is the same as the obj argument, <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate c = (Coordinate) obj;
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
