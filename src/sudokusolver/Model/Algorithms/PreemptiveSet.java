package sudokusolver.Model.Algorithms;

import sudokusolver.Model.Represention.Cell;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Jeroen De Meyer
 */
public class PreemptiveSet {

    private final SortedSet<Integer> values = new TreeSet<>();
    public SortedSet<Integer> getValues(){
        return values;
    }
    
    private final SortedSet<Cell> cells;
    public SortedSet<Cell> getCells(){
        return cells;
    }
    
    public PreemptiveSet(SortedSet<Cell> cells){
        this.cells = cells;
        values.addAll(cells.first().getPossibilities());
    }
    
    /**
     * @return true if all the cells in the preemptive set are in the same column. 
     */
    public boolean inOneColumn(){
        int col = cells.first().getColumn();
        for(Cell cell: cells){
            if(cell.getColumn() != col){
                return false;
            }
        }
        return true;
    }
    
    /**
     * @return true if all the cells in the preemptive set are in the same row. 
     */
    public boolean inOneRow(){
        int row = cells.first().getRow();
        for(Cell cell: cells){
            if(cell.getRow() != row){
                return false;
            }
        }
        return true;
    }
    
    /**
     * @return true if all the cells in the preemptive set are in the same block. 
     */
    public boolean inOneBlock(){
        int bl = cells.first().getBlock();
        for(Cell cell: cells){
            if(cell.getBlock() != bl){
                return false;
            }
        }
        return true;
    }
    
    public boolean contains(Cell cell){
        return cells.contains(cell);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.cells);
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
        final PreemptiveSet other = (PreemptiveSet) obj;
        if (!Objects.equals(this.cells, other.cells)) {
            return false;
        }
        return true;
    }  
}
