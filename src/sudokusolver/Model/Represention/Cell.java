package sudokusolver.Model.Represention;

import java.util.SortedSet;

/**
 *
 * @author Jeroen De Meyer
 */
public class Cell extends SudokuObject{

    private final SortedSet<Integer> possibilities;
    public SortedSet<Integer> getPossibilities() {
        return possibilities;
    }
    
    private int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    
    private final int block;
    public int getBlock() {
        return block;
    }
    
    private final int column;
    public int getColumn(){
        return column;
    }
    
    private final int row;
    public int getRow(){
        return row;
    }
    
    public Cell(int id, SortedSet<Integer> possibilities, int value){
        super(id);
        this.possibilities = possibilities;
        this.value = value;
        column = id%TOTALDIM;
        row = id/TOTALDIM;
        block = ((row/3)*3)+(column/3);
    }
    
    public void clearPossibilities(){
        possibilities.clear();
    }
    
    public boolean removeFromPossibilities(int i){
        return possibilities.remove(i);
    }
    
    public boolean addToPossibilities(int i){
        return possibilities.add(i);
    }
    
    public boolean isBuddy(Cell cell){
        return column==cell.getColumn() || row==cell.getRow() || block==cell.getBlock();
    }
    
    public boolean isEmpty(){
        return possibilities.size() != 0;
    }
}
