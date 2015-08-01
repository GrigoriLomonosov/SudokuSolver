package sudokusolver.Model.Represention;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Jeroen De Meyer
 */
public class House extends SudokuObject{

    private final Set<Integer> values = new HashSet<>();
    public Set<Integer> getValues(){
        return values;
    }
    
    private final Set<Cell> cells;
    public Set<Cell> getCells(){
        return cells;
    }
    
    private final Set<Cell> emptyCells = new HashSet<>();
    public Set<Cell> getEmptyCells(){
        return emptyCells;
    }
    
    public House(int id, Set<Cell> cells){
        super(id);
        this.cells = cells;
        for(Cell cell: cells){
            if(cell.isEmpty()){
                emptyCells.add(cell);
            }
            else{
                values.add(cell.getValue());
            }
        }
    }
    
    public boolean addValueToHouse(Integer i){
        return values.add(i);
    }
    
    public boolean removeValueFromHouse(Integer i){
        return values.remove(i);
    }
    
    public boolean removeFromEmptyCells(Cell cell){
        return emptyCells.remove(cell);
    }
    
    public SortedSet<Cell> occurrences(int i){
        SortedSet<Cell> results = new TreeSet<>();
        for(Cell cell: getEmptyCells()){
            if(cell.getPossibilities().contains(i)){
                results.add(cell);
            }
        }
        return results;
    }
}
