package sudokusolver.Model.Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sudokusolver.Model.Represention.Cell;
import sudokusolver.Model.Represention.House;
import sudokusolver.Model.Represention.Sudoku;

/**
 *
 * @author Jeroen De Meyer
 */
public class NakedTripleAlg extends Algorithm{

    public NakedTripleAlg(Sudoku sudoku){
        super(sudoku);
    }
    
    @Override
    public boolean apply(){
        Set<Trio> loopSet = new HashSet<>();
        loopSet.addAll(findNakedTriples(sudoku.getBlocks()));
        loopSet.addAll(findNakedTriples(sudoku.getRow()));
        loopSet.addAll(findNakedTriples(sudoku.getCol()));
        for(Trio trio: loopSet){
            Set<Integer> totalPos = new HashSet<>();
            totalPos.addAll(trio.getLeft().getPossibilities());
            totalPos.addAll(trio.getMiddle().getPossibilities());
            totalPos.addAll(trio.getRight().getPossibilities());
            if(trio.inOneBlock()){
                //niet zeker of de vervanging correct is in de 3 controles...
                for(Cell cell: sudoku.getBlocks().get(trio.getLeft().getBlock()).getEmptyCells()){
                    if(!trio.isMember(cell)){
                        for(Integer i: totalPos){
                            cell.removeFromPossibilities(i);
                        }
                    }
                }
            }
            if(trio.inOneColumn()){
                for(Cell cell: sudoku.getCol().get(trio.getLeft().getColumn()).getEmptyCells()){
                    if(!trio.isMember(cell)){
                        for(Integer i: totalPos){
                            cell.removeFromPossibilities(i);
                        }
                    }
                }
            }
            if(trio.inOneRow()){
                for(Cell cell: sudoku.getRow().get(trio.getLeft().getRow()).getEmptyCells()){
                    if(!trio.isMember(cell)){
                        for(Integer i: totalPos){
                            cell.removeFromPossibilities(i);
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    private Set<Trio> findNakedTriples(List<House> houses){
        Set<Trio> results = new HashSet<>();       
        for(House house: houses){
            List<Cell> loopList = new ArrayList<>(); 
            //only work with cells who have less than 3 possibilities.
            for(Cell cell: house.getEmptyCells()){
                if(cell.getPossibilities().size()<=3){
                    loopList.add(cell);
                }
            }
            //make all possible trio's with the remaining cells.
            for(int i=0; i<loopList.size()-2; i++){
                for(int j=i+1; j<loopList.size(); j++){
                    for(int k=j+1; k<loopList.size(); k++){
                        Trio trio = new Trio(loopList.get(i), loopList.get(j),loopList.get(k));
                        if(trio.checkNakedTriple()){
                            results.add(trio);
                        }
                    }
                }
            }
        }
        return results;
    }
}
