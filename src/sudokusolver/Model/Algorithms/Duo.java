package sudokusolver.Model.Algorithms;

import sudokusolver.Model.Represention.Cell;
import java.util.Objects;

/**
 * Represents a couple of cells with each exact two candidates.
 * @author Jeroen De Meyer
 */
public class Duo {

    private final Cell first;
    public Cell getFirst(){
        return first;
    }
    
    private final Cell second;
    public Cell getSecond(){
        return second;
    }
    
    public Duo(Cell first, Cell second){
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (Objects.hashCode(this.first)+Objects.hashCode(this.second));
        return hash;
    }

    //Duo's where the cells are in opposite order are also considered equal.
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Duo other = (Duo) obj;
        if (!Objects.equals(this.first, other.first) && !Objects.equals(this.first, other.second)) {
            return false;
        }
        if (!Objects.equals(this.second, other.second) && !Objects.equals(this.second, other.first)) {
            return false;
        }
        return true;
    }   
}
