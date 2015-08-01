package sudokusolver.Model.Algorithms;

import java.util.HashSet;
import java.util.Set;
import sudokusolver.Model.Represention.Cell;
import sudokusolver.Model.Represention.Sudoku;

/**
 *
 * @author Jeroen De Meyer
 */
public class CandidateCheckerAlg extends Algorithm{
    
    public CandidateCheckerAlg(Sudoku sudoku){
        super(sudoku);
    }
    
    @Override
    public boolean apply(){
        Set<Cell> results = new HashSet<>();
        for(Cell cell: sudoku.getEmptyCells()){
            if(cell.getPossibilities().size()==1){
                results.add(cell);
            }
        }
        //Extra loop to avoid concurrentModificationError
        for(Cell cell: results){
            sudoku.setCellValue(cell, cell.getPossibilities().first());
        }
        return !results.isEmpty();
    }
}
