package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class adminSearchTableController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String connectQuery;

    @FXML
    public TextField filterBox;
    @FXML
    public TableView<adminModelTable> tableView=new TableView<>();
    @FXML
    public TableColumn<adminModelTable,String> col_partNo=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,String> col_refPartNo=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,String> col_addOn=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,Integer> col_quantity=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,String> col_partFor=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,String> col_company=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,String> col_inventoryDate=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,String> col_sourceOfPurchase=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,Integer> col_landingPurchaseValue=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,Integer> col_sellingValue=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,String> col_stockLocation=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,String> col_setOf=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,String> col_prefix=new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable,String> col_comment=new TableColumn<>();

    @FXML
    private TextField enteredProdCode;

    ObservableList<adminModelTable> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT * FROM `inventory_management`.`inward_item`";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while(queryOutput.next()) {
                observableList.add(new adminModelTable(
                        queryOutput.getString("part_no"),
                        queryOutput.getString("ref_part_no"),
                        queryOutput.getString("add_on"),
                        queryOutput.getInt("quantity"),
                        queryOutput.getString("part_for"),
                        queryOutput.getString("company"),
                        queryOutput.getString("inventory_date"),
                        queryOutput.getString("source_of_p"),
                        queryOutput.getInt("landing_pv"),
                        queryOutput.getInt("sell_v"),
                        queryOutput.getString("stock_loc"),
                        queryOutput.getString("setof"),
                        queryOutput.getString("prefix"),
                        queryOutput.getString("comment")));
            }

            col_partNo.setCellValueFactory(new PropertyValueFactory<>("P_partNumber"));
            col_refPartNo.setCellValueFactory(new PropertyValueFactory<>("P_refPartNumber"));
            col_addOn.setCellValueFactory(new PropertyValueFactory<>("P_addOn"));
            col_quantity.setCellValueFactory(new PropertyValueFactory<>("P_quantity"));
            col_partFor.setCellValueFactory(new PropertyValueFactory<>("P_partFor"));
            col_company.setCellValueFactory(new PropertyValueFactory<>("P_company"));
            col_inventoryDate.setCellValueFactory(new PropertyValueFactory<>("P_invDate"));
            col_sourceOfPurchase.setCellValueFactory(new PropertyValueFactory<>("P_sourceOfPurchase"));
            col_landingPurchaseValue.setCellValueFactory(new PropertyValueFactory<>("P_landingPurchaseValue"));
            col_sellingValue.setCellValueFactory(new PropertyValueFactory<>("P_sellingValue"));
            col_stockLocation.setCellValueFactory(new PropertyValueFactory<>("P_stockLocation"));
            col_setOf.setCellValueFactory(new PropertyValueFactory<>("P_setOf"));
            col_prefix.setCellValueFactory(new PropertyValueFactory<>("P_prefix"));
            col_comment.setCellValueFactory(new PropertyValueFactory<>("P_comment"));
            tableView.setItems(observableList);

            FilteredList<adminModelTable> filteredData= new FilteredList<>(observableList, b->true);
            filterBox.textProperty().addListener((observableValue, s, t1) -> {
                filteredData.setPredicate(modelTable -> {
                    if(t1==null || t1.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter=t1.toLowerCase();

                    if(modelTable.getP_partNumber().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }
                    if(String.valueOf(modelTable.getP_refPartNumber()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }
                    if(String.valueOf(modelTable.getP_quantity()).toLowerCase().indexOf(lowerCaseFilter)!=-1) {
                        return true;
                    }
                    if(modelTable.getP_invDate().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
                        return true;
                    }
                    if(modelTable.getP_partFor().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
                        return true;
                    }
                    else
                        return false;
                });
            });

            SortedList<adminModelTable> sortedData=new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedData);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goSample(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void retrieveSearchedItems(ActionEvent event) {
    }

    public void goThird(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("third.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

