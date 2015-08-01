package Controller;

import javafx.scene.control.TextField;

/**
 *
 * @author Jeroen De Meyer
 */
public class NumericTextArea extends TextField{
    
    private int colNumber;
    public int getColNumber(){
        return colNumber;
    }
    public void setColNumber(int i){
        colNumber = i;
    }

    @Override
    public void replaceText(int start, int end, String text){
        if (validate(text))
        {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text){
        if (validate(text))
        {
            super.replaceSelection(text);
        }
    }

    private boolean validate(String text){
        return (text.matches("[1-9]*"));
    }
}
