import java.util.Random;
import java.util.Scanner;

public class less4 {

    public static char[][] map;
    public static final int SIZE = 4;

    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_0 = '0';

    public static Scanner scan = new Scanner(System.in);
    public static Random rand = new Random();


    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if(checkWin(DOT_X)){
                System.out.println("Победил человек");
                break;
            }

            aiTurn();
            printMap();
            if(checkWin(DOT_0)){
                System.out.println("Победил компьютер");
                break;
            }
            if(isMapFull()){
                System.out.println("Ничья");
                break;
            }
        }
    }


    public static boolean checkWin(char symbol) {
        int [] countSymbolRow = new int [SIZE]; //счетчик символов в ряду
        int [] countSymbolColumn = new int [SIZE]; //счетчик символов в колонке
        boolean [] countSymbolDiag = new boolean[2]; //счетчик символов в диагонали

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symbol) {
                    countSymbolRow [i] ++;
                    countSymbolColumn [j] ++;
                }
            }
        }
        for (int i=0; i<SIZE; i++) {
            if (map[i][i]==symbol) {
                countSymbolDiag[0] = countSymbolDiag [0] && true;
            } else {
                countSymbolDiag[0] = countSymbolDiag [0] && false;
            }
        }
        for (int i=SIZE-1; i>=0; i--) {
            if (map[i][SIZE-i-1]==symbol) {
                countSymbolDiag[1] = countSymbolDiag [1] && true;
            } else {
                countSymbolDiag[1] = countSymbolDiag [1] && false;
            }
        }
        for (int i = 0; i < SIZE; i++) {
            if (countSymbolRow[i]==SIZE) return true;
            if (countSymbolColumn[i]==SIZE) return true;
        }
        if (countSymbolDiag[0]==true) return true;
        if (countSymbolDiag[1]==true) return true;
        return false;

    }


    private static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x,y));
        map[x][y]= DOT_0;
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = scan.nextInt() - 1;
            y = scan.nextInt() - 1;
        } while (!isCellValid(x,y));
        map[x][y] = DOT_X;
    }

    private static boolean isCellValid(int x, int y) {
        if (x<0 || x >= SIZE || y < 0 || y >= SIZE) return  false;
        if (map[x][y]==DOT_EMPTY) return true;
        return false;
    }

    private static void printMap() {
        for (int i = 0; i <= SIZE; i++){
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i+1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    private static void initMap() {
        map = new char [SIZE][SIZE];
        for (int i = 0; i<SIZE ;i++){
            for(int j = 0; j<SIZE; j++){
                map[i][j] = DOT_EMPTY;

            }
        }
    }
    public static boolean isMapFull(){
        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(map[i][j] == DOT_EMPTY) return false;

            }

        }
        return true;
    }
}
