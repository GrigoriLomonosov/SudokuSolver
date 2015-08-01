package sudokusolver.Model.Algorithms;

import java.util.Objects;
import sudokusolver.Model.Represention.Cell;

/**
 *
 * @author Jeroen De Meyer
 */
public class XWing {

    private final int value;
    public int getValue(){
        return value;
    }
    
    private final Cell c1;
    public Cell getCell1(){
        return c1;
    }
    
    private final Cell c2;
    public Cell getCell2(){
        return c2;
    }
    
    private final Cell c3;
    public Cell getCell3(){
        return c3;
    }
    
    private final Cell c4;
    public Cell getCell4(){
        return c4;
    }
    
    public XWing(int value, Cell c1, Cell c2, Cell c3, Cell c4){
        this.value = value;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.value;
        hash = 67 * hash + Objects.hashCode(this.c1);
        hash = 67 * hash + Objects.hashCode(this.c2);
        hash = 67 * hash + Objects.hashCode(this.c3);
        hash = 67 * hash + Objects.hashCode(this.c4);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final XWing other = (XWing) obj;
        if (this.value != other.value) {
            return false;
        }
        if (!Objects.equals(this.c1, other.c1)) {
            return false;
        }
        if (!Objects.equals(this.c2, other.c2)) {
            return false;
        }
        if (!Objects.equals(this.c3, other.c3)) {
            return false;
        }
        if (!Objects.equals(this.c4, other.c4)) {
            return false;
        }
        return true;
    }
}
