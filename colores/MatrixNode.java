import java.util.ArrayList;

public class MatrixNode {

    public static int movs = 4;
    
    public ArrayList<MatrixNode> chilNodes = new ArrayList<MatrixNode>();
    public char[][] matrix = null;
    public String changed;
    public float factor;

    public MatrixNode(char[][] matrix, String change){
        this.matrix = matrix;
        this.changed = change;
        this.factor = 0.0f;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }

    public void createChildren(int row, int column){
        changeColor(row, column,this.matrix[row][column], "NONE");
        if(hasTop(row)){
            changeColor(row -1, column, this.matrix[row][column], "TOP");
        }
        if(hasButton(row)){
            changeColor(row + 1, column, this.matrix[row][column], "BUTTON");
        }
        if(hasLeft(column)){
            changeColor(row, column - 1, this.matrix[row][column], "LEFT");
        }
        if(hasRight(column)){
            changeColor(row, column + 1, this.matrix[row][column], "RIGHT");
        }

        /*
        for(MatrixNode n : chilNodes){
            System.out.println("Matriz nueva");
            Utils.printMatrix(n.matrix);
            System.out.println("Factor: (f(mat)): " + Utils.f(n.matrix, 9, row, column));
        }
        */
    }

    private void changeColor(int row, int column, char current, String chan){
        char [][] temp = Utils.copyMatrix(this.matrix);
        char c = temp[row][column];
        for(char color : Utils.colors){
            if(color != c && color != current){
                temp[row][column] = color;
                chilNodes.add(new MatrixNode(Utils.copyMatrix(temp), chan));
            }
        }
    }

    private  boolean hasTop(int row){
        if(row >= 1)
            return true; 
        return false;
    }

    private boolean hasButton(int row){
        if(row < this.matrix.length - 1)
            return true;
        return false;
    }

    private boolean hasLeft(int column){
        if(column >= 1)
            return true;
        return false;
    }

    private boolean hasRight(int column){
        if(column < this.matrix.length - 1)
            return true;
        return false;
    }

}
