package sudokusolver.Model.Algorithms;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import sudokusolver.Model.Represention.Cell;
import java.util.SortedSet;

/**
 * Class containing 3 cells
 * @author Jeroen De Meyer
 */

public class Trio {
     
    private final Cell first;
    public Cell getLeft(){
        return first;
    }
    
    private final Cell second;
    public Cell getMiddle(){
        return second;
    }
    
    private final Cell third;
    public Cell getRight(){
        return third;
    }
    
    public Trio(Cell first, Cell second, Cell third){
        this.first = first;
        this.second = second;
        this.third= third;
    } 
    
    public boolean checkNakedTriple(){
        Set<Integer> totalPos = new HashSet<>();
        totalPos.addAll(first.getPossibilities());
        totalPos.addAll(second.getPossibilities());
        totalPos.addAll(third.getPossibilities());
        return totalPos.size() == 3; 
    }
    
    public boolean inOneBlock(){
        return first.getBlock()==second.getBlock() && second.getBlock()==third.getBlock();
    }
    
    public boolean inOneRow(){
        return first.getRow()==second.getRow() && second.getRow()==third.getRow();
    }
    
    public boolean inOneColumn(){
        return first.getColumn()==second.getColumn() && second.getColumn()==third.getColumn();
    }
    
    public boolean isMember(Cell cell){
        return first.equals(cell) || second.equals(cell) || third.equals(cell);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.first);
        hash = 23 * hash + Objects.hashCode(this.second);
        hash = 23 * hash + Objects.hashCode(this.third);
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
        final Trio other = (Trio) obj;
        if (!Objects.equals(this.first, other.first)) {
            return false;
        }
        if (!Objects.equals(this.second, other.second)) {
            return false;
        }
        if (!Objects.equals(this.third, other.third)) {
            return false;
        }
        return true;
    }
}
