import java.util.ArrayList;

public class CollisionChecker {

    World world;

    public CollisionChecker(World world) {
        this.world = world;
    }

    public boolean checkIfPossibleMove(Position position) {
        int possiblePositions = 4;

        if (position.getY() - 1 < 1 || world.checkIfOccupied(new Position(position.getX(), position.getY() - 1))) {
            possiblePositions--;
        }
        if (position.getX() - 1 < 1 || world.checkIfOccupied(new Position(position.getX() - 1, position.getY()))) {
            possiblePositions--;
        }
        if (position.getY() + 1 > world.worldY - 2 || world.checkIfOccupied(new Position(position.getX(), position.getY() + 1))) {
            possiblePositions--;
        }
        if (position.getX() + 1 > world.worldX - 2 || world.checkIfOccupied(new Position(position.getX() + 1, position.getY()))) {
            possiblePositions--;
        }

        return possiblePositions > 0;
    }

    public ArrayList<Position> checkIfFreePosition(Position position) {
        ArrayList<Position> possiblePositions = new ArrayList<>();

        if (position.getY() > 1 && !(world.checkIfOccupied(new Position(position.getX(), position.getY() - 1)))) {
            Position freePosition = new Position(position.getX(), position.getY() - 1);
            possiblePositions.add(freePosition);
        }
        if (position.getX() > 1 && !(world.checkIfOccupied(new Position(position.getX() - 1, position.getY())))) {
            Position freePosition = new Position(position.getX() - 1, position.getY());
            possiblePositions.add(freePosition);
        }
        if (position.getY() < world.worldY - 2 && !(world.checkIfOccupied(new Position(position.getX(), position.getY() + 1)))) {
            Position freePosition = new Position(position.getX(), position.getY() + 1);
            possiblePositions.add(freePosition);
        }
        if (position.getX() < world.worldX - 2 && !(world.checkIfOccupied(new Position(position.getX() + 1, position.getY())))) {
            Position freePosition = new Position(position.getX() + 1, position.getY());
            possiblePositions.add(freePosition);
        }

        return possiblePositions;
    }

    public ArrayList<Organism> checkIfOrganism(Organism organism) {
        int organismX = organism.getPosition().getX();
        int organismTopY = organism.getPosition().getY() - 1;
        int organismBotY = organism.getPosition().getY() + 1;
        int organismLeftX = organism.getPosition().getX() - 1;
        int organismRightX = organism.getPosition().getX() + 1;
        int organismY = organism.getPosition().getY();

        ArrayList<Organism> organismArrayList = new ArrayList<>();

        if (world.map.grid[organismX][organismTopY] != '*' && world.map.grid[organismX][organismTopY] != ' ') {
            Position position = new Position(organismX, organismTopY);
            organismArrayList.add(world.getOrganismFromPosition(position, organism));
        }
        if (world.map.grid[organismX][organismBotY] != '*' && world.map.grid[organismX][organismBotY] != ' ') {
            Position position = new Position(organismX, organismBotY);
            organismArrayList.add(world.getOrganismFromPosition(position, organism));
        }
        if (world.map.grid[organismLeftX][organismY] != '*' && world.map.grid[organismLeftX][organismY] != ' ') {
            Position position = new Position(organismLeftX, organismY);
            organismArrayList.add(world.getOrganismFromPosition(position, organism));
        }
        if (world.map.grid[organismRightX][organismY] != '*' && world.map.grid[organismRightX][organismY] != ' ') {
            Position position = new Position(organismRightX, organismY);
            organismArrayList.add(world.getOrganismFromPosition(position, organism));
        }
        return organismArrayList;
    }

    public ArrayList<Organism> checkForStop(Organism organism) {
        ArrayList<Organism> organismArrayList = new ArrayList<>();

        int x = organism.getPosition().getX();
        int y = organism.getPosition().getY();

        for (int i = x - 2; i <= x + 2; i++) {
            for (int j = y - 2; j <= y + 2; j++) {
                if (i > 0 && i < world.worldX - 1 && j > 0 && j < world.worldY - 1) {
                    Position position = new Position(i, j);
                    Organism organismToBeStopped = world.getOrganismFromPosition(position, organism);
                    if (organismToBeStopped != null) {
                        organismArrayList.add(organismToBeStopped);
                    }
                }
            }
        }
        return organismArrayList;
    }
}
