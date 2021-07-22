package sample;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class monthlyLog  implements Initializable {
    public DatePicker startdatelog;
    public DatePicker enddatelog;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TableView<modelTable> tableView = new TableView<>();
    @FXML
    public TableColumn<modelTable,String> col_partNo=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_refPartNo=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_addOn=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,Integer> col_quantity=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_partFor=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_company=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_inventoryDate=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_sourceOfPurchase=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,Integer> col_landingPurchaseValue=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,Integer> col_sellingValue=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_stockLocation=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_techDetails=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_setOf=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_prefix=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_comment=new TableColumn<>();



    ObservableList<modelTable> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        col_techDetails.setCellValueFactory(new PropertyValueFactory<>("P_stockLocation"));
        col_setOf.setCellValueFactory(new PropertyValueFactory<>("P_setOf"));
        col_prefix.setCellValueFactory(new PropertyValueFactory<>("P_prefix"));
        col_comment.setCellValueFactory(new PropertyValueFactory<>("P_comment"));
        tableView.setItems(observableList);

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String connectQuery = "SELECT * FROM `deletelog`.`deletemaster`";
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput.next()) {
                observableList.add(new modelTable(
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
                        queryOutput.getString("tech_details"),
                        queryOutput.getString("setof"),
                        queryOutput.getString("prefix"),
                        queryOutput.getString("comment")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public void monthlyLog(ActionEvent actionEvent) {
            LocalDate startDate=startdatelog.getValue();
            LocalDate endDate=enddatelog.getValue();
            String connectQuery = "SELECT * from `deletelog`.`deletemaster` where inventory_date between '" + startDate + "' and '" + endDate + "'";
            tableView.getItems().clear();

            try {
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();

                Statement statement = connectDB.createStatement();
                ResultSet queryOutput = statement.executeQuery(connectQuery);

                while(queryOutput.next()) {
                    observableList.add(new modelTable(
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
                            queryOutput.getString("tech_details"),
                            queryOutput.getString("setof"),
                            queryOutput.getString("prefix"),
                            queryOutput.getString("comment")));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    public void goDelete(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

