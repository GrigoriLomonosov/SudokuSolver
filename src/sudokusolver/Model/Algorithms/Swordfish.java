package sudokusolver.Model.Algorithms;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import sudokusolver.Model.Represention.Cell;
import sudokusolver.Model.Represention.House;

/**
 *
 * @author Jeroen De Meyer
 */
public class Swordfish {

    private final int value;
    public int getValue(){
        return value;
    }
    
    private final House house1;
    private final House house2;
    private final House house3;
    
    private final List<House> houses;
    public List<House> getHouses(){
        return houses;
    }
    
    private final Set<Cell> swordfishCells;
    
    public Swordfish(int value, List<House> houses, Set<Cell> swordfishCells){
        this.value = value;
        this.houses = houses;
        house1 = houses.get(0);
        house2 = houses.get(1);
        house3 = houses.get(2);
        this.swordfishCells = swordfishCells;
    }
    
    public boolean isSwordfishCell(Cell cell){
        return swordfishCells.contains(cell);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.value;
        hash = 53 * hash + Objects.hashCode(this.house1);
        hash = 53 * hash + Objects.hashCode(this.house2);
        hash = 53 * hash + Objects.hashCode(this.house3);
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
        final Swordfish other = (Swordfish) obj;
        if (this.value != other.value) {
            return false;
        }
        if (!Objects.equals(this.house1, other.house1)) {
            return false;
        }
        if (!Objects.equals(this.house2, other.house2)) {
            return false;
        }
        if (!Objects.equals(this.house3, other.house3)) {
            return false;
        }
        return true;
    }
   
}
