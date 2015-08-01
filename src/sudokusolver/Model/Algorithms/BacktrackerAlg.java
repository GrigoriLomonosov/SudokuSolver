package sudokusolver.Model.Algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import sudokusolver.Model.Represention.Cell;
import sudokusolver.Model.Represention.Sudoku;

/**
 *
 * @author Jeroen De Meyer
 */
public class BacktrackerAlg extends Algorithm{
    
    private final int[]values = new int[81];

    private final Map<Integer, Set<Integer>> rows = makeMaps();
    private final Map<Integer, Set<Integer>> cols = makeMaps();
    private final Map<Integer, Set<Integer>> blocks = makeMaps();
    
    public BacktrackerAlg(Sudoku sudoku){
        super(sudoku);
        int counter = 0;
        for(Cell cell: sudoku.getValues()){
            values[counter] = cell.getValue();
            counter++;
        }
        for(int i=0; i<values.length; i++){
            rows.get(i/9).add(values[i]);
            cols.get(i%9).add(values[i]);
            blocks.get((((i/9)/3)*3)+((i%9)/3)).add(values[i]);
        }
    }
    
    @Override
    public boolean apply(){
        findSolution(values);
        for(int i=0; i<values.length; i++){
            if(sudoku.getCell(i).isEmpty()){
                sudoku.setCellValue(sudoku.getCell(i), values[i]);
            }
        }
        return false;
    }
    
    public boolean findSolution(int[] values){
        for(int i=0; i<values.length; i++){
            if(values[i]!=0){
                continue;
            }
            for(Integer number: sudoku.getCell(i).getPossibilities()){
                if(isPossible(i,number)){
                    values[i] = number;
                    rows.get(i/9).add(values[i]);
                    cols.get(i%9).add(values[i]);
                    blocks.get((((i/9)/3)*3)+((i%9)/3)).add(values[i]);
                    if(findSolution(values)){
                        return true;
                    } else{
                        rows.get(i/9).remove(values[i]);
                        cols.get(i%9).remove(values[i]);
                        blocks.get((((i/9)/3)*3)+((i%9)/3)).remove(values[i]);
                        values[i] = 0;
                    }
                }
            }
            return false;
        }
        return true;
    }
    
    public boolean isPossible(int numberOfCell, int value){
        return !rows.get(numberOfCell/9).contains(value) &&
                !cols.get(numberOfCell%9).contains(value) &&
                !blocks.get((((numberOfCell/9)/3)*3)+((numberOfCell%9)/3)).contains(value);
    }
    
    private Map<Integer, Set<Integer>> makeMaps(){
        Map<Integer, Set<Integer>> m = new HashMap<>();
        for(int i=0; i<9; i++){
            m.put(i, new HashSet<>());
        }
        return m;
    }
}
