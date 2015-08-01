package sudokusolver.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import sudokusolver.Model.Represention.Sudoku;

/**
 *
 * @author Jeroen De Meyer
 */
public class Model implements Observable{
    
    private final Solver solver = new Solver();
    private List<Sudoku> suds = new SudokuCollection().getSudokuCollection();
    private final Set<Integer> zeros = new HashSet<>();    
    private Sudoku currentSolution;
    //array that will be used to represent the sudoku in the view
    private final int[] representation = new int[81];
    public int[] getRepresentation(){
        return representation;
    }
    
    private OriginalConfiguration config;
    public OriginalConfiguration getConfig(){
        return config;
    }
    
    private boolean working;
    public boolean isWorking(){
        return working;
    }
    
    private boolean startClock = false;
    public boolean startClock(){
        return startClock;
    }
    
    private boolean stopClock = false;
    public boolean stopClock(){
        return stopClock;
    }
    
    //Returns true when the number in the representation at the given index is not zero
    public boolean filledIn(int index){
        return representation[index]!=0 && index<81;
    }
    
    //Returns true when there are no more indexes in the zeros collection.
    public boolean filledInAll(){
        return zeros.isEmpty();
    }
    
    public void fillIn(int index, int value){
        if(representation[index] != value && config!=null){
            working = true;
            representation[index] = value;
            if(value==0){                
                    zeros.add(index);
            }
            else{
                zeros.remove(index);
            }
            if(filledInAll()){
                stopClock = true;
            }
            fireInvalidationEvent();
        }
    }
    
    //Returns true if the index is in the original configuration
    public boolean inConfig(int index){
        if(config!=null){
            return config.inConfig(index);
        }
        return false;
    }
    
    public void makeNewQuestion(){
        startClock = true;
        stopClock = false;
        working = false;
        if(suds.isEmpty()){
            suds = new SudokuCollection().getSudokuCollection();
        }
        currentSolution =  suds.get(0);
        suds.remove(suds.get(0));
        config = new OriginalConfiguration(currentSolution);
        zeros.clear();
        for(int index=0; index<representation.length; index++){
            representation[index] = currentSolution.getCell(index).getValue();
            if(representation[index]==0){
                zeros.add(index);
            }
        }
        solver.solve(currentSolution);
        fireInvalidationEvent();
        startClock = false;
    }
    
    public void step(){
        stopClock = true;
        if(!zeros.isEmpty()){
            working = true;
            for(Integer index: zeros){
                representation[index] = currentSolution.getCell(index).getValue();
                zeros.remove(index);
                break;
            }
            fireInvalidationEvent();
        }
    }
    
    public void solve(){
        stopClock = true;        
        if(!zeros.isEmpty()){
            working = true;
            Set<Integer> toRemove = new HashSet<>();
            for(Integer index: zeros){
                representation[index] = currentSolution.getCell(index).getValue();
                toRemove.add(index);
            }
            zeros.removeAll(toRemove);
            fireInvalidationEvent();
        }
    }
    
    public void clear(){
        working = false;
        if(config!=null){        
            zeros.clear();
            for(int index=0; index<representation.length; index++){
                representation[index] = config.getConfig()[index];
                if(representation[index]==0){
                    zeros.add(index);
                }
            }
        }
        else{
            for(int index=0; index<representation.length; index++){
                representation[index] = 0;
            }
        }
        fireInvalidationEvent();
    }
    
    public boolean check(){
        if(currentSolution!=null){
            for(int i=0; i<representation.length; i++){
                if(representation[i]!=currentSolution.getCell(i).getValue()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    //Code concerning the listeners
    private final List<InvalidationListener> listenerList = new ArrayList<>();
    @Override
    public void addListener(InvalidationListener listener){
        listenerList.add(listener);
    }
    @Override
    public void removeListener(InvalidationListener listener){
        listenerList.add(listener);
    }

    public void fireInvalidationEvent(){
        for(InvalidationListener listener: listenerList){
            listener.invalidated(this);
        }
    }
}
