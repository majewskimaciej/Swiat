public class Toadstool extends Plant{
    public Toadstool(World world, Position position) {
        super(world, position, 0, 0, 10, 9, 'T');
    }

    @Override
    public void move() {

    }

    @Override
    public Organism newOne(World world, Position position) {
        return new Toadstool(world, new Position(position.getX(), position.getY()));
    }
}