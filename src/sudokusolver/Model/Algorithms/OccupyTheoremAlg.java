package sudokusolver.Model.Algorithms;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import sudokusolver.Model.Represention.Cell;
import sudokusolver.Model.Represention.Sudoku;

/**
 *
 * @author Jeroen De Meyer
 */
public class OccupyTheoremAlg extends Algorithm{

    public OccupyTheoremAlg(Sudoku sudoku){
        super(sudoku);
    }
    
    @Override
    public boolean apply(){
        boolean changed = false;
        for(PreemptiveSet peSet: searchPreemptiveSets()){
            Cell peCell = peSet.getCells().first();
            if(peSet.inOneColumn()){
                for(Cell cell: sudoku.getEmptyCells()){
                    if((cell.getColumn()==peCell.getColumn()) && !peSet.contains(cell)){
                        for(Integer i: peSet.getValues()){
                            if(cell.removeFromPossibilities(i)){
                                changed = true;
                            }
                        }
                    }
                }
            }
            if(peSet.inOneRow()){
                for(Cell cell: sudoku.getEmptyCells()){
                    if((cell.getRow() == peCell.getRow()) && !peSet.contains(cell)){
                        for(Integer i: peSet.getValues()){
                            if(cell.removeFromPossibilities(i)){
                                changed = true;
                            }
                        }
                    }
                }
            }
            if(peSet.inOneBlock()){
                for(Cell cell: sudoku.getEmptyCells()){
                    if((cell.getBlock() == peCell.getBlock()) && !peSet.contains(cell)){
                        for(Integer i: peSet.getValues()){
                           if(cell.removeFromPossibilities(i)){
                                changed = true;
                            }
                        }
                    }
                }
            }
        }
        return changed;
    }
    
    private Set<PreemptiveSet> searchPreemptiveSets(){
        Set<PreemptiveSet> results = new HashSet<>();
        for(Cell cell1: sudoku.getEmptyCells()){
            SortedSet set1 = cell1.getPossibilities();
            if(set1.size() > 1){
                SortedSet<Cell> preSet = new TreeSet<>();
                preSet.add(cell1);
                for(Cell cell2: sudoku.getEmptyCells()){
                    SortedSet set2 = cell2.getPossibilities();
                    if(set1.containsAll(set2) && set2.containsAll(set1)){
                        preSet.add(cell2);
                    }
                }
                if(preSet.size() == set1.size()){
                    results.add(new PreemptiveSet(preSet));
                }
            }
        }
        return results;
    }
}
