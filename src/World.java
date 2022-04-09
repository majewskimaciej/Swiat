import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class World {

    public int worldX = 10;
    public int worldY = 5;
    public boolean isAlien = true;
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    Map map = new Map(this);

    Organism alien = new Alien(this, new Position(0, 0), 0, 0, 0, 0, 'A');
    private ArrayList<Organism> organisms;
    private ArrayList<Organism> newOrganisms;
    private ArrayList<Organism> removedOrganisms;

    public World() {
        this.organisms = new ArrayList<>();
        this.newOrganisms = new ArrayList<>();
        this.removedOrganisms = new ArrayList<>();
    }

    public void setAlien(boolean isAlien) {
        this.isAlien = isAlien;
    }

    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public ArrayList<Organism> getRemovedOrganisms() {
        return removedOrganisms;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void sortOrganisms() {
        organisms.sort(Comparator.comparing(Organism::getInitiative));
        Collections.reverse(organisms);
    }

    public void addOrganism(Organism organism) {
        organisms.add(organism);
    }

    public void addNewOrganism(Organism organism) {
        newOrganisms.add(organism);
    }

    public void addRemovedOrganism(Organism organism) {
        removedOrganisms.add(organism);
    }

    public void removeOrganism(Organism organism) {
        organisms.remove(organism);
    }

    public void runIndefinitely() throws InterruptedException {
        while (true) {
            update();
            map.setMap(organisms);
            map.printMap();
            //Thread.sleep(300);
        }
    }

    public void runForXTurns(int turn) {
        for (int i = 0; i < turn; i++) {
            update();
            map.setMap(organisms);
            map.printMap();
            //Thread.sleep(1000);
        }
    }

    public void organismManager() {
        if (!removedOrganisms.isEmpty()) {
            for (Organism organism : removedOrganisms) {
                map.grid[organism.getPosition().getX()][organism.getPosition().getY()] = ' ';
                removeOrganism(organism);
            }
        }
        if (!newOrganisms.isEmpty()) {
            for (Organism organism : newOrganisms) {
                organism.actionAdd();
            }
        }
        newOrganisms.clear();
    }

    public void update() {
        for (Organism organism : organisms) {
            organism.update();
        }
        organismManager();
        alienManager();
    }

    public Organism getOrganismFromPosition(Position checkPosition, Organism currentOrganism) {
        for (Organism organism : organisms) {
            if (checkPosition.isEqual(organism.getPosition()) && organism != currentOrganism) {
                return organism;
            }
        }
        return null;
    }

    public boolean checkIfOccupied(Position checkPosition) {
        for (Organism organism : organisms) {
            if (checkPosition.isEqual(organism.getPosition())) {
                return true;
            }
        }
        return false;
    }

    public boolean alienExists() {
        for (Organism organism : organisms) {
            if (organism.getSign() == 'A') {
                return true;
            }
        }
        return false;
    }

    public void alienManager() {
        if (!alienExists() && isAlien) {
            Random random = new Random();
            int rand = random.nextInt(6);
            int randX = random.nextInt(worldX - 3) + 1;
            int randY = random.nextInt(worldY - 3) + 1;

            while (checkIfOccupied(new Position(randX, randY))) {
                randX = random.nextInt(worldX - 3) + 1;
                randY = random.nextInt(worldY - 3) + 1;
            }

            if (rand == 0) {
                Organism newOrganism = alien.newOne(this, new Position(randX, randY));
                addOrganism(newOrganism);
                sortOrganisms();
            }
        }
    }
}
