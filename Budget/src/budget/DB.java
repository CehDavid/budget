
package budget;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class DB {
    
    final String URL = "jdbc:derby:budgetDB;create=true";
    final String USERNAME = "";
    final String PASSWORD = "";
    
    Connection conn = null;
    Statement st = null;
    DatabaseMetaData dbmd = null;
    
    
    public DB(){
    
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("A híd létrejött");
            
        } catch (SQLException ex) {
            System.out.println("Valami baj van a connection (híd) létrehozásakor.");
            System.out.println(""+ex);
        }
         if (conn != null){
            try {
                st = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Valami baj van van a Statament létrehozásakor.");
                System.out.println(""+ex);
            }
        }
        try {           
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a DatabaseMetaData (adatbázis leírása) létrehozásakor..");
            System.out.println(""+ex);
        }
         
        try {
            ResultSet rs = dbmd.getTables(null, "APP", "POCKETS", null);
            if(!rs.next())
            { 
             st.execute("create table pockets(id int, name varchar(20), balance int)");
             
             st.executeUpdate("INSERT INTO pockets(id,name,balance) VALUES(1,'fő',0)");
             st.executeUpdate("INSERT INTO pockets(id,name,balance) VALUES(2,'új otthon',0)");
             st.executeUpdate("INSERT INTO pockets(id,name,balance) VALUES(3,'új autó',0)");
             st.executeUpdate("INSERT INTO pockets(id,name,balance) VALUES(4,'utazás',0)");
             st.executeUpdate("INSERT INTO pockets(id,name,balance) VALUES(5,'egyéb',0)");
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van a 'pockets' tábla létrehozásakor.");
            System.out.println(""+ex);
        }
        try {
            ResultSet rs = dbmd.getTables(null, "APP", "EXPENDITURES", null);
            if(!rs.next())
            { 
             st.execute("create table expenditures(id int not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), pocketid int, type varchar(20),amount int, date date)");
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van az 'expenditures' tábla létrehozásakor.");
            System.out.println(""+ex);
        }
        try {
            ResultSet rs = dbmd.getTables(null, "APP", "REVENUES", null);
            if(!rs.next())
            { 
             st.execute("create table revenues(id int not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), pocketid int, amount int, date date)");
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van az 'income' tábla létrehozásakor.");
            System.out.println(""+ex);
        } 
        
              
    
    }
    
    //kiadások menüpont adatbázis kapcsolatáért felelős függvények
    public ArrayList<Expenditure> getAllExpenses(){
            
            String sql = "select * from expenditures";
            ArrayList<Expenditure> expenses = null;
            try {
                ResultSet rs = st.executeQuery(sql);
                expenses = new ArrayList<>();

                while (rs.next()){
                    Expenditure actualExp = new Expenditure(rs.getInt(1),rs.getString(3),rs.getInt(4),rs.getDate(5));
                    expenses.add(actualExp);
                }
                } catch (SQLException ex) {
                System.out.println("Valami baj van");
                System.out.println(""+ex);
            }
            
            return expenses;
        }
        
    public void newSpending(Expenditure exp){
            
        try {
        String sql = "INSERT INTO expenditures (pocketid, type, amount,date) values (1,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, exp.getType());
        preparedStatement.setInt(2, exp.getAmount());
        preparedStatement.setDate(3, exp.getDate());
        preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van");
            System.out.println(""+ex);
        }
        }
        
    public void removeExpenses(Expenditure exp){
            
            try {
                
                String sql = "delete from expenditures where id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1,exp.getId());
                preparedStatement.execute();
           
             } catch (SQLException ex) {
                System.out.println("Valami baj van");
                System.out.println(""+ex);
            }
        }
        
    public int getMonthlyExpenses(String type,int actualYear,int actualMonth,int lastDay){
            
            String sql = "SELECT SUM(amount) FROM expenditures WHERE type LIKE '"+type+"' AND date BETWEEN '"+actualYear+"-"+actualMonth+"-01' AND '"+actualYear+"-"+actualMonth+"-"+lastDay+"'";
            int monthlyExpenses = 0;
            
        try {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            monthlyExpenses = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.println("Valami baj van a havi lekérdezésel");
            System.out.println(""+ex);
        }
           
            return monthlyExpenses;
        }
        
    public int getTotalMonthlyExpenses(int actualYear,int actualMonth, int lastDay){
           
            String sql = "SELECT SUM(amount) FROM expenditures WHERE date BETWEEN '"+actualYear+"-"+actualMonth+"-01' AND '"+actualYear+"-"+actualMonth+"-"+lastDay+"'";
            int totalMonthlyExpenses = 0;
            
        try {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            totalMonthlyExpenses = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.println("Valami baj van az össz havi lekérdezéssel");
            System.out.println(""+ex);
        }
           
            return totalMonthlyExpenses;
      
        }
        
    public int getAnnualExpenses(String type,int actualYear){
            
            String sql = "SELECT SUM(amount) FROM expenditures WHERE type LIKE '"+type+"' AND date BETWEEN '"+actualYear+"-01-01' AND '"+actualYear+"-12-31'";
            int annualExpenses = 0;
            
        try {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            annualExpenses = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.println("Valami baj van");
            System.out.println(""+ex);
        }
           
            return annualExpenses;
        }
        
    public int getTotalAnnualExpenses(int actualYear){
            
            String sql = "SELECT SUM(amount) FROM expenditures WHERE date BETWEEN '"+actualYear+"-01-01' AND '"+actualYear+"-12-31'";
            int totalAnnualExpenses = 0;
            
        try {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            totalAnnualExpenses = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.println("Valami baj van");
            System.out.println(""+ex);
        }
           
            return totalAnnualExpenses;
      
        }
    
    //Zsebek adatbázis kapcsolatáért felelős függvének    
    public ArrayList<Pocket> getAllPockets(){
            
            String sql = "select * from pockets";
            ArrayList<Pocket> pockets = null;
            try {
                ResultSet rs = st.executeQuery(sql);
                pockets = new ArrayList<>();

                while (rs.next()){
                    Pocket actualPocket = new Pocket(rs.getInt(1),rs.getString(2),rs.getInt(3));
                    pockets.add(actualPocket);
                }
                } catch (SQLException ex) {
                System.out.println("Valami baj van");
                System.out.println(""+ex);
            }
            
            return pockets;
        }
    
    public int getBalance(int pocketId){
        
        String sql = "Select balance from pockets where id ="+pocketId;
        int balance =0;
        
        try {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            balance = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.println("Valami baj van");
            System.out.println(""+ex);
        }
        
        return balance;
    }
    
    public void updateBalance(Pocket pocket){
            
            String sql = "UPDATE pockets SET balance =? where id=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, pocket.getBalance());
            preparedStatement.setInt(2, pocket.getId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van");
            System.out.println(""+ex);
        }
        }
    
    //Bevétel adatbázis kapcsolatáért felellős függvények
    public void newIncome(Income income){
            
        try {
        String sql = "INSERT INTO revenues (pocketid,amount,date) values (?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,income.getPocketId());
        preparedStatement.setInt(2,income.getAmount());
        preparedStatement.setDate(3,income.getDate());
        preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van");
            System.out.println(""+ex);
        }
        }
    
    public ArrayList<Income> getAllRevenues(){
            
            String sql = "select revenues.id, name, amount, date from revenues join pockets on revenues.pocketid = pockets.id";
            ArrayList<Income> revenues = null;
            try {
                ResultSet rs = st.executeQuery(sql);
                revenues = new ArrayList<>();

                while (rs.next()){
                    Income actualIncome = new Income(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDate(4));
                    revenues.add(actualIncome);
                }
                } catch (SQLException ex) {
                System.out.println("Valami baj van");
                System.out.println(""+ex);
            }
            
            return revenues;
        }
    
    public void removeIncome(Income inc){
            
            try {
                
                String sql = "delete from revenues where id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1,inc.getId());
                preparedStatement.execute();
           
             } catch (SQLException ex) {
                System.out.println("Valami baj van");
                System.out.println(""+ex);
            }
        }
}
