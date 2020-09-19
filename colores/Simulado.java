import java.util.ArrayList;
import java.util.Random;

public class Simulado {

    public MatrixNode raiz;
    private int row, column, intentos;
    public int movs;
    private ArrayList<String> generados = new ArrayList<String>();
    private final float k = 0.9f;
    private boolean cambio = false;
    
    public Simulado(char [][] matrix, int row, int column, int intentos){
        this.raiz = new MatrixNode(matrix, "START");
        this.row = row;
        this.column = column;
        this.intentos = intentos;
        this.movs = 0;
    }

    public MatrixNode findSolution(){
        MatrixNode S = this.raiz;
        float T = 1;
        float deltaE;
        do {
            this.cambio = false;
            MatrixNode S1 = selectBetter(S);
            deltaE = Utils.f(S1.matrix, movs, this.row, this.column) - Utils.f(S.matrix, this.movs, this.row, this.column);
            if(deltaE < 0 && !this.generados.contains(Utils.matrixToStr(S1.matrix))){
                this.generados.add(Utils.matrixToStr(S1.matrix));
                S = S1;
                this.column++;
                if(this.column >= 3){
                    this.row++;
                    if(this.row >= 4)
                        this.row = 0;
                    this.column = 0;
                }
                cambio = true;
            } else {
                if(proba(deltaE, T)){
                //if(this.intentos-- > 0){
                    System.out.println("Entro a intento extra");
                    this.generados.add(Utils.matrixToStr(S1.matrix));
                    S = S1;
                    cambio = true;
                    this.column++;
                    if(this.column >= 3){
                        this.row++;
                        if(this.row >= 4)
                            this.row = 0;
                        this.column = 0;
                    }
                }
            }
            this.movs++;
            T = alpha(T); 
        }while(cambio);
        return S;
    }

    private MatrixNode selectBetter(MatrixNode node){
        MatrixNode temp = node;
        float min = 10;
        int index_min = 0;
        float c_min = 100;
        temp.createChildren(this.row, this.column);
        if(temp.chilNodes.size() > 0){
            for (int i = 0; i < temp.chilNodes.size(); i++) {
                c_min = Utils.f(temp.chilNodes.get(i).matrix, this.movs, this.row, this.column);
                if(c_min < min){
                    min = c_min;
                    index_min = i;
                }
            }
        }
        return temp.chilNodes.get(index_min);
    }

    public boolean proba(float deltaE, float T){
        Random rnd = new Random();
        float factor = rnd.nextFloat();
        return factor < e(deltaE, T);
    }

    private float alpha(float T){
        return T *= k;
    }

    private float e(float deltaE, float T){
        double x = (double) deltaE/T;
        return (float)Math.pow(Math.E, -x);
    }

}
