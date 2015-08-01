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
public class SwordfishAlg extends Algorithm{
    
    public SwordfishAlg(Sudoku sudoku){
        super(sudoku);
    }
    
    @Override
    public boolean apply(){
        boolean changed = false;
        Set<Swordfish> loopSet = findSwordfishRows();
        loopSet.addAll(findSwordfishColumns());
        for(Swordfish sw: loopSet){
            for(House house: sw.getHouses()){
                for(Cell cell: house.getEmptyCells()){
                    if(cell.isEmpty() && !sw.isSwordfishCell(cell)){
                        if(cell.removeFromPossibilities(sw.getValue())){
                            changed = true;
                        }
                    }
                }
            }
        }
        return changed;
    }
    
    public Set<Swordfish> findSwordfishRows(){
        Set<Swordfish> results = new HashSet<>();
        for(int i=1; i<=sudoku.getTotalDim(); i++){
            List<Set<Cell>> poss = new ArrayList<>();
            for(House house: sudoku.getRow()){
                Set<Cell> temp = house.occurrences(i);
                if(temp.size()==2 || temp.size()==3){
                    poss.add(temp);
                }
            }
            for(int j=0; j<poss.size(); j++){
                for(int k=j+1; k<poss.size(); k++){
                    for(int l=k+1; l<poss.size(); l++){
                        Set<House> colsSet = getDifferentCols(poss.get(j), poss.get(k), poss.get(l));
                        if(colsSet.size()==3){
                            List<House> colsList = new ArrayList<>();
                            colsList.addAll(colsSet);
                            Set<Cell> swCells = new HashSet<>();
                            swCells.addAll(poss.get(j));
                            swCells.addAll(poss.get(k));
                            swCells.addAll(poss.get(l));
                            results.add(new Swordfish(i, colsList, swCells));
                        }
                    }
                }
            }
        }
        return results;
    }
    
    public Set<Swordfish> findSwordfishColumns(){
        Set<Swordfish> results = new HashSet<>();
        for(int i=1; i<=sudoku.getTotalDim(); i++){
            List<Set<Cell>> poss = new ArrayList<>();
            for(House house: sudoku.getCol()){
                Set<Cell> temp = house.occurrences(i);
                if(temp.size()==2 || temp.size()==3){
                    poss.add(temp);
                }
            }
            for(int j=0; j<poss.size(); j++){
                for(int k=j+1; k<poss.size(); k++){
                    for(int l=k+1; l<poss.size(); l++){
                        Set<House> rowsSet = getDifferentRows(poss.get(j), poss.get(k), poss.get(l));
                            if(rowsSet.size()==3){
                                List<House> rowsList = new ArrayList<>();
                                rowsList.addAll(rowsSet);
                                Set<Cell> swCells = new HashSet<>();
                                swCells.addAll(poss.get(j));
                                swCells.addAll(poss.get(k));
                                swCells.addAll(poss.get(l));
                                results.add(new Swordfish(i, rowsList, swCells));
                        }
                    }
                }
            }
        }
        return results;
    }
    
    private Set<House> getDifferentCols(Set<Cell> l1, Set<Cell> l2, Set<Cell> l3){
        Set<House> results = new HashSet<>();
        for(Cell cell: l1){
            results.add(sudoku.getCol().get(cell.getColumn()));
        }
        for(Cell cell: l2){
            results.add(sudoku.getCol().get(cell.getColumn()));
        }
        for(Cell cell: l3){
            results.add(sudoku.getCol().get(cell.getColumn()));
        }
        return results;
    }
    
    private Set<House> getDifferentRows(Set<Cell> l1, Set<Cell> l2, Set<Cell> l3){
        Set<House> results = new HashSet<>();
        for(Cell cell: l1){
            results.add(sudoku.getRow().get(cell.getRow()));
        }
        for(Cell cell: l2){
            results.add(sudoku.getRow().get(cell.getRow()));
        }
        for(Cell cell: l3){
            results.add(sudoku.getRow().get(cell.getRow()));
        }
        return results;
    }
}
