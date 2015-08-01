package sudokusolver.Model.Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sudokusolver.Model.Represention.Cell;
import sudokusolver.Model.Represention.Sudoku;

/**
 *
 * @author Jeroen De Meyer
 */
public class WWingAlg extends Algorithm{
    
    public WWingAlg(Sudoku sudoku){
        super(sudoku);
    }
    
    @Override
    public boolean apply(){
        boolean changed = false;
        for(WWing w: createWWings(sudoku.getEmptyCells())){
            if(w.applyWWing()){
                changed = true;
            }
        }
        return changed;
    }
    
    private Set<WWing> createWWings(Set<Cell> cells){
        Set<WWing> results = new HashSet<>();
        //create all the identical duo's.
        Set<Duo> identicalDuos = new HashSet<>();
        Set<Cell> twoCandidates = new HashSet<>();
        for(Cell cell: cells){
            if(cell.getPossibilities().size()==2){
                twoCandidates.add(cell);
            }
        }        
        for(Cell first: twoCandidates){
            for(Cell second: twoCandidates){
                if(!first.equals(second) &&
                        first.getPossibilities().first()==second.getPossibilities().first() &&
                        first.getPossibilities().last()==second.getPossibilities().last()){
                    identicalDuos.add(new Duo(first, second));
                }
            }
        }

        //create WWings
        for(Duo idDuo: identicalDuos){
            List<Cell> firstBuddies = sudoku.getBuddies(idDuo.getFirst());
            List<Cell> secondBuddies = sudoku.getBuddies(idDuo.getSecond());
            List<Cell> bothBuddies = new ArrayList<>();
            bothBuddies.addAll(firstBuddies);
            bothBuddies.retainAll(secondBuddies);
            int firstPos = idDuo.getFirst().getPossibilities().first();
            int secondPos = idDuo.getFirst().getPossibilities().last();
            Set<Duo> strongLinks = strongLinks(sudoku.getEmptyCells(), firstPos);
            for(Duo duo: strongLinks){
                if(!idDuo.getFirst().equals(duo.getFirst()) && !idDuo.getFirst().equals(duo.getSecond()) 
                && !idDuo.getSecond().equals(duo.getFirst()) && !idDuo.getSecond().equals(duo.getSecond())){
                    if ((firstBuddies.contains(duo.getFirst()) && secondBuddies.contains(duo.getSecond()))
                    || (firstBuddies.contains(duo.getSecond()) && secondBuddies.contains(duo.getFirst()))){
                            results.add(new WWing(bothBuddies, secondPos));
                    }
                }
            }
            strongLinks = strongLinks(sudoku.getEmptyCells(), secondPos);
            for(Duo duo: strongLinks){
                if(!idDuo.getFirst().equals(duo.getFirst()) && !idDuo.getFirst().equals(duo.getSecond()) 
                && !idDuo.getSecond().equals(duo.getFirst()) && !idDuo.getSecond().equals(duo.getSecond())){
                    if ((firstBuddies.contains(duo.getFirst()) && secondBuddies.contains(duo.getSecond()))
                        || (firstBuddies.contains(duo.getSecond()) && secondBuddies.contains(duo.getFirst()))){
                            results.add(new WWing(bothBuddies, firstPos));
                    }
                }
            }
        }
        return  results;
    }
    
    private Set<Duo> strongLinks(Set<Cell> cells, int value){
        Set<Duo> results = new HashSet<>();
        for(Cell c1: cells){
            for(Cell c2: cells){
                if(sudoku.hasStrongLink(c1, c2, value)){
                    results.add(new Duo(c1,c2));
                }
            }
        }
        return results;
    }
}
