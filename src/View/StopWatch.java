package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import sudokusolver.Model.Model;

/**
 *
 * @author Jeroen De Meyer
 */
public class StopWatch extends HBox{

    private final StopWatchCompanion comp;
    
    public Model model;
    public Model getModel(){
        return model;
    }
    public void setModel(Model m){
        model = m;
        comp.setModel(model);
    }
    
    public StopWatch(){
        try{
            FXMLLoader loader = new FXMLLoader(
                StopWatch.class.getResource("StopWatch.fxml"));
            loader.setRoot(this);
            this.comp = new StopWatchCompanion();
            loader.setController(comp);
            loader.load();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
