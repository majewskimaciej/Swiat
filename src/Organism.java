import java.util.ArrayList;
import java.util.Random;

public abstract class Organism {

    World world;
    private Position position;
    private int power;
    private final int initiative;
    private int liveLength;
    private final int powerToReproduce;
    private final char sign;
    private Position lastPosition = new Position(0, 0);
    private boolean isStopped = false;

    public Organism(World world, Position position, int power, int initiative, int liveLength, int powerToReproduce, char sign) {
        this.world = world;
        this.position = position;
        this.power = power;
        this.initiative = initiative;
        this.liveLength = liveLength;
        this.powerToReproduce = powerToReproduce;
        this.sign = sign;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getInitiative() {
        return initiative;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getLiveLength() {
        return liveLength;
    }

    public void setLiveLength(int liveLength) {
        this.liveLength = liveLength;
    }

    public char getSign() {
        return sign;
    }

    public Position getLastPosition() {
        return lastPosition;
    }

    public void setIsStopped(boolean isStopped) {
        this.isStopped = isStopped;
    }

    public boolean ifReproduce() {
        return this.power >= this.powerToReproduce;
    }

    public void actionAdd() {
        Position position = chooseBirthPlace();

        if (position == null) {
            Organism newOrganism = this.newOne(world, getPosition());
            world.addOrganism(newOrganism);
        } else {
            Organism newOrganism = this.newOne(world, position);
            world.addOrganism(newOrganism);
        }

        world.sortOrganisms();
    }

    public void actionIncPower(int x) {
        this.setPower(this.getPower() + x);
    }

    public void decLiveLength() {
        this.setLiveLength(this.getLiveLength() - 1);
    }

    public void makeDead() {
        if (liveLength < 1) {
            world.addRemovedOrganism(this);
        }
    }

    public boolean checkIfDead() {
        for (Organism organism : world.getRemovedOrganisms()) {
            if (organism == this) {
                return true;
            }
        }
        return false;
    }

    public void chooseAction() {
        ArrayList<Organism> organismArrayList = world.collisionChecker.checkIfOrganism(this);

        for (Organism value : world.getOrganisms()) {
            for (Organism organism : organismArrayList) {
                if (organism != null && organism.getPosition().isEqual(value.getPosition())) {
                    if (value.getSign() == this.getSign() && value.ifReproduce() && this.ifReproduce()) {
                        world.addNewOrganism(value);
                        this.setPower(this.getPower() / 2);
                        value.setPower(value.getPower() / 2);
                    } else if (this.getSign() == 'S' && value.getSign() == 'G') {
                        while (!this.ifReproduce()) {
                            world.addRemovedOrganism(value);
                            actionIncPower(2);
                        }
                    } else if (this.getSign() == 'S' && value.getSign() == 'D') {
                        while (!this.ifReproduce()) {
                            world.addRemovedOrganism(value);
                            actionIncPower(1);
                        }
                    } else if (this.getSign() == 'W' && value.getSign() == 'S') {
                        world.addRemovedOrganism(value);
                        actionIncPower(5);
                    }
                }
            }
        }

        if (this instanceof Plant && this.ifReproduce()) {
            world.addNewOrganism(this);
        }
    }

    public void update() {
        makeDead();
        if (!(this instanceof Alien) && !checkIfDead() && !isStopped) {
            chooseAction();
        }
        if (!(this instanceof Plant) && world.collisionChecker.checkIfPossibleMove(getPosition()) && !isStopped) {
            move();
        }

        actionIncPower(1);
        decLiveLength();
        if (this instanceof Animal) {
            ((Animal) this).setIsStopped(false);
        }
    }

    public Position chooseBirthPlace() {
        ArrayList<Position> positions = world.collisionChecker.checkIfFreePosition(getPosition());
        Random random = new Random();
        int i = 0;

        for (Position position : positions) {
            i++;
        }

        if (i > 0) {
            int rand = random.nextInt(i);

            if (rand == 0) {
                Position position = positions.get(rand);
                return position;
            }
            if (rand == 1) {
                Position position = positions.get(rand);
                return position;
            }
            if (rand == 2) {
                Position position = positions.get(rand);
                return position;
            }
            if (rand == 3) {
                Position position = positions.get(rand);
                return position;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " power = " + this.power + ", liveLength = " + this.liveLength + ", powerToReproduce = "
                + this.powerToReproduce + ", position = " + this.position;
    }

    public abstract void move();

    public abstract Organism newOne(World world, Position position);
}
