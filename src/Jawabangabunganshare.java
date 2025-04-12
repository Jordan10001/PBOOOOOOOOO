Nomer 1

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Tanaman {
    protected String nama;
    protected String jenis;
    protected int harga;
    protected int jumlahTerjual;

    public Tanaman(String nama, String jenis, int harga) {
        this.nama = nama;
        this.jenis = jenis;
        this.harga = harga;
        this.jumlahTerjual = 0;
    }

    public void terjual() {
        jumlahTerjual++;
    }

    public int getHarga() {
        return harga;
    }

    public int getJumlahTerjual() {
        return jumlahTerjual;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void tampilkanInfo() {
        System.out.println("Tanaman - " + nama + " | Jenis: " + jenis + " | Harga: " + harga + " | Terjual: " + jumlahTerjual);
    }
}

class TanamanHias extends Tanaman {
    public TanamanHias(String nama, String jenis, int harga) {
        super(nama, jenis, harga);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Tanaman Hias - " + nama + " | Jenis: " + jenis + " | Harga: " + harga + " | Terjual: " + jumlahTerjual);
    }
}

class TanamanBerbunga extends Tanaman {
    private String warna;

    public TanamanBerbunga(String nama, String jenis, int harga, String warna) {
        super(nama, jenis, harga);
        this.warna = warna;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Tanaman Berbunga - " + nama + " | Jenis: " + jenis + " | Warna: " + warna + " | Harga: " + harga + " | Terjual: " + jumlahTerjual);
    }
}

class Pot {
    private String bentuk;
    private String ukuran;
    private int harga;
    private int jumlahTerjual;

    public Pot(String bentuk, String ukuran, int harga) {
        this.bentuk = bentuk;
        this.ukuran = ukuran;
        this.harga = harga;
        this.jumlahTerjual = 0;
    }

    public void terjual() {
        jumlahTerjual++;
    }

    public String getBentuk() {
        return bentuk;
    }

    public String getUkuran() {
        return ukuran;
    }

    public int getHarga() {
        return harga;
    }

    public int getJumlahTerjual() {
        return jumlahTerjual;
    }

    public void tampilkanInfo() {
        System.out.println("Pot - Bentuk: " + bentuk + " | Ukuran: " + ukuran + " | Harga: " + harga + " | Terjual: " + jumlahTerjual);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Tanaman> daftarTanaman = new ArrayList<>();
        ArrayList<Pot> daftarPot = new ArrayList<>();

        // Data awal tanaman
        daftarTanaman.add(new TanamanHias("Lidah Mertua", "Sansevieria", 15000));
        daftarTanaman.add(new TanamanHias("Baby Rubberplant", "Peperomia", 30000));
        daftarTanaman.add(new TanamanBerbunga("Parodia", "Kaktus", 20000, "Kuning"));
        daftarTanaman.add(new TanamanBerbunga("Mawar Eden", "Mawar", 50000, "Pink"));

        // Data awal pot
        daftarPot.add(new Pot("Bulat", "Kecil", 10000));
        daftarPot.add(new Pot("Kotak", "Sedang", 20000));
        daftarPot.add(new Pot("Silinder", "Besar", 30000));
        daftarPot.add(new Pot("Artistik", "Sedang", 15000));
        daftarPot.add(new Pot("Gantung", "Kecil", 25000));

        int menu;
        do {
            System.out.print("\n==MENU==\n1. Entry Penjualan\n2. Laporan Hasil Penjualan\n3. Exit\nPilih : ");
            menu = sc.nextInt();
            sc.nextLine();

            Pot potDipilih = null;
            Tanaman tanamanDipilih = null;
            if (menu == 1) {
                System.out.print("\n==OPSI==\n1. Tanaman\n2. Pot\n3. Paket\nPilih : ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Nama : ");
                    String nama = sc.nextLine();
                    System.out.print("Jenis : ");
                    String jenis = sc.nextLine();

                    boolean found = false;
                    for (Tanaman t : daftarTanaman) {
                        if (t.getNama().equalsIgnoreCase(nama) && t.getJenis().equalsIgnoreCase(jenis)) {
                            t.terjual();
                            System.out.println("Total Biaya : " + t.getHarga());
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Tanaman tidak ada");

                } else if (choice == 2) {
                    System.out.print("Bentuk : ");
                    String bentuk = sc.nextLine();
                    System.out.print("Ukuran : ");
                    String ukuran = sc.nextLine();

                    boolean found = false;
                    for (Pot p : daftarPot) {
                        if (p.getBentuk().equalsIgnoreCase(bentuk) && p.getUkuran().equalsIgnoreCase(ukuran)) {
                            p.terjual();
                            System.out.println("Total Biaya : " + p.getHarga());
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Pot tidak ada");

                } else if (choice == 3) { // Paket
                    System.out.println("---Tanaman---");
                    System.out.print("Nama Tanaman : ");
                    String nama = sc.nextLine();
                    System.out.print("Jenis Tanaman : ");
                    String jenis = sc.nextLine();

                    tanamanDipilih = null;
                    for (Tanaman t : daftarTanaman) {
                        if (t.getNama().equalsIgnoreCase(nama) && t.getJenis().equalsIgnoreCase(jenis)) {
                            tanamanDipilih = t;
                            break;
                        }
                    }

                    if (tanamanDipilih == null) {
                        System.out.println("Tanaman tidak ada");
                        continue;
                    }
                    System.out.println("---Pot---");
                    System.out.print("Bentuk Pot : ");
                    String bentuk = sc.nextLine();
                    System.out.print("Ukuran Pot : ");
                    String ukuran = sc.nextLine();

                    potDipilih = null;
                    for (Pot p : daftarPot) {
                        if (p.getBentuk().equalsIgnoreCase(bentuk) && p.getUkuran().equalsIgnoreCase(ukuran)) {
                            potDipilih = p;
                            break;
                        }
                    }

                    if (potDipilih == null) {
                        System.out.println("Pot tidak ada");
                        continue;
                    }

                    tanamanDipilih.terjual();
                    potDipilih.terjual();

                    int hargaTanaman = tanamanDipilih.getHarga();
                    int hargaPot = potDipilih.getHarga();
                    int total = hargaTanaman + hargaPot;

                    if (total > 50000) {
                        int diskon = hargaPot * 10 / 100;
                        int hargaPotDiskon = hargaPot - diskon;
                        total = hargaTanaman + hargaPotDiskon;
                        System.out.println("Harga pot semula " + hargaPot + " menjadi " + hargaPotDiskon + " karena diskon 10%");
                    }

                    System.out.println("Total Biaya : " + total);
                }

            } else if (menu == 2) {
                int totalTanaman = 0;
                int totalPot = 0;
                int totall = 0;

                System.out.println("\n==Laporan Penjualan Tanaman==");
                for (Tanaman t : daftarTanaman) {
                    if (t.getJumlahTerjual() > 0) {
                        t.tampilkanInfo();
                        totalTanaman += t.getJumlahTerjual() * t.getHarga();
                    }
                }

                System.out.println("\n==Laporan Penjualan Pot==");
                for (Pot p : daftarPot) {
                    if (p.getJumlahTerjual() > 0) {
                        p.tampilkanInfo();
                        totalPot += p.getJumlahTerjual() * p.getHarga();
                    }
                }

                System.out.println("\nTotal Penjualan Tanaman: " + totalTanaman);
                int totalpotdiskon = 0;
                totall = totalTanaman + totalPot;
                if (totall > 50000) {
                    totalpotdiskon = totalPot-(totalPot * 10 / 100);
                    System.out.println("Total Penjualan Pot : " + totalpotdiskon);
                } else {
                    System.out.println("Total Penjualan Pot : " + totalPot);
                }

            }

        } while (menu != 3);

        System.out.println("Terima kasih!");
    }
}







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
