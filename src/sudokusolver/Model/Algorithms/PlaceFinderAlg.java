package sudokusolver.Model.Algorithms;

import java.util.HashSet;
import java.util.Set;
import sudokusolver.Model.Represention.Cell;
import sudokusolver.Model.Represention.Sudoku;

/**
 *
 * @author Jeroen De Meyer
 */
public class PlaceFinderAlg extends Algorithm{

    public PlaceFinderAlg(Sudoku sudoku){
        super(sudoku);
    }
    
    @Override
    public boolean apply(){
        //Made new Sets to avoid changing the emptyCells-Set while iterating (ConcurrentModificaton).
        Set<Cell> colResults = new HashSet<>();
        Set<Cell> rowResults = new HashSet<>();
        Set<Cell> blockResults = new HashSet<>();
        for(Cell cell: sudoku.getEmptyCells()){
            if(sudoku.getCol().get(cell.getColumn()).getEmptyCells().size()==1){
                colResults.add(cell);
            }
            if(sudoku.getRow().get(cell.getRow()).getEmptyCells().size()==1){
                rowResults.add(cell);
            }
            if(sudoku.getBlocks().get(cell.getBlock()).getEmptyCells().size()==1){
                blockResults.add(cell);
            }
        }
        for(Cell cell: colResults){
            sudoku.setCellValue(cell, searchMissingValue(sudoku.getCol().get(cell.getColumn()).getValues()));
        }
        for(Cell cell: rowResults){
            sudoku.setCellValue(cell, searchMissingValue(sudoku.getRow().get(cell.getRow()).getValues()));
        }
        for(Cell cell: blockResults){
            sudoku.setCellValue(cell, searchMissingValue(sudoku.getBlocks().get(cell.getBlock()).getValues()));
        }
        return !colResults.isEmpty() || !rowResults.isEmpty() || !blockResults.isEmpty();
    }
    
    private int searchMissingValue(Set<Integer> s){
        int[]possibilities = {1,2,3,4,5,6,7,8,9};
        for(int i=0; i<possibilities.length; i++){
            if(s.add(possibilities[i])){
                return possibilities[i];
            }
        }
        return -1;
    }
}
