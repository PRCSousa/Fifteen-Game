package game;
public enum Moves{
        // (Y,X) / (i,j)
        LEFT(0,-1),
        RIGHT(0,1),
        UP(-1,0),
        DOWN(1,0);

    int dx, dy;

        Moves(int y, int x)
        {
            dx = x;
            dy = y;
        }

    }