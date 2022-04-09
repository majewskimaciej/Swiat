public class Grass extends Plant {
    public Grass(World world, Position position) {
        super(world, position, 0, 0, 10, 8, 'G');
    }

    @Override
    public void move() {

    }

    @Override
    public Organism newOne(World world, Position position) {
        return new Grass(world, new Position(position.getX(), position.getY()));
    }
}