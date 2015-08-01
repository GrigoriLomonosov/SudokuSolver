package sudokusolver.Model;

import sudokusolver.Model.Represention.Sudoku;
import sudokusolver.Model.Represention.Cell;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Reads from a file of to randomly construct 100 sudokus with 17 clues. 
 * The sudokus are selected from a list of 49151 possibilities.
 * @author Jeroen De Meyer
 */
public class SudokuCollection {
    
    //The file in the classpath with the questions (49151 items)
    private String input = "/resources/questions.txt";
    private final int numberOfLines = 49151;
    //The number of sudokus selected from the list
    private final int numberOfSudokus = 100;
    //The dimension of a sudokuBlock
    private static final int DIMENSION = 3;
    //The collection of selected sudokus
    private final List<Sudoku> sudokuCollection = new ArrayList<>();
    public List<Sudoku> getSudokuCollection(){return sudokuCollection;}
    
    public SudokuCollection(){
        makeCollection();
    }
    
    public SudokuCollection(String file){
        input = file;
        makeCollection();
    }

    private void makeCollection(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                        SudokuCollection.class.getResourceAsStream(input)));
            String line = reader.readLine();
            int counter = 0;
            Set<Integer> ran = randomSud();
            while(line!=null){
                if(ran.contains(counter)){
                    makeSudoku(line, counter);
                }
                line = reader.readLine();
                counter++;
            }
        }catch(IOException e){
            System.out.println("Couldn't read file");
        }
    }
    
    //Make a set of 100 random numbers between 0 and 49150 (included) without
    //any of the numbers in the given set.
    private Set<Integer> randomSud(){
        Set<Integer> results = new HashSet<>();
        Random rd = new Random();
        while(results.size() < numberOfSudokus){
            results.add(rd.nextInt(numberOfLines));
        }
        return results;
    }

    private void makeSudoku(String line, int id){
        int numberOfCells = (int) Math.pow((Math.pow(DIMENSION, 2)),2);
        Cell[]values = new Cell[numberOfCells];
        for(int i=0; i<numberOfCells; i++){
            SortedSet<Integer> possibilities = makePossibilities();
            int k = Character.getNumericValue(line.charAt(i));
            if(k!=0){
                possibilities.clear();
            }
            values[i] = new Cell(i,possibilities,k);
        }
        sudokuCollection.add(new Sudoku(values, id));   
    }
    
    private SortedSet<Integer> makePossibilities(){
        SortedSet<Integer> results = new TreeSet<>();
        for(int i=1; i<(Math.pow(DIMENSION, 2)+1); i++){
            results.add(i);
        }
        return results;
    }
}
