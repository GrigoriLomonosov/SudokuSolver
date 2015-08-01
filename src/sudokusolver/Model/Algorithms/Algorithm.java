package sudokusolver.Model.Algorithms;

import sudokusolver.Model.Represention.Sudoku;

/**
 *
 * @author Jeroen De Meyer
 */
public abstract class Algorithm {

    protected final Sudoku sudoku;
    
    public Algorithm(Sudoku sudoku){
        this.sudoku = sudoku;
    }
    
    public abstract boolean apply();
}
