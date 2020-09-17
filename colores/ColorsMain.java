import java.util.Random;

public class ColorsMain {
    public static void main(String[] args) {
        /*
        char [][] mat = {
            {'R', 'G', 'R', 'B', 'R'},
            {'G', 'G', 'B', 'R', 'B'},
            {'R', 'R', 'G', 'B', 'B'},
            {'B', 'G', 'R', 'G', 'B'},
            {'G', 'B', 'B', 'R', 'B'}
        };
        */
        //test2(mat);
        //test3(mat);
        test();
    }

    private static void test(){
        char [][] matrix = Utils.createMatrix(5);
        MatrixNode root = new MatrixNode(matrix, "START");
        Random rnd = new Random();
        int row = 0;//rnd.nextInt(4);
        int column = 0;//rnd.nextInt(4);
        HillClimbing hill = new HillClimbing(root.matrix, row, column);
        MatrixNode solution = hill.findSolution();
        System.out.println("Matriz inicial con r: " + row + " y c: " + column);
        Utils.printMatrix(root.matrix);
        System.out.println("Maatriz final con " + hill.movs + " movimientos");
        Utils.printMatrix(solution.matrix);
    }

    private static void test1(char [][] mat){
        Utils.printMatrix(mat);
        float r = Utils.h1(mat, 2);
        float c = Utils.h2(mat, 4);
        System.out.println("R: " + r + " | C: " + c);
        for (int i = 0; i < mat.length; i++) {
            System.out.println("Row " + i + ": " + Utils.rowRepeat(mat, i));
            System.out.println("Coluumn " + i + ": " + Utils.columnRepeat(mat, i));

        }
    }

    private static void test2(char [][] mat){
        System.out.println("Tamaño : " + mat.length);
        //test1(mat);
        MatrixNode raiz = new MatrixNode(mat, "START");
        Random rnd = new Random();
        int r = rnd.nextInt(4);
        int c = rnd.nextInt(4);
        System.out.println("R: " + r + " | C: " + c);
        System.out.println("---[MATRIZ ORIGINAL]---");
        Utils.printMatrix(mat);
        System.out.println("Factor original: " + Utils.f(mat, 8, r, c));
        raiz.createChildren(r, c);      
    }

    private static void test3(char [][] mat){
        Random rnd = new Random();
        int r = rnd.nextInt(4);
        int c = rnd.nextInt(4);
        System.out.println("Matriz inicial con fila " + r + " y columna " + c);
        Utils.printMatrix(mat);
        HillClimbing hill = new HillClimbing(mat, r, c);
        MatrixNode result = hill.findSolution();
        System.out.println("Matriz final con " + hill.movs + " movimientos");
        Utils.printMatrix(result.matrix);
    }

}
