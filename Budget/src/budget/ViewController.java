
package budget;



import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;



public class ViewController implements Initializable {
    
    @FXML
    Pane spendingPane, menuPane, statisticsPane, tablePane, diagramPane, incomePane,tablePane2, balancePane;
    @FXML
    TextField spendingTF, incomeTF, transferTF;
    @FXML
    DatePicker spendingDP, incomeDP;
    @FXML
    Button houseButton, carButton, shoppingCartButton, shoppingBagButton, invoiceButton, studentButton, cameraButton, turtleButton, cocktailButton, bookButton;
    @FXML
    Button addSpendingButton, monthlySpending,annualSpending,addIncomeButton;
    @FXML
    Button walletButton,newHomeButton,newCarButton,palmButton,savingsButton;
    @FXML
    Label spendingOfHome,spendingOfCar, spendingOfFood,spendingOfShopping, spendingOfInvoices;
    @FXML
    Label spendingOfStudy, spendingOfHobby,spendingOfPet, spendingOfEntertainment,otherSpending,totalSpending;
    @FXML
    Label spendingOfHome2,spendingOfCar2,spendingOfFood2,spendingOfShopping2,spendingOfInvoices2;
    @FXML
    Label spendingOfStudy2,spendingOfHobby2,spendingOfPet2, spendingOfEntertainment2,otherSpending2;
    @FXML
    Label warningLabel1, warningLabel2,warningLabel3, warningLabel4,warningLabel5;
    @FXML
    TableView spendingTable,incomeTable;
    @FXML
    Label mainBalance, newHomeBalance, newCarBalance, travelBalance, otherBalance;
    @FXML
    ComboBox transferCombo,transferCombo2;       
    @FXML
    Button transferButton;
            
    DB db = new DB();
    
    private final String [] typeOfRelease = {"Otthon","Autó","Élelmiszer","Vásárlás","Számlák","Oktatás","Hobbi","Kiskedvenc","Szórakozás","Egyéb"};
    private String spendingType = null;
    private int pocketId = 0;
    
    private String selectedButtonStyle = "-fx-background-color: yellow;-fx-background-radius: 10;-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 1.0), 10, 0.0, 0.0, 0.0);";
    private String allButtonStyle ="-fx-background-color: #3ca447;-fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 1.0), 10, 0.0, 0.0, 0.0);";
    
    private int actualMonth = LocalDate.now().getMonthValue();
    private int actualYear = LocalDate.now().getYear();
    private int lastDayOfMonth = LocalDate.now().lengthOfMonth();
    
    private final ObservableList<Expenditure> expList = FXCollections.observableArrayList();
    private final ObservableList<Income> revenuesList = FXCollections.observableArrayList();
    private final ObservableList<String> pocketNames = FXCollections.observableArrayList();
   
    final private CategoryAxis x = new CategoryAxis();
    final private NumberAxis y = new NumberAxis();
    final private BarChart<String,Number> bc = new BarChart<String,Number>(x,y);
    private XYChart.Series series1 = new XYChart.Series();
    private XYChart.Series series2 = new XYChart.Series();
    
    //Kiadások menüpont funkciói
    @FXML
    private void selectedHouseButton(){
    
        spendingType = typeOfRelease[0];
       
        
        houseButton.setStyle(selectedButtonStyle);
        carButton.setStyle(allButtonStyle);
        shoppingCartButton.setStyle(allButtonStyle);
        shoppingBagButton.setStyle(allButtonStyle);
        invoiceButton.setStyle(allButtonStyle);
        studentButton.setStyle(allButtonStyle);
        cameraButton.setStyle(allButtonStyle);
        turtleButton.setStyle(allButtonStyle);
        cocktailButton.setStyle(allButtonStyle);
        bookButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedCarButton(){
    
        spendingType = typeOfRelease[1];
        
        
        houseButton.setStyle(allButtonStyle);
        carButton.setStyle(selectedButtonStyle);
        shoppingCartButton.setStyle(allButtonStyle);
        shoppingBagButton.setStyle(allButtonStyle);
        invoiceButton.setStyle(allButtonStyle);
        studentButton.setStyle(allButtonStyle);
        cameraButton.setStyle(allButtonStyle);
        turtleButton.setStyle(allButtonStyle);
        cocktailButton.setStyle(allButtonStyle);
        bookButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedShoppingCartButton(){
    
        spendingType = typeOfRelease[2];
      
        
        houseButton.setStyle(allButtonStyle);
        carButton.setStyle(allButtonStyle);
        shoppingCartButton.setStyle(selectedButtonStyle);
        shoppingBagButton.setStyle(allButtonStyle);
        invoiceButton.setStyle(allButtonStyle);
        studentButton.setStyle(allButtonStyle);
        cameraButton.setStyle(allButtonStyle);
        turtleButton.setStyle(allButtonStyle);
        cocktailButton.setStyle(allButtonStyle);
        bookButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedShoppingBagButton(){
    
        spendingType = typeOfRelease[3];
       
        
        houseButton.setStyle(allButtonStyle);
        carButton.setStyle(allButtonStyle);
        shoppingCartButton.setStyle(allButtonStyle);
        shoppingBagButton.setStyle(selectedButtonStyle);
        invoiceButton.setStyle(allButtonStyle);
        studentButton.setStyle(allButtonStyle);
        cameraButton.setStyle(allButtonStyle);
        turtleButton.setStyle(allButtonStyle);
        cocktailButton.setStyle(allButtonStyle);
        bookButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedInvoiceButton(){
    
        spendingType = typeOfRelease[4];
     
        houseButton.setStyle(allButtonStyle);
        carButton.setStyle(allButtonStyle);
        shoppingCartButton.setStyle(allButtonStyle);
        shoppingBagButton.setStyle(allButtonStyle);
        invoiceButton.setStyle(selectedButtonStyle);
        studentButton.setStyle(allButtonStyle);
        cameraButton.setStyle(allButtonStyle);
        turtleButton.setStyle(allButtonStyle);
        cocktailButton.setStyle(allButtonStyle);
        bookButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedStudentButton(){
    
        spendingType = typeOfRelease[5];
       
        
        houseButton.setStyle(allButtonStyle);
        carButton.setStyle(allButtonStyle);
        shoppingCartButton.setStyle(allButtonStyle);
        shoppingBagButton.setStyle(allButtonStyle);
        invoiceButton.setStyle(allButtonStyle);
        studentButton.setStyle(selectedButtonStyle);
        cameraButton.setStyle(allButtonStyle);
        turtleButton.setStyle(allButtonStyle);
        cocktailButton.setStyle(allButtonStyle);
        bookButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedCameraButton(){
    
        spendingType = typeOfRelease[6];
        
        houseButton.setStyle(allButtonStyle);
        carButton.setStyle(allButtonStyle);
        shoppingCartButton.setStyle(allButtonStyle);
        shoppingBagButton.setStyle(allButtonStyle);
        invoiceButton.setStyle(allButtonStyle);
        studentButton.setStyle(allButtonStyle);
        cameraButton.setStyle(selectedButtonStyle);
        turtleButton.setStyle(allButtonStyle);
        cocktailButton.setStyle(allButtonStyle);
        bookButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedTurtleButton(){
    
        spendingType = typeOfRelease[7];
        
        
        houseButton.setStyle(allButtonStyle);
        carButton.setStyle(allButtonStyle);
        shoppingCartButton.setStyle(allButtonStyle);
        shoppingBagButton.setStyle(allButtonStyle);
        invoiceButton.setStyle(allButtonStyle);
        studentButton.setStyle(allButtonStyle);
        cameraButton.setStyle(allButtonStyle);
        turtleButton.setStyle(selectedButtonStyle);
        cocktailButton.setStyle(allButtonStyle);
        bookButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedCocktailButton(){
    
        spendingType = typeOfRelease[8];
       
        
        houseButton.setStyle(allButtonStyle);
        carButton.setStyle(allButtonStyle);
        shoppingCartButton.setStyle(allButtonStyle);
        shoppingBagButton.setStyle(allButtonStyle);
        invoiceButton.setStyle(allButtonStyle);
        studentButton.setStyle(allButtonStyle);
        cameraButton.setStyle(allButtonStyle);
        turtleButton.setStyle(allButtonStyle);
        cocktailButton.setStyle(selectedButtonStyle);
        bookButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedBookButton(){
    
        spendingType = typeOfRelease[9];
      
        houseButton.setStyle(allButtonStyle);
        carButton.setStyle(allButtonStyle);
        shoppingCartButton.setStyle(allButtonStyle);
        shoppingBagButton.setStyle(allButtonStyle);
        invoiceButton.setStyle(allButtonStyle);
        studentButton.setStyle(allButtonStyle);
        cameraButton.setStyle(allButtonStyle);
        turtleButton.setStyle(allButtonStyle);
        cocktailButton.setStyle(allButtonStyle);
        bookButton.setStyle(selectedButtonStyle);
    }
    @FXML
    private void addNewSpending(){
        
        int amount=0;
        try{
            
            amount = Integer.parseInt(spendingTF.getText());
            
        }catch(NumberFormatException ex){
            
            warningLabel1.setVisible(true);
        }
       
        if(amount<=0){
            
            warningLabel1.setVisible(true);
            warningLabel2.setVisible(false);
           
        }else if(spendingType==null){
            
            warningLabel2.setVisible(true);
            warningLabel1.setVisible(false);
            
        }else{
            Pocket pock = db.getAllPockets().get(0);
            Pocket pocket = new Pocket(pock.getId(),pock.getName(),pock.getBalance()-amount);
            db.updateBalance(pocket);
            
            Expenditure exp = new Expenditure(spendingType,amount,Date.valueOf(spendingDP.getValue()));
            db.newSpending(exp);
            expList.add(exp);
            
            spendingTF.clear();
            spendingType = null;
            spendingDP.setValue(LocalDate.now());
            warningLabel1.setVisible(false);
            warningLabel2.setVisible(false);
            
            houseButton.setStyle(allButtonStyle);
            carButton.setStyle(allButtonStyle);
            shoppingCartButton.setStyle(allButtonStyle);
            shoppingBagButton.setStyle(allButtonStyle);
            invoiceButton.setStyle(allButtonStyle);
            studentButton.setStyle(allButtonStyle);
            cameraButton.setStyle(allButtonStyle);
            turtleButton.setStyle(allButtonStyle);
            cocktailButton.setStyle(allButtonStyle);
            bookButton.setStyle(allButtonStyle);
            
        }
        
        
    }
    
    @FXML
    private void monthlyStatistic(){
        
        monthlySpending.setStyle("-fx-background-color: #52ff64; -fx-border-width: 0 0 2 0; -fx-border-color: black;");
        annualSpending.setStyle("-fx-background-color: #52ff64;");
        
        Label [] labels2 = {spendingOfHome2,spendingOfCar2,spendingOfFood2,spendingOfShopping2, spendingOfInvoices2,spendingOfStudy2,spendingOfHobby2,spendingOfPet2,spendingOfEntertainment2,otherSpending2};
        for(int i=0; i<labels2.length;i++){
            
            labels2[i].setText(getPercentage(db.getTotalMonthlyExpenses(actualYear, actualMonth,lastDayOfMonth),db.getMonthlyExpenses(typeOfRelease[i], actualYear, actualMonth,lastDayOfMonth))+"%");
        }
        
        Label [] labels = {spendingOfHome,spendingOfCar,spendingOfFood,spendingOfShopping, spendingOfInvoices,spendingOfStudy,spendingOfHobby,spendingOfPet,spendingOfEntertainment,otherSpending};
        
        for(int i = 0; i<labels.length;i++){
            
            labels[i].setText(String.valueOf(db.getMonthlyExpenses(typeOfRelease[i],actualYear,actualMonth,lastDayOfMonth))+" ft");
        }
        
        totalSpending.setText(String.valueOf(db.getTotalMonthlyExpenses(actualYear,actualMonth,lastDayOfMonth))+" ft");
        
    }
    @FXML
    private void annualStatistic(){
       
        monthlySpending.setStyle("-fx-background-color: #52ff64;");
        annualSpending.setStyle("-fx-background-color: #52ff64; -fx-border-width: 0 0 2 0; -fx-border-color: black;");
       
        
        Label [] labels2 = {spendingOfHome2,spendingOfCar2,spendingOfFood2,spendingOfShopping2, spendingOfInvoices2,spendingOfStudy2,spendingOfHobby2,spendingOfPet2,spendingOfEntertainment2,otherSpending2};
        
        for(int i=0;i<labels2.length;i++){
        
            labels2[i].setText(getPercentage(db.getTotalAnnualExpenses(actualYear),db.getAnnualExpenses(typeOfRelease[i], actualYear))+"%");
            
        }
        Label [] labels = {spendingOfHome,spendingOfCar,spendingOfFood,spendingOfShopping, spendingOfInvoices,spendingOfStudy,spendingOfHobby,spendingOfPet,spendingOfEntertainment,otherSpending};
        
        for(int i=0;i<labels.length;i++){
        
            labels[i].setText(String.valueOf(db.getAnnualExpenses(typeOfRelease[i],actualYear))+" ft");
        }
   
        totalSpending.setText(String.valueOf(db.getTotalAnnualExpenses(actualYear))+" ft");
        
    }
    //Egy stringet ad vissza, a különböző költségek egymáshoz képest hány százalékot érnek el.
    public String getPercentage(double total, double expenses){
        
            double percentage = (expenses/total)*100;
            
            return String.format("%.1f",percentage).replace(",", ".");
        }
    
    private void setExpensesTableData(){
        
        
        TableColumn typeCol = new TableColumn("Kategoria");
        typeCol.setMinWidth(122);
        typeCol.setMaxWidth(122);
        typeCol.setCellValueFactory(new PropertyValueFactory<Expenditure, String>("type"));
       
        TableColumn amountCol = new TableColumn("Összeg");
        amountCol.setMinWidth(122);
        amountCol.setMaxWidth(122);
        amountCol.setCellValueFactory(new PropertyValueFactory<Expenditure, Integer>("amount"));
        
        TableColumn dateCol = new TableColumn("Dátum");
        dateCol.setMinWidth(122);
        dateCol.setMaxWidth(122);
        dateCol.setCellValueFactory(new PropertyValueFactory<Expenditure, LocalDate>("date"));
        
        TableColumn removeCol = new TableColumn( "Törlés" );
        removeCol.setMinWidth(55);
        removeCol.setMaxWidth(55);
        
        
        Callback<TableColumn<Expenditure, String>, TableCell<Expenditure, String>> cellFactory = 
                new Callback<TableColumn<Expenditure, String>, TableCell<Expenditure,String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<Expenditure, String> param )
                    {
                        final TableCell<Expenditure,String> cell = new TableCell<Expenditure, String>()
                        {   
                            Button btn = new Button();
                           
                            @Override
                            public void updateItem( String item, boolean empty )
                            {
                                super.updateItem( item, empty );
                                if ( empty )
                                {
                                    setGraphic( null );
                                    setText( null );
                                }
                                else
                                {
                                    btn.setOnAction( ( ActionEvent event ) ->
                                            {
                                                Expenditure exp = getTableView().getItems().get( getIndex() );
                                                expList.remove(exp);
                                                db.removeExpenses(exp);
                                                db.updateBalance(new Pocket(db.getAllPockets().get(0).getId(),db.getAllPockets().get(0).getName(),(db.getAllPockets().get(0).getBalance()+exp.getAmount())));
                                       } );
                                    setGraphic(btn);
                                    ImageView icon = new ImageView("/Image/trash.png");
                                    icon.setFitHeight(20);
                                    icon.setFitWidth(20);
                                    btn.setGraphic(icon);
                                    btn.setStyle("-fx-background-color: #3ca447;");
                                    setStyle("-fx-alignment : CENTER;");
                                    setText( null );
                                }
                            }
                        };
                        return cell;
                    }
                };

        removeCol.setCellFactory( cellFactory );
        
        spendingTable.getColumns().addAll(typeCol,amountCol,dateCol,removeCol);
        spendingTable.getStyleClass().add("myTable");
        expList.addAll(db.getAllExpenses());
        
        spendingTable.setItems(expList);
    }   
    //A digram oszlopainak adatokkal való feltőltése. Külön töltjük fel adatokkal , hogy futás közben is dinamikusan változanak az adatok
    private void setSeriesData(){
        
         for(int i=0;i<typeOfRelease.length;i++){
            
            series1.getData().add(new XYChart.Data(typeOfRelease[i],Double.parseDouble(getPercentage(db.getTotalMonthlyExpenses(actualYear, actualMonth,lastDayOfMonth),db.getMonthlyExpenses(typeOfRelease[i], actualYear, actualMonth,lastDayOfMonth)))));
        }
         for(int i=0;i<typeOfRelease.length;i++){
            
            series2.getData().add(new XYChart.Data(typeOfRelease[i],Double.parseDouble(getPercentage(db.getTotalAnnualExpenses(actualYear),db.getAnnualExpenses(typeOfRelease[i], actualYear)))));
        }
    }
    //A digram a megjelenéséért felelős függvény.     
    private void setBCData(){
       
        bc.setTitle("Kiadás statisztika százalékokban");
         
        series1.setName("Havi kiadások"); 
        series2.setName("Éves kiadások");
        
        setSeriesData();
        
        bc.getStyleClass().add("myBC");
        bc.setLayoutX(0);
        bc.setLayoutY(0);
        bc.setMaxSize(420,500);
        bc.getData().addAll(series1,series2);
        diagramPane.getChildren().add(bc);
        
    }
    
    //Bevétel menüpont funkciói
    @FXML
    private void selectedWalletButton(){
    
        pocketId = 1;
        
        walletButton.setStyle(selectedButtonStyle);
        newHomeButton.setStyle(allButtonStyle);
        newCarButton.setStyle(allButtonStyle);
        palmButton.setStyle(allButtonStyle);
        savingsButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedNewHomeButton(){
    
        pocketId = 2;
        
        walletButton.setStyle(allButtonStyle);
        newHomeButton.setStyle(selectedButtonStyle);
        newCarButton.setStyle(allButtonStyle);
        palmButton.setStyle(allButtonStyle);
        savingsButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedNewCarButton(){
    
        pocketId = 3;
        
        walletButton.setStyle(allButtonStyle);
        newHomeButton.setStyle(allButtonStyle);
        newCarButton.setStyle(selectedButtonStyle);
        palmButton.setStyle(allButtonStyle);
        savingsButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedPalmButton(){
    
        pocketId = 4;
        
        walletButton.setStyle(allButtonStyle);
        newHomeButton.setStyle(allButtonStyle);
        newCarButton.setStyle(allButtonStyle);
        palmButton.setStyle(selectedButtonStyle);
        savingsButton.setStyle(allButtonStyle);
    }
    @FXML
    private void selectedSavingsButton(){
        
        pocketId = 5;
        
        walletButton.setStyle(allButtonStyle);
        newHomeButton.setStyle(allButtonStyle);
        newCarButton.setStyle(allButtonStyle);
        palmButton.setStyle(allButtonStyle);
        savingsButton.setStyle(selectedButtonStyle);
    }
    @FXML
    private void addNewIncome(){
        
        int amount=0;
        try{
            
            amount = Integer.parseInt(incomeTF.getText());
            
        }catch(NumberFormatException ex){
            
            warningLabel3.setVisible(true);
        }
       
        if(amount<=0){
            
            warningLabel3.setVisible(true);
            warningLabel4.setVisible(false);
           
        }else if(pocketId==0){
            
            warningLabel4.setVisible(true);
            warningLabel3.setVisible(false);
            
        }else{
            
           
            Pocket pock = db.getAllPockets().get(pocketId-1);
            
            Pocket pocket = new Pocket(pock.getId(),pock.getName(),pock.getBalance()+amount);
            db.updateBalance(pocket);
            
            Income income = new Income(pocketId,amount,Date.valueOf(incomeDP.getValue()));
            Income income2 = new Income(pock.getName(),amount,Date.valueOf(incomeDP.getValue()) );
            db.newIncome(income);
            revenuesList.add(income2);
            
            incomeTF.clear();
            pocketId = 0;
            incomeDP.setValue(LocalDate.now());
            warningLabel3.setVisible(false);
            warningLabel4.setVisible(false);
            
            walletButton.setStyle(allButtonStyle);
            newHomeButton.setStyle(allButtonStyle);
            newCarButton.setStyle(allButtonStyle);
            palmButton.setStyle(allButtonStyle);
            savingsButton.setStyle(allButtonStyle);
           
            
        }
        
        
    }
    
    private void setRevenuesTableData(){
        
        
        TableColumn pockCol = new TableColumn("Zseb");
        pockCol.setMinWidth(122);
        pockCol.setMaxWidth(122);
        pockCol.setCellValueFactory(new PropertyValueFactory<Income, String>("pocketName"));
       
        TableColumn amountCol = new TableColumn("Összeg");
        amountCol.setMinWidth(122);
        amountCol.setMaxWidth(122);
        amountCol.setCellValueFactory(new PropertyValueFactory<Income, Integer>("amount"));
        
        TableColumn dateCol = new TableColumn("Dátum");
        dateCol.setMinWidth(122);
        dateCol.setMaxWidth(122);
        dateCol.setCellValueFactory(new PropertyValueFactory<Income, LocalDate>("date"));
        
        TableColumn removeCol = new TableColumn( "Törlés" );
        removeCol.setMinWidth(55);
        removeCol.setMaxWidth(55);
        
        
        Callback<TableColumn<Income, String>, TableCell<Income, String>> cellFactory = 
                new Callback<TableColumn<Income, String>, TableCell<Income,String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<Income, String> param )
                    {
                        final TableCell<Income,String> cell = new TableCell<Income, String>()
                        {   
                            Button btn = new Button();
                           
                            @Override
                            public void updateItem( String item, boolean empty )
                            {
                                super.updateItem( item, empty );
                                if ( empty )
                                {
                                    setGraphic( null );
                                    setText( null );
                                }
                                else
                                {
                                    btn.setOnAction( ( ActionEvent event ) ->
                                            {
                                                Income income = getTableView().getItems().get( getIndex() );
                                                revenuesList.remove(income);
                                                db.removeIncome(income);
                                                db.updateBalance(new Pocket(db.getAllPockets().get(0).getId(),db.getAllPockets().get(0).getName(),(db.getAllPockets().get(0).getBalance()-income.getAmount())));
                                       } );
                                    setGraphic(btn);
                                    ImageView icon = new ImageView("/Image/trash.png");
                                    icon.setFitHeight(20);
                                    icon.setFitWidth(20);
                                    btn.setGraphic(icon);
                                    btn.setStyle("-fx-background-color: #3ca447;");
                                    setStyle("-fx-alignment : CENTER;");
                                    setText( null );
                                }
                            }
                        };
                        return cell;
                    }
                };

        removeCol.setCellFactory( cellFactory );
        
        incomeTable.getColumns().addAll(pockCol,amountCol,dateCol,removeCol);
        incomeTable.getStyleClass().add("myTable");
        revenuesList.addAll(db.getAllRevenues());
        
        incomeTable.setItems(revenuesList);
    }
    
    private void setComboBoxData(){
       
        ArrayList<Pocket> pockets = db.getAllPockets();
        
        for(int i=0;i<pockets.size();i++){
            
            Pocket pock = pockets.get(i);
            pocketNames.add(pock.getName());
        }
       
        transferCombo2.getItems().addAll(pocketNames);
        transferCombo.getItems().addAll(pocketNames);
    }
    //egyenleg megjelenitéséért felelős függvény
    private void balance(){
        
        Label[] labels = {mainBalance, newHomeBalance, newCarBalance, travelBalance, otherBalance};
        
        for(int i=0;i<labels.length;i++){
            labels[i].setText(String.valueOf(db.getBalance(i+1)));
        }
    }
    
    @FXML
    private void transfer(){
        
        String name = String.valueOf(transferCombo.getValue());
        String name2 = String.valueOf(transferCombo2.getValue());
        
        int amount=0;
        ArrayList <Pocket> pockets = db.getAllPockets();
        Pocket firstPocket = new Pocket();
        Pocket secondPocket = new Pocket();
      
        try{
            
            amount = Integer.parseInt(transferTF.getText());
            
            if(transferCombo.getSelectionModel().getSelectedItem()==null){
            
            warningLabel5.setVisible(true);
            warningLabel5.setText("Válasza ki a megfelelő zsebeket az utaláshoz!");
        }
        else if(transferCombo2.getSelectionModel().getSelectedItem()==null){
            
            warningLabel5.setVisible(true);
            warningLabel5.setText("Válasza ki a megfelelő zsebeket az utaláshoz!");
        }
        else if(name==name2){    
            
            warningLabel5.setVisible(true);
            warningLabel5.setText("Különbőző zsebek választása szükséges!");
            
        }else{
            
            for(int i =0;i<pockets.size();i++){

                Pocket pock = pockets.get(i);

                if(name.equals(pock.getName())){
                    firstPocket = new Pocket(pock.getId(),pock.getName(),pock.getBalance()-amount);
                }
                if(name2.equals(pock.getName())){
                    secondPocket = new Pocket(pock.getId(),pock.getName(),pock.getBalance()+amount);
                }
            }
            
                db.updateBalance(firstPocket);
                db.updateBalance(secondPocket);
                balance();
                warningLabel5.setVisible(false);
                transferTF.clear();
                
        }
        
        }catch(NumberFormatException ex){
                
                warningLabel5.setVisible(true);
                warningLabel5.setText("A berírt összeg nem megfelelő!");
                
        }
        
        
    }
        
    private void setMenuData(){
       
        TreeItem<String> treeItemRoot1 = new TreeItem <>("Menü");
        TreeView <String> treeView = new TreeView<>(treeItemRoot1);
        treeView.setShowRoot(false);
        treeView.getStyleClass().add("myTree");
        
        Node expNode = new ImageView(new Image(getClass().getResourceAsStream("/Image/shopping.png")));
        TreeItem <String> nodeItemA = new TreeItem <>("Kiadások",expNode);
        nodeItemA.setExpanded(true);
        
        Node incomeNode = new ImageView(new Image(getClass().getResourceAsStream("/Image/income.png")));
        TreeItem <String> nodeItemB = new TreeItem <>("Bevételek",incomeNode);
        
        Node exitNode = new ImageView(new Image(getClass().getResourceAsStream("/Image/exit.png")));
        TreeItem <String> nodeItemC = new TreeItem <>("Kilépés",exitNode);
        
        Node spendingNode = new ImageView(new Image(getClass().getResourceAsStream("/Image/add.png")));
        Node statisticsNode = new ImageView(new Image(getClass().getResourceAsStream("/Image/statistics.png")));
        Node tableNode = new ImageView(new Image(getClass().getResourceAsStream("/Image/bankcard.png")));
        Node diagramNode = new ImageView(new Image(getClass().getResourceAsStream("/Image/diagram.png")));
  
        TreeItem <String> nodeItemA1 = new TreeItem <>("Kiadás",spendingNode);
        TreeItem <String> nodeItemA2 = new TreeItem <>("Statisztika",statisticsNode);
        TreeItem <String> nodeItemA3 = new TreeItem <>("Kimenő",tableNode);
        TreeItem <String> nodeItemA4 = new TreeItem <>("Diagramok",diagramNode);
        
        Node addIncomeNode = new ImageView(new Image(getClass().getResourceAsStream("/Image/dollar.png")));
        Node incomeTableNode = new ImageView(new Image(getClass().getResourceAsStream("/Image/master_card.png")));
        Node balanceNode = new ImageView(new Image(getClass().getResourceAsStream("/Image/balance.png")));
        
        TreeItem <String> nodeItemB1 = new TreeItem <>("Bevétel",addIncomeNode);
        TreeItem <String> nodeItemB2 = new TreeItem <>("Bejővő",incomeTableNode);
        TreeItem <String> nodeItemB3 = new TreeItem <>("Zsebek",balanceNode);
        
        nodeItemB.getChildren().addAll(nodeItemB1,nodeItemB2,nodeItemB3);
        nodeItemA.getChildren().addAll(nodeItemA1,nodeItemA2,nodeItemA3,nodeItemA4);
        treeItemRoot1.getChildren().addAll(nodeItemA,nodeItemB,nodeItemC);
        menuPane.getChildren().add(treeView);
        
         treeView.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener(){
                public void changed(ObservableValue observable, Object oldValue, Object newValue){
                    
                    TreeItem <String> selectItem =(TreeItem)newValue;
                    String selectedMenu = selectItem.getValue();
                    
                    if(selectedMenu!=null){
                        switch(selectedMenu){
                            case "Kiadások":
                                selectItem.setExpanded(true);
                                break;
                            case "Kiadás":
                                spendingPane.setVisible(true);
                                statisticsPane.setVisible(false);
                                diagramPane.setVisible(false);
                                tablePane.setVisible(false);
                                spendingTF.clear();
                                spendingType = null;
                                spendingDP.setValue(LocalDate.now());
                                warningLabel1.setVisible(false);
                                warningLabel2.setVisible(false);
                                incomePane.setVisible(false);
                                tablePane2.setVisible(false);
                                balancePane.setVisible(false);

                                houseButton.setStyle(allButtonStyle);
                                carButton.setStyle(allButtonStyle);
                                shoppingCartButton.setStyle(allButtonStyle);
                                shoppingBagButton.setStyle(allButtonStyle);
                                invoiceButton.setStyle(allButtonStyle);
                                studentButton.setStyle(allButtonStyle);
                                cameraButton.setStyle(allButtonStyle);
                                turtleButton.setStyle(allButtonStyle);
                                cocktailButton.setStyle(allButtonStyle);
                                bookButton.setStyle(allButtonStyle);
                                break;
                            case "Statisztika":
                                spendingPane.setVisible(false);
                                statisticsPane.setVisible(true);
                                diagramPane.setVisible(false);
                                tablePane.setVisible(false);
                                incomePane.setVisible(false);
                                tablePane2.setVisible(false);
                                balancePane.setVisible(false);
                                monthlyStatistic();
                                break;
                            case "Kimenő":
                                spendingPane.setVisible(false);
                                statisticsPane.setVisible(false);
                                diagramPane.setVisible(false);
                                tablePane.setVisible(true);
                                incomePane.setVisible(false);
                                tablePane2.setVisible(false);
                                balancePane.setVisible(false);
                                break;
                            case "Diagramok":
                                spendingPane.setVisible(false);
                                statisticsPane.setVisible(false);
                                diagramPane.setVisible(true);
                                tablePane.setVisible(false);
                                incomePane.setVisible(false);
                                tablePane2.setVisible(false);
                                balancePane.setVisible(false);
                                setSeriesData();
                                break;
                            case "Bevételek":
                               selectItem.setExpanded(true);
                               break;
                            case "Bevétel":
                                spendingPane.setVisible(false);
                                statisticsPane.setVisible(false);
                                diagramPane.setVisible(false);
                                tablePane.setVisible(false);
                                incomePane.setVisible(true);
                                tablePane2.setVisible(false);
                                balancePane.setVisible(false);
                                
                                incomeTF.clear();
                                pocketId = 0;
                                incomeDP.setValue(LocalDate.now());
                                warningLabel3.setVisible(false);
                                warningLabel4.setVisible(false);

                                walletButton.setStyle(allButtonStyle);
                                newHomeButton.setStyle(allButtonStyle);
                                newCarButton.setStyle(allButtonStyle);
                                palmButton.setStyle(allButtonStyle);
                                savingsButton.setStyle(allButtonStyle);
                               break;
                            case "Bejővő":
                                spendingPane.setVisible(false);
                                statisticsPane.setVisible(false);
                                diagramPane.setVisible(false);
                                tablePane.setVisible(false);
                                incomePane.setVisible(false);
                                tablePane2.setVisible(true);
                                balancePane.setVisible(false);
                               break;
                            case "Zsebek":
                                spendingPane.setVisible(false);
                                statisticsPane.setVisible(false);
                                diagramPane.setVisible(false);
                                tablePane.setVisible(false);
                                incomePane.setVisible(false);
                                tablePane2.setVisible(false);
                                balancePane.setVisible(true);
                                balance();
                                transferTF.clear();
                               break;
                            case "Kilépés":
                                System.exit(0);
                        }
                    }
                }
            });
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        spendingDP.setValue(LocalDate.now());
        incomeDP.setValue(LocalDate.now());
        setMenuData();
        setExpensesTableData();
        setBCData();
        setRevenuesTableData();
        setComboBoxData();
    }    
    
}
