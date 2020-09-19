public class HillClimbing {

    private MatrixNode raiz;
    public int movs;
    private int initialRow, initialColum;

    public HillClimbing(char[][] input, int row, int column){
        this.raiz = new MatrixNode(input, "START");
        this.movs = 0;
        this.initialRow = row;
        this.initialColum = column;
    }

    private float newFactor(MatrixNode node, int r, int c, int movs){
        switch(node.changed){
            case "TOP":
                r-=1;
                break;
            case "BUTTOM":
                r+=1;
                break;
            case "LEFT":
                c-=1;
                break;
            case "RIGHT":
                c+=1;
                break;
            default:
        }
        return Utils.f(node.matrix, movs, r, c);
    }
    
    public MatrixNode findSolution(){
        MatrixNode temp = this.raiz;
        float min = 10;
        int index_min = 0;
        float c_min = 100;
        float currentFactor;
        int currentRow = this.initialRow;
        int currentColumn = this.initialColum;
        while(true){ 
            currentFactor = Utils.f(temp.matrix, movs, currentRow, currentColumn);
            min = 10;   
            temp.createChildren(currentRow, currentColumn);
            if(temp.chilNodes.size() > 0){
                for (int i = 0; i < temp.chilNodes.size(); i++) {
                    //c_min = newFactor(temp.chilNodes.get(i), currentRow, currentColumn, movs);
                    c_min = Utils.f(temp.chilNodes.get(i).matrix, movs, currentRow, currentColumn);
                    if(c_min < min){
                        min = c_min;
                        index_min = i;
                    }
                }
            }
            if(min <= currentFactor){
                temp = temp.chilNodes.get(index_min);
                movs++;
                
                currentColumn++;
                if(currentColumn >= 3){
                    currentRow++;
                    if(currentRow >= 4)
                        currentRow = 0;
                    currentColumn = 0;
                }
                /*
                switch(temp.changed){
                    case "TOP":
                        currentRow-=1;
                        break;
                    case "BUTTOM":
                        currentRow+=1;
                        break;
                    case "LEFT":
                        currentColumn-=1;
                        break;
                    case "RIGHT":
                        currentColumn+=1;
                        break;
                    default:
                } */
                
            } else {
                break;
            }
        }
        return temp;
    }

}
