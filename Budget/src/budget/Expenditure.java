
package budget;

import java.sql.Date;

public class Expenditure {
    
    private int id;
    private String type;
    private int amount;
    private Date date;
    
    public Expenditure(){
    }

    public Expenditure(String type, int amount, Date date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }
    

    public Expenditure(int id, String type, int amount, Date date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    

    

   
   
    
}
