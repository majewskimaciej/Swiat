import java.util.ArrayList;

public class Map {

    World world;
    char[][] grid;

    public Map(World world) {
        this.world = world;
        this.grid = new char[world.worldX][world.worldY];
    }

    public void setMap(ArrayList<Organism> organisms) {
        for (int i = 0; i < world.worldX; i++) {
            for (int j = 0; j < world.worldY; j++) {
                if (i == 0 || i == world.worldX - 1 || j == 0 || j == world.worldY - 1) {
                    grid[i][j] = '*';
                } else {
                    grid[i][j] = ' ';
                }
                for (Organism organism : organisms) {
                    if (organism.getPosition().isEqual(new Position(i, j))) {
                        grid[i][j] = organism.getSign();
                    }
                }
            }
        }
    }

    public void printMap() {
        for (int i = 0; i < world.worldY; i++) {
            for (int j = 0; j < world.worldX; j++) {
                System.out.print(grid[j][i]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
