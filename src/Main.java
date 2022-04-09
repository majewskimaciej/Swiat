public class Main {

    public static void main(String[] args) throws InterruptedException {
        World world = new World();
        Organism sheep1 = new Sheep(world, new Position(2, 3));
        Organism sheep2 = new Sheep(world, new Position(2, 2));
        Organism sheep3 = new Sheep(world, new Position(3, 3));
        world.addOrganism(sheep1);
        world.addOrganism(sheep2);
        world.addOrganism(sheep3);
        Organism toadstool = new Toadstool(world, new Position(4, 2));
        world.addOrganism(toadstool);
        Organism wolf1 = new Wolf(world, new Position(8, 2));
        Organism wolf2 = new Wolf(world, new Position(7, 2));
        world.addOrganism(wolf1);
        world.addOrganism(wolf2);
        Organism grass = new Grass(world, new Position(3, 1));
        world.addOrganism(grass);
        Organism dandelion1 = new Dandelion(world, new Position(8, 3));
        world.addOrganism(dandelion1);

        world.runForXTurns(50);
    }
}
