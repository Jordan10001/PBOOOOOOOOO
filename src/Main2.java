import java.util.*;

abstract class Enemy {
    protected int row, col;
    protected int size;

    public abstract void randomize();
    public abstract boolean checkHit(int r, int c);
    public abstract boolean isDestroyed();
}

class Dot extends Enemy {
    private boolean hit = false;

    public Dot() {
        this.size = 1;
        randomize();
    }

    @Override
    public void randomize() {
        Random rand = new Random();
        row = rand.nextInt(8);
        col = rand.nextInt(8);
    }

    @Override
    public boolean checkHit(int r, int c) {
        if (r == row && c == col && !hit) {
            hit = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean isDestroyed() {
        return hit;
    }
}

class Line extends Enemy {
    private boolean[] hits;

    public Line(int size) {
        this.size = size;
        hits = new boolean[size];
        randomize();
    }

    @Override
    public void randomize() {
        Random rand = new Random();
        do {
            row = rand.nextInt(8);
            col = rand.nextInt(8);
        } while (row + size > 8 || col + size > 8);
    }

    @Override
    public boolean checkHit(int r, int c) {
        for (int i = 0; i < size; i++) {
            if (r == row + i && c == col + i && !hits[i]) {
                hits[i] = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isDestroyed() {
        for (boolean h : hits) {
            if (!h) return false;
        }
        return true;
    }
}

class Square extends Enemy {
    private boolean[][] hits;

    public Square(int size) {
        this.size = size;
        hits = new boolean[size][size];
        randomize();
    }

    @Override
    public void randomize() {
        Random rand = new Random();
        do {
            row = rand.nextInt(8);
            col = rand.nextInt(8);
        } while (row + size > 8 || col + size > 8);
    }

    @Override
    public boolean checkHit(int r, int c) {
        int relRow = r - row;
        int relCol = c - col;
        if (relRow >= 0 && relRow < size && relCol >= 0 && relCol < size && !hits[relRow][relCol]) {
            hits[relRow][relCol] = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean isDestroyed() {
        for (boolean[] rowHits : hits) {
            for (boolean h : rowHits) {
                if (!h) return false;
            }
        }
        return true;
    }
}

class GameBoard {
    private int[][] board = new int[8][8];
    private boolean[][] isShot = new boolean[8][8];
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public void initialize() {
        enemies.clear();
        // Tambahkan 5 musuh random
        enemies.add(new Dot());
        enemies.add(new Dot());
        enemies.add(new Line(3));
        enemies.add(new Square(2));
        enemies.add(new Line(4));
        // Papan dan tembakan direset
        for (int i = 0; i < 8; i++) {
            Arrays.fill(board[i], -1);
            Arrays.fill(isShot[i], false);
        }
    }

    public int shootAt(int r, int c) {
        if (r < 0 || r >= 8 || c < 0 || c >= 8) {
            System.out.println("Koordinat di luar papan!");
            return -1;
        }

        if (isShot[r][c]) {
            return -1;
        }

        isShot[r][c] = true;
        int hit = 0;
        for (Enemy e : enemies) {
            if (e.checkHit(r, c)) {
                hit++;
            }
        }
        board[r][c] = hit;
        return hit;
    }

    public boolean isGameOver() {
        for (Enemy e : enemies) {
            if (!e.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public void printBoard() {
        System.out.println("Papan:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isShot[i][j]) {
                    System.out.print(board[i][j]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}

public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GameBoard board = new GameBoard();
        board.initialize();
        board.printBoard();

        int shotCount = 0;

        while (!board.isGameOver()) {
            System.out.print("\nInput Koordinat Tembakan:\nRow: ");
            int r = input.nextInt()-1;
            System.out.print("Column: ");
            int c = input.nextInt()-1;

            int hit = board.shootAt(r, c);
            if (hit == -1) {
                System.out.println("Petak ini sudah pernah ditembak!");
            } else {
                System.out.println(hit + " enemy hit");
                shotCount++;
            }

            board.printBoard();
        }

        System.out.println("Semua musuh berhasil dikalahkan!");
        System.out.println("Total tembakan: " + shotCount);
    }
}
