package facturador.beans;

public class Item {
    private int idInt;
    private String idString;
    private String description;

    public Item() {
    }

    public Item(int idInt, String idString, String description) {
        this.idInt = idInt;
        this.idString = idString;
        this.description = description;
    }

    public int getIdInt() {
        return idInt;
    }

    public void setIdInt(int idInt) {
        this.idInt = idInt;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
