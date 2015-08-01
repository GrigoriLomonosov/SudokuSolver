package Controller;

import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import sudokusolver.Model.Model;

/**
 *
 * @author Jeroen De Meyer
 */
public class ControlPanelCompanion implements InvalidationListener{
    
    @FXML
    public Button hintBtn;
    @FXML
    public Button solveBtn;
    @FXML
    public Button clearBtn;
    @FXML
    public Button checkBtn;
    @FXML
    public Button newSudBtn;
    
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
    }

    private ResourceBundle resources = ResourceBundle.getBundle("resources/i18n");
    public void setResources(ResourceBundle r){
        if(resources==null){
            resources = null;
        }
    }
    
    public void initialize(){

    }
    
    @Override
    public void invalidated(Observable o){
        //disable all buttons, except new sudoku field, when there isn't a sudoku field loaded.
        if(model.getConfig()!=null){
            hintBtn.setDisable(model.filledInAll());
            solveBtn.setDisable(model.filledInAll());
            clearBtn.setDisable(!model.isWorking());
            checkBtn.setDisable(!model.filledInAll());
        }
    }
    
    public void handleHint(ActionEvent e){
        model.step();
    }
    
    public void handleSolve(ActionEvent e){
        model.solve();
    }
    
    public void handleClear(ActionEvent e){
        model.clear();
    }
    
    public void handleCheck(ActionEvent e){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(resources.getString("checkDialogTitle"));
        alert.setHeaderText(resources.getString("checkDialogHeader"));
        if(model.check()){
            alert.setContentText(resources.getString("correctMessage")); 
        }
        else{
            alert.setContentText(resources.getString("inCorrectMessage"));
        }
        alert.showAndWait();
    }
    
    public void handleNewSud(ActionEvent e){
        model.makeNewQuestion();
    }
}
