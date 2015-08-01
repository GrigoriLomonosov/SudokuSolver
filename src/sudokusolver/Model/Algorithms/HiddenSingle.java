package sudokusolver.Model.Algorithms;

import java.util.Objects;
import sudokusolver.Model.Represention.Cell;

/**
 *
 * @author Jeroen De Meyer
 */
public class HiddenSingle {

    private final Cell cell;
    public Cell getCell(){
        return cell;
    }
    
    private final int value;
    public int getValue(){
        return value;
    }
    
    public HiddenSingle(Cell cell, int value){
        this.cell = cell;
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.cell);
        hash = 59 * hash + this.value;
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
        final HiddenSingle other = (HiddenSingle) obj;
        if (!Objects.equals(this.cell, other.cell)) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        return true;
    }   
}
