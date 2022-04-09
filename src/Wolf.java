public class Wolf extends Animal {
    public Wolf(World world, Position position) {
        super(world, position, 6, 5, 30, 18, 'W');
    }

    @Override
    public Organism newOne(World world, Position position) {
        return new Wolf(world, new Position(position.getX(), position.getY()));
    }
}
