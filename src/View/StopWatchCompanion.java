package View;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sudokusolver.Model.Model;

/**
 *
 * @author Jeroen De Meyer
 */
public class StopWatchCompanion implements InvalidationListener{
    
    @FXML
    public Label hours;
    @FXML
    public Label minutes;
    @FXML
    public Label seconds;
    
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
    
    private int totalSeconds = 0;
    
    private Timer timer;
    
    @Override
    public void invalidated(Observable o){
        if(model.startClock()){
            totalSeconds = 0;
            if(timer!=null){
                timer.cancel();
                timer.purge();
            }
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                    public void run() {
                        Platform.runLater(() -> {
                            String h = "0" + totalSeconds/3600;
                            String m = "0" + (totalSeconds/60)%60;
                            String s = "0" + totalSeconds%60;
                            hours.setText(h.substring(h.length()-2));
                            minutes.setText(m.substring(m.length()-2));
                            seconds.setText(s.substring(s.length()-2));
                            totalSeconds++;
                        });
                    }
                },0, 1000);
        }
        else if(model.stopClock()){
            if(timer!=null){
                timer.cancel();
                timer.purge();
            }
            hours.setText(hours.getText());
            minutes.setText(minutes.getText());
            seconds.setText(seconds.getText());
        }
    }
}
