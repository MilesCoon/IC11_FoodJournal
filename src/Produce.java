import java.io.Serializable;
import java.util.Objects;

public class Produce extends PaleoFood implements Serializable {
    private boolean organic;
    // Constructor
    protected Produce (String name, int calories, int carbs, boolean organic) {
        super(name, calories, carbs);
        this.organic = organic;
    }
    // Getter and Setter
    public boolean getOrganic() {return organic;}
    public void setOrganic(boolean organic) {this.organic = organic;}
    // toString()
    @Override
    public String toString() {
        if (organic) {
            return "Organic Produce: " + name + ", " +
                    calories + " calories, " +
                    carbs + "g carbs";
        } else {
            return "Produce: " + name + ", " +
                    calories + " calories, " +
                    carbs + "g carbs";
        }
    }
    // equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Produce produce = (Produce) o;
        return organic == produce.organic;
    }
    // hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), organic);
    }
}
