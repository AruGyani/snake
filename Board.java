import java.awt.Graphics2D;
import java.awt.Color;

public class Board {
    private int width, height;
    private int[][] board;

    public Board() {
        this.width = 20;
        this.height = 20;
        this.board = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;
            }
        }

        populateFruit();
    }   

    public void render(Graphics2D g) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 0) g.setColor(new Color(62, 230, 107));
                if (board[i][j] == 1) g.setColor(Color.RED);

                g.fillRect(i * App.TILE_SIZE, j * App.TILE_SIZE, App.TILE_SIZE, App.TILE_SIZE);
            }
        }
    }

    public void update(Snake s) {
        if (board[s.x][s.y] == 1) {
            System.out.println("EATEN");

            board[s.x][s.y] = 0;
            populateFruit();
            s.addTail();
        }
    }

    public void populateFruit() {
        int row = (int) (Math.random() * board.length);
        int col = (int) (Math.random() * board[row].length);

        System.out.println(col + ", " + row);

        if (board[row][col] == 0) board[row][col] = 1;
    }

    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
}
