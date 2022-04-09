public class Position {
    private Integer x = 0;
    private Integer y = 0;

    Position(Integer x, Integer y) {
            this.x = x;
            this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Boolean isEqual(Position otherPosition) {
        return x == otherPosition.x && y == otherPosition.y;
    }

    @Override
    public String toString() {
        return x + ":" + y;
    }
}
