import java.util.Arrays;
import java.util.Random;

public class Utils {

    public final static char[] colors = {'R', 'G', 'B'};
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[41m"; // "\033[0;31m";     // RED
    private static final String GREEN = "\033[42m";//"\033[0;32m";   // GREEN
    private static final String BLUE = "\033[44m"; // "\033[0;34m";    // BLUE


    public static void printMatrix(char [][] matrix){
        String color;
        for(int i = 0; i < matrix.length * 4; i++)
            System.out.print("-");
        System.out.println(" ");
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                switch(matrix[i][j]){
                    case 'R': color = RED; break;
                    case 'G': color = GREEN; break;
                    case 'B': color = BLUE; break;
                    default: color = RESET; 
                }
                if(j == 0)
                    System.out.print("|" + color + " " + matrix[i][j] + " " + RESET + "|");
                else
                    System.out.print(color + " " + matrix[i][j] + " " + RESET + "|");
            }
            System.out.println("");
            for(int k = 0; k < matrix.length * 4; k++)
                System.out.print("-");
                System.out.println("");
        }
        System.out.print(RESET);
    }
    
    public static char [][] createMatrix(int n){
        char [][] matrix = new char[n][n];
        Random rnd = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = colors[rnd.nextInt(colors.length)];
            }
        }
        return matrix;
    }

    public static float f(char[][] matrix, int movs, int row, int column){           
        float alpha1 = 0.1f;
        float alpha2 = 0.2f;
        float alpha3 = 0.2f;
        float alpha4 = 0.25f;
        float alpha5 = 0.25f;
        return ((alpha1 * g(movs)) + (alpha2 * h1(matrix, row)) + (alpha3 * h2(matrix, column)) + (alpha4 * h3(matrix, row) + (alpha5 * h4(matrix, column))));
    }

    private static float g(int movs){
        return movs / 100;
    }

    public static float h1(char[][] matrix, int row){
        int [] n_colors = new int[3]; 
        n_colors[0] = numberInRow(matrix, 'R', row);
        n_colors[1] = numberInRow(matrix, 'G', row);
        n_colors[2]= numberInRow(matrix, 'B', row);
        Arrays.sort(n_colors);
        return (float)(n_colors[2] - n_colors[1] - n_colors[0] + 1) / 6;
    }

    public static float h2(char[][] matrix, int column){
        int [] n_colors = new int[3]; 
        n_colors[0] = numberInColumn(matrix, 'R', column);
        n_colors[1] = numberInColumn(matrix, 'G', column);
        n_colors[2]= numberInColumn(matrix, 'B', column);
        Arrays.sort(n_colors);
        return (float)(n_colors[2] - n_colors[1] - n_colors[0] + 1) / 6;
    }

    private static float h3(char[][] matrix, int row){
        return (float)rowRepeat(matrix, row) / 4;
    }

    private static float h4(char[][] matrix, int column){
        return (float)columnRepeat(matrix, column) / 4;
    }

    private static int numberInRow(char [][] matrix, char character, int row){
        int num = 0;
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[row][i] == character)
                num++;
        }
        return num;
    }

    private static int numberInColumn(char [][] matrix, char character, int column){
        int num = 0;
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][column] == character)
                num++;
        }
        return num;
    }

    public static int rowRepeat(char[][] matrix, int row){
        int cont = 0;
        char c = matrix[row][0];
        for (int i = 1; i < matrix.length; i++) {
            if(matrix[row][i] == c)
                cont++;
            c = matrix[row][i];
        }
        return cont;
    }

    public static int columnRepeat(char[][] matrix, int column){
        int cont = 0;
        char c = matrix[0][column];
        for (int i = 1; i < matrix.length; i++) {
            if(matrix[i][column] == c)
                cont++;
            c = matrix[i][column];
        }
        return cont;
    }

    public static String matrixToStr(char[][] matrix){
        StringBuilder cad = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                cad.append(matrix[i][j]);
            }
        }
        return cad.toString();


    }
    
    public static char [][] strToMatrix(String str, int n){
        char [][] matrix = new char[n][n];
        int row = 0;
        int column = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            matrix[row][column++] = str.charAt(i);
            if(column >= n - 1){
                row++;
                column = 0;
            }
        }
        return matrix;
    }

    public static char[][] copyMatrix(char [][] matrix){
        char [][] mat = new char[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                mat[i][j] = matrix[i][j];
            }
        }
        return mat;
    }

    public static int factorial(int x){
        int f = 1;
        for (int i = 2; i <= x; i++) {
            f *= i;
        }
        return f;
    }

}