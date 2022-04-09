public class Sheep extends Animal{
    public Sheep(World world, Position position) {
        super(world, position, 4, 3, 30, 10, 'S');
    }

    @Override
    public Organism newOne(World world, Position position) {
        return new Sheep(world, new Position(position.getX(), position.getY()));
    }
}