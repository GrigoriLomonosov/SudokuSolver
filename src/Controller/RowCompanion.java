package Controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import sudokusolver.Model.Model;

/**
 *
 * @author Jeroen De Meyer
 */
public class RowCompanion{

    @FXML
    public NumericTextArea first;
    @FXML
    public NumericTextArea second;
    @FXML
    public NumericTextArea third;
    @FXML
    public NumericTextArea fourth;
    @FXML
    public NumericTextArea fifth;
    @FXML
    public NumericTextArea sixth;
    @FXML
    public NumericTextArea seventh;
    @FXML
    public NumericTextArea eight;
    @FXML
    public NumericTextArea ninth;
    
    private NumericTextArea[] texts;
    public NumericTextArea[] getTexts(){
        return texts;
    }
    
    private Model model;
    public void setModel(Model m){
        model = m;
    }
   
    private int rowNumber;
    public void setRowNumber(int i){
        rowNumber = i;
    }
    
    public void initialize(){
        texts = new NumericTextArea[]{first, second, third, fourth, fifth, sixth, seventh, eight, ninth};
        for(NumericTextArea nta: texts){
            nta.textProperty().addListener((ObservableValue<? extends String> o, String oldValue, String newValue) -> {
                if(newValue.trim().length()==1){
                    model.fillIn((rowNumber*9) + nta.getColNumber(), Integer.parseInt(newValue));
                    nta.setStyle(
                            "-fx-font-size: 16;");
                }
                else{
                    model.fillIn((rowNumber*9) + nta.getColNumber(), 0);
                    nta.setStyle(
                            "-fx-font-size: 10;");
                }
            });
        }
    }
}
