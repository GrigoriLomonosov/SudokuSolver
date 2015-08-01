package Controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import sudokusolver.Model.Model;

/**
 *
 * @author Jeroen De Meyer
 */
public class PlayingField extends GridPane{
    
    private final PlayingFieldCompanion comp;
    
    public Model model;
    public Model getModel(){
        return model;
    }
    public void setModel(Model m){
        model = m;
        comp.setModel(model);
    }
    
    public PlayingField(){
        try{
            FXMLLoader loader = new FXMLLoader(
                PlayingField.class.getResource("PlayingField.fxml"));
            loader.setRoot(this);
            this.comp = new PlayingFieldCompanion();
            loader.setController(comp);
            loader.load();
        }catch(IOException e){
            throw new RuntimeException(e);
          }
    }
}
