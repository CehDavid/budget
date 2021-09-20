
package budget;

import java.sql.Date;


public class Income {
    
    private int id;
    private int pocketId;
    private int amount;
    private Date date;
    private String pocketName;
    
    public Income(){
    }

    public Income(int id, String pocketName, int amount, Date date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.pocketName = pocketName;
    }
    
     public Income(String pocketName, int amount, Date date) {
        this.amount = amount;
        this.date = date;
        this.pocketName = pocketName;
    }
    
    public Income( int id, int pocketId, int amount, Date date) {
        this.id = id;
        this.pocketId = pocketId;
        this.amount = amount;
        this.date = date;
    }

    public Income( int pocketId, int amount, Date date) {
        this.pocketId = pocketId;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getPocketId() {
        return pocketId;
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

    public void setPocketId(int pocketId) {
        this.pocketId = pocketId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPocketName() {
        return pocketName;
    }

    public void setPocketName(String pocketName) {
        this.pocketName = pocketName;
    }
    
    
    
}
