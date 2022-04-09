import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class InteractionsTest {
    @Test
    public void TestNoSheepWithSheepInteraction() {
        World testWorld = new World();
        testWorld.setWorldX(4);
        testWorld.setWorldY(5);
        Organism sheep1 = new Sheep(testWorld, new Position(1, 1));
        Organism sheep2 = new Sheep(testWorld, new Position(1, 2));
        testWorld.addOrganism(sheep1);
        testWorld.addOrganism(sheep2);
        testWorld.setAlien(false);
        testWorld.sortOrganisms();
        testWorld.runForXTurns(1);

        Assertions.assertEquals(2, testWorld.getOrganisms().size());
    }

    @Test
    public void TestSheepWithSheepInteraction() {
        World testWorld = new World();
        testWorld.setWorldX(4);
        testWorld.setWorldY(4);
        Organism sheep1 = new Sheep(testWorld, new Position(1, 1));
        Organism sheep2 = new Sheep(testWorld, new Position(2, 1));
        testWorld.addOrganism(sheep1);
        testWorld.addOrganism(sheep2);
        testWorld.setAlien(false);
        testWorld.sortOrganisms();
        testWorld.runForXTurns(7);

        Assertions.assertEquals(3, testWorld.getOrganisms().size());
    }

    @Test
    public void TestWolfWithWolfInteraction() {
        World testWorld = new World();
        testWorld.setWorldX(4);
        testWorld.setWorldY(4);
        Organism wolf1 = new Wolf(testWorld, new Position(1, 1));
        Organism wolf2 = new Wolf(testWorld, new Position(2, 1));
        testWorld.addOrganism(wolf1);
        testWorld.addOrganism(wolf2);
        testWorld.setAlien(false);
        testWorld.sortOrganisms();
        testWorld.runForXTurns(13);

        Assertions.assertEquals(3, testWorld.getOrganisms().size());
    }

    @Test
    public void TestWolfWithSheepInteraction() {
        World testWorld = new World();
        testWorld.setWorldX(4);
        testWorld.setWorldY(5);
        Organism sheep = new Sheep(testWorld, new Position(1, 1));
        Organism wolf = new Wolf(testWorld, new Position(2, 1));
        testWorld.addOrganism(sheep);
        testWorld.addOrganism(wolf);
        testWorld.setAlien(false);
        testWorld.sortOrganisms();
        testWorld.runForXTurns(1);

        Assertions.assertEquals(1, testWorld.getOrganisms().size());
    }

    @Test
    public void TestSheepWithGrassInteraction() {
        World testWorld = new World();
        testWorld.setWorldX(4);
        testWorld.setWorldY(5);
        Organism sheep = new Sheep(testWorld, new Position(1, 1));
        Organism grass = new Grass(testWorld, new Position(2, 1));
        testWorld.addOrganism(sheep);
        testWorld.addOrganism(grass);
        testWorld.setAlien(false);
        testWorld.sortOrganisms();
        testWorld.runForXTurns(1);

        Assertions.assertEquals(1, testWorld.getOrganisms().size());
    }

    @Test
    public void TestSheepWithDandelionInteraction() {
        World testWorld = new World();
        testWorld.setWorldX(4);
        testWorld.setWorldY(5);
        Organism sheep = new Sheep(testWorld, new Position(1, 1));
        Organism dandelion = new Dandelion(testWorld, new Position(2, 1));
        testWorld.addOrganism(sheep);
        testWorld.addOrganism(dandelion);
        testWorld.setAlien(false);
        testWorld.sortOrganisms();
        testWorld.runForXTurns(1);

        Assertions.assertEquals(1, testWorld.getOrganisms().size());
    }

    @Test
    public void TestSheepWithToadstoolInteraction() {
        World testWorld = new World();
        testWorld.setWorldX(4);
        testWorld.setWorldY(5);
        Organism sheep = new Sheep(testWorld, new Position(1, 1));
        Organism toadstool = new Toadstool(testWorld, new Position(2, 1));
        testWorld.addOrganism(sheep);
        testWorld.addOrganism(toadstool);
        testWorld.setAlien(false);
        testWorld.sortOrganisms();
        testWorld.runForXTurns(1);

        Assertions.assertEquals(2, testWorld.getOrganisms().size());
    }

    @Test
    public void TestSelfReplication() {
        World testWorld = new World();
        testWorld.setWorldX(4);
        testWorld.setWorldY(4);
        Organism grass = new Grass(testWorld, new Position(1, 1));
        testWorld.addOrganism(grass);
        testWorld.addOrganism(grass);
        testWorld.setAlien(false);
        testWorld.sortOrganisms();
        testWorld.runForXTurns(2);

        Assertions.assertEquals(2, testWorld.getOrganisms().size());
    }
}