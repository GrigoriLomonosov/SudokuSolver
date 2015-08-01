package sudokusolver.Model;

import sudokusolver.Model.Represention.Sudoku;

/**
 *
 * @author Jeroen De Meyer
 */
public class OriginalConfiguration {
    
    private final int[] config = new int[81];
    public int[] getConfig(){
        return config;
    }
    
    public OriginalConfiguration(Sudoku s){
        for(int i=0; i<config.length; i++){
            config[i] = s.getCell(i).getValue();
        }
    }
    
    //Returns true if the given index is not zero
    public boolean inConfig(int index){
        return config[index]!=0;
    }
}
