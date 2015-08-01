package Controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import sudokusolver.Model.Model;

/**
 *
 * @author Jeroen De Meyer
 */
public class Row extends HBox{
    
    private final RowCompanion comp;

    private int rowNumber;
    public int getRowNumber(){
        return rowNumber;
    }
    public void setRowNumber(int i){
        rowNumber = i;
        comp.setRowNumber(i);
    }
    
    private Model model;
    public void setModel(Model m){
        model = m;
        comp.setModel(model);
    }
    
    public Row(){
        try{
            FXMLLoader loader = new FXMLLoader(
                Row.class.getResource("Row.fxml"));
            loader.setRoot(this);
            this.comp = new RowCompanion();
            loader.setController(comp);
            loader.load();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    
    public NumericTextArea[] getTexts(){
        return comp.getTexts();
    }
}
