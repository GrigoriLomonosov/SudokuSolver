package Controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import sudokusolver.Model.Model;

/**
 *
 * @author Jeroen De Meyer
 */
public class PlayingFieldCompanion implements InvalidationListener{
    
    @FXML
    public Row first;
    @FXML
    public Row second;
    @FXML
    public Row third;
    @FXML
    public Row fourth;
    @FXML
    public Row fifth;
    @FXML
    public Row sixth;
    @FXML
    public Row seventh;
    @FXML
    public Row eight;
    @FXML
    public Row ninth;
    
    private Row[] rows;
    
    private Model model;
    public void setModel(Model m){
        if(model != m){
            if(model != null){
                model.removeListener(this);
            }
            model = m;
            if(model!=null){
                model.addListener(this);
            }
        }
        first.setModel(model);
        second.setModel(model);
        third.setModel(model);
        fourth.setModel(model);
        fifth.setModel(model);
        sixth.setModel(model);
        seventh.setModel(model);
        eight.setModel(model);
        ninth.setModel(model);
    }
    
    public void initialize(){
        rows = new Row[]{first, second, third, fourth, fifth, sixth, seventh, eight, ninth};
    }
    
    @Override
    public void invalidated(Observable o){
        if(model.isWorking()){
            for(int r=0; r<rows.length; r++){
                for(int c=0; c<rows[0].getTexts().length; c++){
                    int index = (9*r)+c;
                    NumericTextArea nta = rows[r].getTexts()[c];
                    if(model.filledIn(index)){
                        nta.setText(String.valueOf(model.getRepresentation()[index]));
                    }
                }
            }
        }
        else{
            for(int r=0; r<rows.length; r++){
                for(int c=0; c<rows[0].getTexts().length; c++){
                    int index = (9*r)+c;
                    NumericTextArea nta = rows[r].getTexts()[c];
                    if(model.inConfig(index)){
                        nta.setEditable(false);
                        nta.setText(String.valueOf(model.getRepresentation()[index]));
                        nta.setStyle(
                                "-fx-font-size: 16;" +
                                "-fx-font-weight: bold;");
                    }
                    else{
                        nta.setText("");
                        nta.setEditable(true);
                    }
                }
            }
        }
    }
}
