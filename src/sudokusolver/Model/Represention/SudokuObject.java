package sudokusolver.Model.Represention;

/**
 *
 * @author Jeroen De Meyer
 */
public class SudokuObject implements Comparable{
   
    //The dimension of a sudokuBlock
    protected static final int DIMENSION = 3;
    public int getDimension(){
        return DIMENSION;
    }
    protected static final int NUMBEROFCELLS = 81;
    protected static final int TOTALDIM = 9;
    public int getTotalDim(){
        return TOTALDIM;
    }
    
    private final int id;
    public int getId(){
        return id;
    } 
    
    public SudokuObject(int id){
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuObject other = (SudokuObject) obj;
        return this.id == other.id;
    }
    
    @Override
    public int compareTo(Object o){
       SudokuObject sudokuObject = (SudokuObject)o; 
       return this.getId() - sudokuObject.getId();
    }
}
