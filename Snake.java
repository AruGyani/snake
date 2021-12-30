import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    public int x, y, length;
    public int dir = 2;
    public ArrayList<int[]> board = new ArrayList<int[]>();

    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
        this.length = 5;

        board.add(new int[]{x, y});
        for (int i = 1; i <= length; i++) {
            board.add(new int[]{x - i, y});
        }
    } 

    public Snake(int x, int y, int length) {
        this.x = x;
        this.y = y;
        this.length = length;
    }

    public void addTail() {
        int x = board.get(board.size() - 1)[0];
        int y = board.get(board.size() - 1)[0];

        if (dir == 1) x++;
        if (dir == 2) x--;
        if (dir == 3) y++;
        if (dir == 4) y--;

        board.add(new int[]{x, y});
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);

        for (int i  = 0; i < board.size(); i++) {
            g.fillRect(board.get(i)[0] * App.TILE_SIZE, board.get(i)[1] * App.TILE_SIZE, App.TILE_SIZE, App.TILE_SIZE);
        }
    }

    public void update() {
        board.get(0)[0] = x;
        board.get(0)[1] = y;
        
        if (dir == 1) x--;
        if (dir == 2) x++;
        if (dir == 3) y--;
        if (dir == 4) y++;

        if (x < 0) x = 19;
        if (x > 19) x = 0;
        if (y < 0) y = 19;
        if (y > 19) y = 0;

        for (int i = board.size() - 1; i > 0; i--) {
            board.get(i)[0] = board.get(i - 1)[0];
            board.get(i)[1] = board.get(i - 1)[1];
        }

        for (int i = 1; i < board.size(); i++) {
            int curX = board.get(i)[0];
            int curY = board.get(i)[1];

            if (x == curX && y == curY) System.exit(0);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && dir != 2) dir = 1;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && dir != 1) dir = 2;
        if (e.getKeyCode() == KeyEvent.VK_UP && dir != 4) dir = 3;
        if (e.getKeyCode() == KeyEvent.VK_DOWN && dir != 3) dir = 4;

        if (e.getKeyCode() == KeyEvent.VK_SPACE) System.out.println(x + ", " + y);
    }
}
