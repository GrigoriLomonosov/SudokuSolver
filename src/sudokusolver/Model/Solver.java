package sudokusolver.Model;

import java.util.HashSet;
import java.util.Set;
import sudokusolver.Model.Algorithms.Algorithm;
import sudokusolver.Model.Represention.Sudoku;
import sudokusolver.Model.Represention.Cell;
import sudokusolver.Model.Algorithms.BacktrackerAlg;
import sudokusolver.Model.Algorithms.CandidateCheckerAlg;
import sudokusolver.Model.Algorithms.HiddenSinglesAlg;
import sudokusolver.Model.Algorithms.NakedTripleAlg;
import sudokusolver.Model.Algorithms.OccupyTheoremAlg;
import sudokusolver.Model.Algorithms.PlaceFinderAlg;
import sudokusolver.Model.Algorithms.SwordfishAlg;
import sudokusolver.Model.Algorithms.WWingAlg;
import sudokusolver.Model.Algorithms.XWingAlg;

/**
 * 
 * @author Jeroen De Meyer
 */
public class Solver {

    public Sudoku solve(Sudoku sudoku){
        Algorithm[] algs = {new OccupyTheoremAlg(sudoku),
                            new WWingAlg(sudoku),
                            new NakedTripleAlg(sudoku),
                            new XWingAlg(sudoku),
                            new SwordfishAlg(sudoku),
                            new CandidateCheckerAlg(sudoku),
                            new PlaceFinderAlg(sudoku),
                            new HiddenSinglesAlg(sudoku)};
        
        int count = 0;
        while(step(algs, sudoku) && count<100){
            count++;
        }
        BacktrackerAlg bta = new BacktrackerAlg(sudoku); 
        bta.apply();
        return sudoku;
    }
    
    /**
     * Calculates for every cell which numbers are still possible. 
     */
    private void calcPossibilities(Sudoku sudoku){
        //Remove impossible numbers from the possibilities from each cell
        for(Cell cell: sudoku.getEmptyCells()){
            Set<Integer> loopSet = new HashSet<>();
            loopSet.addAll(sudoku.getRow().get(cell.getRow()).getValues());
            loopSet.addAll(sudoku.getCol().get(cell.getColumn()).getValues());
            loopSet.addAll(sudoku.getBlocks().get(cell.getBlock()).getValues());
            for(Integer i: loopSet){
                cell.removeFromPossibilities(i);
            }
        }
    }
    
    private boolean step(Algorithm[] algs, Sudoku sudoku){
        calcPossibilities(sudoku);
        // return true on the last three algorithms
        for(int i=0; i<algs.length-3; i++){
            algs[i].apply();
        }
        for(int j=algs.length-3; j< algs.length; j++){
            if(algs[j].apply()){
                return true;
            }
        }
        return false;
    }
}
