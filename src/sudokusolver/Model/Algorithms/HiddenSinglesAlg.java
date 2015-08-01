package sudokusolver.Model.Algorithms;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import sudokusolver.Model.Represention.Cell;
import sudokusolver.Model.Represention.House;
import sudokusolver.Model.Represention.Sudoku;

/**
 *
 * @author Jeroen De Meyer
 */
public class HiddenSinglesAlg extends Algorithm{

    public HiddenSinglesAlg(Sudoku sudoku){
        super(sudoku);
    }
    
    @Override
    public boolean apply(){
        Set<HiddenSingle> results = new HashSet<>();
        results.addAll(checkCells(sudoku.getBlocks()));
        results.addAll(checkCells(sudoku.getRow()));
        results.addAll(checkCells(sudoku.getCol()));
        for(HiddenSingle hs: results){
            sudoku.setCellValue(hs.getCell(), hs.getValue());
        }
        return !results.isEmpty();
    }
    
    private Set<HiddenSingle> checkCells(Collection<House> houses){
        Set<HiddenSingle> results = new HashSet<>();
        for(House house: houses){
            Map<Integer,SortedSet<Cell>> temp = createMap();
            for(Cell cell: house.getEmptyCells()){
                    for(Integer i: cell.getPossibilities()){
                        temp.get(i).add(cell);
                    }
            }
            for(int i=1; i<=temp.size(); i++){
                if(temp.get(i).size()==1){
                    results.add(new HiddenSingle(temp.get(i).first(),i));
                }
            }
        }
        return results;
    }
    
    private Map<Integer,SortedSet<Cell>> createMap(){
        Map<Integer,SortedSet<Cell>> results = new HashMap<>();
        for(int i=1; i<10; i++){
            results.put(i, new TreeSet<>());
        }
        return results;
    }
}
