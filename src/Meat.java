import java.io.Serializable;
import java.util.Objects;

public class Meat extends PaleoFood implements Serializable {
    private int type, cookingTemp;
    // Constructor
    protected Meat(String name, int calories, int type, int cookingTemp) {
        super(name, calories, 0);
        this.type = type;
        this.cookingTemp = cookingTemp;
    }
    // Getters and Setters
    public int getType() {return type;}
    public void setType(int type) {this.type = type;}
    public int getCookingTemp() {return cookingTemp;}
    public void setCookingTemp(int cookingTemp) {this.cookingTemp = cookingTemp;}
    // toString()
    @Override
    public String toString() {
        if (type == 1) {
            return "Meat: " + name + ", " +
                    calories + " calories, " +
                    carbs + "g carbs, " +
                    cookingTemp + " degrees F";
        } else {
            return "Seafood: " + name + ", " +
                    calories + " calories, " +
                    carbs + "g carbs, " +
                    cookingTemp + " degrees F";
        }
    }
    // equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Meat meat = (Meat) o;
        return type == meat.type && cookingTemp == meat.cookingTemp;
    }
    // hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, cookingTemp);
    }
}
