import java.util.ArrayList;
import java.util.Random;

public class Alien extends Organism {

    Random random = new Random();

    public Alien (World world, Position position, int power, int initiative, int liveLength, int powerToReproduce, char sign) {
        super(world, position, power, initiative, liveLength, powerToReproduce, sign);
    }

    @Override
    public void move() {
        ArrayList<Position> positions = world.collisionChecker.checkIfFreePosition(getPosition());
        this.getLastPosition().setX(getPosition().getX());
        this.getLastPosition().setY(getPosition().getY());
        int i = 0;

        for (Position position : positions) {
            i++;
        }

        int rand = random.nextInt(i);
        int disappear = random.nextInt(5);

        if (rand == 0) {
            if (disappear != 0) {
                getPosition().setX(positions.get(rand).getX());
                getPosition().setY(positions.get(rand).getY());
                stopOrganisms(world.collisionChecker.checkForStop(this));
            } else {
                world.addRemovedOrganism(this);
            }
        }
        if (rand == 1) {
            if (disappear != 0) {
                getPosition().setX(positions.get(rand).getX());
                getPosition().setY(positions.get(rand).getY());
                stopOrganisms(world.collisionChecker.checkForStop(this));
            } else {
                world.addRemovedOrganism(this);
            }
        }
        if (rand == 2) {
            if (disappear != 0) {
                getPosition().setX(positions.get(rand).getX());
                getPosition().setY(positions.get(rand).getY());
                stopOrganisms(world.collisionChecker.checkForStop(this));
            } else  {
                world.addRemovedOrganism(this);
            }
        }
        if (rand == 3) {
            if (disappear != 0) {
                getPosition().setX(positions.get(rand).getX());
                getPosition().setY(positions.get(rand).getY());
                stopOrganisms(world.collisionChecker.checkForStop(this));
            } else {
                world.addRemovedOrganism(this);
            }
        }
    }

    public void stopOrganisms(ArrayList<Organism> organisms) {
        for (Organism organism : organisms) {
            for (Organism value : world.getOrganisms()) {
                if (value instanceof Animal && organism.getPosition().isEqual(value.getPosition())) {
                    ((Animal) value).setIsStopped(true);
                }
            }
        }
    }

    @Override
    public Organism newOne(World world, Position position) {
        return new Alien(world, new Position(position.getX(), position.getY()), 0, 20, 30, 0, 'A');
    }
}