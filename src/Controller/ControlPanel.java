package Controller;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import sudokusolver.Model.Model;

/**
 *
 * @author Jeroen De Meyer
 */
public class ControlPanel extends VBox{
    
    private final ControlPanelCompanion comp;
    
    public ResourceBundle resources;
    
    public Model model;
    public Model getModel(){
        return model;
    }
    public void setModel(Model m){
        model = m;
        comp.setModel(m);
    }
    
    public ControlPanel(){
         try{
            FXMLLoader loader = new FXMLLoader(
                ControlPanel.class.getResource("ControlPanel.fxml"),
                ResourceBundle.getBundle("resources/i18n"));
            loader.setRoot(this);
            this.comp = new ControlPanelCompanion();
            loader.setController(comp);
            loader.load();
        }catch(IOException e){
            throw new RuntimeException(e);
          }
    }
}
