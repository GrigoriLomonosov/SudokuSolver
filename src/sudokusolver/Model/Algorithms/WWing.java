package sudokusolver.Model.Algorithms;

import sudokusolver.Model.Represention.Cell;
import java.util.List;
import java.util.Objects;

/**
 * Represents a W-Wing in a sudokuField
 * @author Jeroen De Meyer
 */
public class WWing {

    private final int valueToRemove;
    public int getValueToRemove(){
        return valueToRemove;
    }
    
    //list containing all the cells where the value should be removed
    private final List<Cell> cells;
    public List<Cell> getCells(){
        return cells;
    }
    
    public WWing(List<Cell> cells, int valueToRemove){
        this.valueToRemove = valueToRemove;
        this.cells = cells;
    }
    
    public boolean applyWWing(){
        boolean changed = false;
        for(Cell cell: cells){
            if(cell.removeFromPossibilities(valueToRemove)){
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.valueToRemove;
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
        final WWing other = (WWing) obj;
        if (this.valueToRemove != other.valueToRemove) {
            return false;
        }
        if (!Objects.equals(this.cells, other.cells)) {
            return false;
        }
        return true;
    }
}
