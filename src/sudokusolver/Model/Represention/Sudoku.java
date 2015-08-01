package sudokusolver.Model.Represention;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classic 9x9 sudokuField
 * @author Jeroen De Meyer
 */
public class Sudoku extends SudokuObject{
    
    //An arrayList consisting of lists of cells per column.
    private final List<House> col = new ArrayList<>();
    public List<House> getCol(){
        return col;
    }
    
    //An arrayList consisting of lists of cells per row.
    private final List<House>row = new ArrayList<>();
    public List<House> getRow(){
        return row;
    }
       
    private final List<House> blocks = new ArrayList<>();
    public List<House> getBlocks(){
        return blocks;
    }
    
    private final Cell[] values;
    public Cell[] getValues(){
        return values;
    }
    
    private final Set<Cell> emptyCells = new HashSet<>();
    public Set<Cell> getEmptyCells(){
        return emptyCells;
    }

    public Sudoku(Cell[]val, int id){
        super(id);
        values = val;
        createSudoku(values);
    }
    
    private Map<Integer,Set<Cell>> createMaps(){
        Map<Integer, Set<Cell>> m = new HashMap<>();
        for(int i=0; i<TOTALDIM; i++){
            m.put(i, new HashSet<>());
        }
        return m;
    }
    
    private void createSudoku(Cell[]val){
        Map<Integer, Set<Cell>> r = createMaps();
        Map<Integer, Set<Cell>> c = createMaps();
        Map<Integer, Set<Cell>> bl = createMaps();
        for(int i=0; i<val.length; i++){
            r.get(i/9).add(val[i]);
            c.get(i%9).add(val[i]);
            bl.get((((i/9)/3)*3)+((i%9)/3)).add(val[i]);
            if(val[i].isEmpty()){
                emptyCells.add(val[i]);
            }
        }
        for(int j=0; j<r.size(); j++){
            row.add(new House(j,r.get(j)));
            col.add(new House(j, c.get(j)));
            blocks.add(new House(j, bl.get(j)));
        }
    }
       
    public void removeFromEmptyCells(Cell cell){
        emptyCells.remove(cell);
    }
    
    public Cell getCell(int i){
        return values[i];
    }

    public void setCellValue(Cell cell, int i){
        if(cell.isEmpty() && cell.getPossibilities().contains(i)){
            row.get(cell.getRow()).addValueToHouse(i);
            col.get(cell.getColumn()).addValueToHouse(i);
            blocks.get(cell.getBlock()).addValueToHouse(i);
            row.get(cell.getRow()).removeFromEmptyCells(cell);
            col.get(cell.getColumn()).removeFromEmptyCells(cell);
            blocks.get(cell.getBlock()).removeFromEmptyCells(cell);
            emptyCells.remove(cell);
            cell.clearPossibilities();
            cell.setValue(i);
        }
    }
    /**
     * @param cell
     * @return a list of all the cells who have the column, row or block in common with a given cell, without the cell itself.
     */
    public List<Cell> getBuddies(Cell cell){
        List<Cell> results = new ArrayList<>();
        for(int i=0; i<values.length; i++){
            if(values[i].getColumn()==cell.getColumn() || values[i].getRow()==cell.getRow() || values[i].getBlock()==cell.getBlock()){
                results.add(values[i]);
            }
        }
        results.remove(cell);
        return results;
    }
    
    /**
     * @param c1
     * @param c2
     * @param value
     * @return true when there are only two candidates in a row, column or block with the value as possibility.
     */
    public boolean hasStrongLink(Cell c1, Cell c2, int value){
        if(c1.getPossibilities().contains(value) && c2.getPossibilities().contains(value)){
            boolean rowCheck = false;
            boolean columnCheck = false;
            boolean blockCheck = false;
            int counter;
            //check row
            if(c1.getRow()==c2.getRow()){
                counter = 0;
                for(Cell cell: emptyCells){
                    if(cell.getRow()==c1.getRow() && !cell.equals(c1) && !cell.equals(c2) 
                        && cell.getPossibilities().contains(value)){
                        counter++;
                    }
                }
                rowCheck = counter==0;
            }
            //check column
            if(c1.getColumn()==c2.getColumn()){
                counter = 0;
                for(Cell cell: emptyCells){
                    if(cell.getColumn()==c1.getColumn() && !cell.equals(c1) && !cell.equals(c2) 
                        && cell.getPossibilities().contains(value)){
                        counter++;
                    }
                }
                columnCheck = counter==0;
            }
            //check block
            if(c1.getBlock()==c2.getBlock()){
                counter = 0;
                for(Cell cell: emptyCells){
                    if(cell.getBlock()==c1.getBlock() && !cell.equals(c1) && !cell.equals(c2) 
                        && cell.getPossibilities().contains(value)){
                        counter++;
                    }
                }
                blockCheck = counter==0;
            }
            return rowCheck || columnCheck || blockCheck;
        }
        return false;
    }
}
