package sudokusolver.Model.Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import sudokusolver.Model.Represention.Cell;
import sudokusolver.Model.Represention.House;
import sudokusolver.Model.Represention.Sudoku;

/**
 *
 * @author Jeroen De Meyer
 */
public class XWingAlg extends Algorithm{

    public XWingAlg(Sudoku sudoku){
        super(sudoku);
    }
    
    @Override
    public boolean apply(){
        boolean changed = false;
        Set<XWing> rowWings = new HashSet<>();
        rowWings.addAll(findXWings(sudoku.getRow(), true));
        Set<XWing> colWings = new HashSet<>();
        colWings.addAll(findXWings(sudoku.getCol(), false));
        for(XWing xw: rowWings){
            Set<Cell> cells = new HashSet<>();
            cells.add(xw.getCell1());
            cells.add(xw.getCell2());
            cells.add(xw.getCell3());
            cells.add(xw.getCell4());
            for(Cell cell: sudoku.getEmptyCells()){
                if((cell.getColumn()==xw.getCell1().getColumn() || cell.getColumn()==xw.getCell2().getColumn()) && !cells.contains(cell)){
                    if(cell.removeFromPossibilities(xw.getValue())){
                        changed = true;
                    }
                }
            }
        }
        for(XWing xw: colWings){
            Set<Cell> cells = new HashSet<>();
            cells.add(xw.getCell1());
            cells.add(xw.getCell2());
            cells.add(xw.getCell3());
            cells.add(xw.getCell4());
            for(Cell cell: sudoku.getEmptyCells()){
                if((cell.getRow()==xw.getCell1().getRow() || cell.getRow()==xw.getCell2().getRow()) && !cells.contains(cell)){
                    if(cell.removeFromPossibilities(xw.getValue())){
                        changed = true;
                    }
                }
            }
        }
        return changed;
    }
    
    public List<XWing> findXWings(List<House> houses, boolean comparingRows){
        List<XWing> results = new ArrayList<>();
        for(int i=1; i<=sudoku.getTotalDim(); i++){
            List<SortedSet<Cell>> possibilities = new ArrayList<>();
            for(House h2: houses){
                SortedSet<Cell> temp = h2.occurrences(i);
                if(temp.size()==2){
                    possibilities.add(temp);
                }
            }         
            for(int j=0; j<possibilities.size(); j++){
                for(int k=j+1; k<possibilities.size(); k++){
                    Cell cell1 = possibilities.get(j).first();
                    Cell cell2 = possibilities.get(j).last();
                    Cell cell3 = possibilities.get(k).first();
                    Cell cell4 = possibilities.get(k).last();
                    if(comparingRows){
                        if(cell1.getColumn()==cell3.getColumn() && cell2.getColumn()==cell4.getColumn()){
                            results.add(new XWing(i, cell1, cell2, cell3, cell4));
                        }
                    }
                    else{
                        if(cell1.getRow()==cell3.getRow() && cell2.getRow()==cell4.getRow()){
                            results.add(new XWing(i, cell1, cell2, cell3, cell4));
                        }
                    }
                }
            }
        }
        return results;
    }
}
