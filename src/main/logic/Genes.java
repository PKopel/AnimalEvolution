package main.logic;

public enum Genes {
    N(0, 1), NE(1, 1), E(1, 0), SE(1, -1),
    S(0, -1), SW(-1, -1), W(-1, 0), NW(-1, 1);

    private int x;
    private int y;

    Genes(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position nextPosition(Position current) {
        int nextX = (World.width + current.x + this.x) % World.width;
        int nextY = (World.height + current.y + this.y) % World.height;
        return new Position(nextX, nextY);
    }

}
