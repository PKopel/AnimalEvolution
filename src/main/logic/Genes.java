package main.logic;

import main.parameters.WorldParameters;

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
        int nextX =
                (WorldParameters.getParameters().getWidth() + current.x + this.x) % WorldParameters.getParameters().getWidth();
        int nextY =
                (WorldParameters.getParameters().getHeight() + current.y + this.y) % WorldParameters.getParameters().getHeight();
        return new Position(nextX, nextY);
    }

}
