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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteLogController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String connectQuery;
    public DatePicker startdatelog;
    public DatePicker enddatelog;
    String connectQuery1;

    @FXML
    private TextField selectedQuantity;
    @FXML
    AnchorPane myAnchorPane;
    @FXML
    public TextField filterBox=new TextField();
    @FXML
    public TableView<deleteLogModelTable> tableView = new TableView<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_partNo = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_employeeID = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_refPartNo = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_addOn = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, Integer> col_quantity = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, Integer> col_quantityUsed = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_partFor = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_company = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_inventoryDate = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_date_time = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_sourceOfPurchase = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, Integer> col_landingPurchaseValue = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, Integer> col_sellingValue = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_stockLocation = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_setOf = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_prefix = new TableColumn<>();
    @FXML
    public TableColumn<deleteLogModelTable, String> col_comment = new TableColumn<>();


    @FXML
    private TextField enteredProdCode;


    ObservableList<deleteLogModelTable> observableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT * FROM `deletelog`.`outward_item`";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput.next()) {
                observableList.add(new deleteLogModelTable(
                        queryOutput.getString("part_no"),
                        queryOutput.getString("ref_part_no"),
                        queryOutput.getString("emp_id"),
                        queryOutput.getString("add_on"),
                        queryOutput.getInt("quantity"),
                        queryOutput.getInt("transaction_qt"),
                        queryOutput.getString("part_for"),
                        queryOutput.getString("company"),
                        queryOutput.getString("inventory_date"),
                        queryOutput.getString("transaction_dt"),
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
            col_employeeID.setCellValueFactory(new PropertyValueFactory<>("P_emp_id"));
            col_quantityUsed.setCellValueFactory(new PropertyValueFactory<>("P_quantityUsed"));
            col_quantity.setCellValueFactory(new PropertyValueFactory<>("P_quantity"));
            col_partFor.setCellValueFactory(new PropertyValueFactory<>("P_partFor"));
            col_company.setCellValueFactory(new PropertyValueFactory<>("P_company"));
            col_inventoryDate.setCellValueFactory(new PropertyValueFactory<>("P_invDate"));
            col_date_time.setCellValueFactory(new PropertyValueFactory<>("P_date_time"));
            col_sourceOfPurchase.setCellValueFactory(new PropertyValueFactory<>("P_sourceOfPurchase"));
            col_landingPurchaseValue.setCellValueFactory(new PropertyValueFactory<>("P_landingPurchaseValue"));
            col_sellingValue.setCellValueFactory(new PropertyValueFactory<>("P_sellingValue"));
            col_stockLocation.setCellValueFactory(new PropertyValueFactory<>("P_stockLocation"));
            col_addOn.setCellValueFactory(new PropertyValueFactory<>("P_addOn"));
            col_setOf.setCellValueFactory(new PropertyValueFactory<>("P_setOf"));
            col_prefix.setCellValueFactory(new PropertyValueFactory<>("P_prefix"));
            col_comment.setCellValueFactory(new PropertyValueFactory<>("P_comment"));
            tableView.setItems(observableList);

//            FilteredList<deleteLogModelTable> filteredData = new FilteredList<>(observableList, b -> true);
//            filterBox.textProperty().addListener((observableValue, s, t1) -> {
//                filteredData.setPredicate(modelTable -> {
//                    if (t1 == null || t1.isEmpty()) {
//                        return true;
//                    }
//                    String lowerCaseFilter = t1.toLowerCase();
//
//                    if (modelTable.getP_partNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                        return true;
//                    }
//                    if (String.valueOf(modelTable.getP_refPartNumber()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                        return true;
//                    }
//                    if (String.valueOf(modelTable.getP_quantity()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                        return true;
//                    }
//                    if (modelTable.getP_invDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                        return true;
//                    }
//                    if (modelTable.getP_partFor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                        return true;
//                    } else
//                        return false;
//                });
//            });
//
//            SortedList<deleteLogModelTable> sortedData = new SortedList<>(filteredData);
//            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
//            tableView.setItems(sortedData);

        } catch (Exception e) {
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

//    public void retrieveSearchedItems(ActionEvent event) {
//        String ProdCode = enteredProdCode.getText();
//        connectQuery = String.format("SELECT * FROM `inventory_management`.`product_details` where prod_code REGEXP '^%s'", ProdCode);
//        tableView.getItems().clear();
//
//        try {
//            DatabaseConnection connectNow = new DatabaseConnection();
//            Connection connectDB = connectNow.getConnection();
//
//            Statement statement = connectDB.createStatement();
//            ResultSet queryOutput = statement.executeQuery(connectQuery);
//
//            while (queryOutput.next()) {
//                observableList.add(new modelTable(
//                        queryOutput.getInt("prod_code"),
//                        queryOutput.getString("mfd"),
//                        queryOutput.getString("last_date"),
//                        queryOutput.getString("stock_location"),
//                        queryOutput.getString("company"),
//                        queryOutput.getString("comment")));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//}


//    public void showRowDetails(ActionEvent event) {
//        ObservableList<modelTable> productList;
//        productList = tableView.getSelectionModel().getSelectedItems();
//        Integer selectedProdID = productList.get(0).getPid();
//
//        connectQuery = String.format("DELETE FROM `inventory_management`.`product_details` WHERE u_ID IN ( SELECT pid FROM\n" +
//                "(SELECT u_ID AS pid FROM `inventory_management`.`product_details` WHERE prod_code = %d) AS p );\n",selectedProdID);
//
//        int quantity = 5;
//        connectQuery = String.format("UPDATE `inventory_management`.`product_details` SET quantity = %d WHERE u_ID = %d", quantity,selectedProdID);
//
//        System.out.println(connectQuery);
//

    //    }
    public void goLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goSearch(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminSearch.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goDELETE_PUBLIC(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DELETE_PUBLIC.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void DeleteLogMaster(ActionEvent actionEvent) throws IOException {
        ObservableList<deleteLogModelTable> selectedItems = tableView.getSelectionModel().getSelectedItems();
        String E1 = "Please select a valid row and then proceed for delete.";
        if (selectedItems.size() == 0) {
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Do you want to continue?");
            alert.getDialogPane().setHeaderText(E1);
            Optional<ButtonType> result = alert.showAndWait();
        } else {

            Stage stage = (Stage) myAnchorPane.getScene().getWindow();

            Alert.AlertType type = Alert.AlertType.CONFIRMATION;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("Do you want to confirm?");

            alert.getDialogPane().setHeaderText("Are you sure you want to delete selected product.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
//                ObservableList<deleteLogModelTable> selectedItems = tableView.getSelectionModel().getSelectedItems();
                String selectedProdID = selectedItems.get(0).getP_partNumber();
                Integer selectedQuantity = selectedItems.get(0).getP_quantity();
                boolean flag = selectedQuantity < 0;

                String connectQuery = "INSERT INTO `deletelog`.`deletemaster` (\n" +
                        "`part_no`,\n" +
                        "`ref_part_no`,\n" +
                        "`add_on`,\n" +
                        "`quantity`,\n" +
                        "`part_for`,\n" +
                        "`company`,\n" +
                        "`inventory_date`,\n" +
                        "`source_of_p`,\n" +
                        "`landing_pv`,\n" +
                        "`sell_v`,\n" +
                        "`stock_loc`,\n" +
                        "`setof`,\n" +
                        "`prefix`,\n" +
                        "`comment`,\n" +
                        "`transaction_dt`,\n" +
                        "`emp_id`,\n" +
                        "`transaction_qt`) VALUES ('" + selectedItems.get(0).getP_partNumber() + "','" + selectedItems.get(0).getP_refPartNumber() + "','" + selectedItems.get(0).getP_addOn() + "','" + selectedItems.get(0).getP_quantity() + "','" + selectedItems.get(0).getP_partFor() + "','" + selectedItems.get(0).getP_company() + "','" + selectedItems.get(0).getP_invDate() + "','" + selectedItems.get(0).getP_sourceOfPurchase() + "','" + selectedItems.get(0).getP_landingPurchaseValue() + "','" + selectedItems.get(0).getP_sellingValue() + "','" + selectedItems.get(0).getP_stockLocation() + "','" + selectedItems.get(0).getP_setOf() + "','" + selectedItems.get(0).getP_prefix() + "','" + selectedItems.get(0).getP_comment() + "','" + selectedItems.get(0).getP_date_time() + "','" + selectedItems.get(0).getP_emp_id() + "','" + selectedItems.get(0).getP_quantityUsed() + "'" + ")";

//            connectQuery1 = flag ? String.format("UPDATE `inventory_management`.`inward_item` SET `quantity` = 0 WHERE `part_no` = '%s';",selectedProdID) :  String.format("UPDATE `inventory_management`.`inward_item` SET `quantity` = (SELECT `quantity` FROM (SELECT `quantity` FROM inventory_management.inward_item WHERE `part_no` = '%s') as lpv ) - %s WHERE `part_no` = '%s';",selectedProdID,selectedItems.get(0).getP_quantity(),selectedProdID);

                connectQuery1 = flag ? String.format("UPDATE `inventory_management`.`inward_item` SET `quantity` = 0 WHERE `part_no` = '%s';", selectedProdID) : String.format("UPDATE `inventory_management`.`inward_item` SET `quantity` = %s WHERE `part_no` = '%s';", selectedItems.get(0).getP_quantity(), selectedProdID);
                String connectQuery3 = String.format("DELETE FROM `deletelog`.`outward_item` WHERE part_no = '%s'", selectedProdID);

                if (flag) {
                    stage = (Stage) myAnchorPane.getScene().getWindow();

                    type = Alert.AlertType.CONFIRMATION;
                    alert = new Alert(type, "");

                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner(stage);

                    alert.getDialogPane().setContentText("Do you want to confirm?");

                    alert.getDialogPane().setHeaderText("The selected item's quantity count is less than 0. Would you like to add similar new items? ");
                    result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddNewItem.fxml")));
                        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                }


                try {
                    DatabaseConnectionDelete connectNow = new DatabaseConnectionDelete();
                    Connection connectDB = connectNow.getConnection();

                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(connectQuery);
                    statement.executeUpdate(connectQuery1);
                    statement.executeUpdate(connectQuery3);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                observableList.removeAll(selectedItems);
//            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
//            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
            }
        }
    }

    public void retrieveSearchedItems(ActionEvent actionEvent) {
    }

    public void goDelete(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void monthlyLog(ActionEvent actionEvent) {
        if (startdatelog.getValue() == null || enddatelog.getValue() == null) {

            Stage stage = (Stage) myAnchorPane.getScene().getWindow();

            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("Do you want to continue?");

            alert.getDialogPane().setHeaderText("Please provide valid start and end date.");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            LocalDate startDate = startdatelog.getValue();
            LocalDate endDate = enddatelog.getValue();
            String connectQuery = "SELECT * from `deletelog`.`outward_item` where inventory_date between '" + startDate + "' and '" + endDate + "'";
            System.out.println(connectQuery);
            tableView.getItems().clear();

            try {
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();

                Statement statement = connectDB.createStatement();
                ResultSet queryOutput = statement.executeQuery(connectQuery);

                while (queryOutput.next()) {
                    observableList.add(new deleteLogModelTable(
                            queryOutput.getString("part_no"),
                            queryOutput.getString("ref_part_no"),
                            queryOutput.getString("emp_id"),
                            queryOutput.getString("add_on"),
                            queryOutput.getInt("quantity"),
                            queryOutput.getInt("transaction_qt"),
                            queryOutput.getString("part_for"),
                            queryOutput.getString("company"),
                            queryOutput.getString("inventory_date"),
                            queryOutput.getString("transaction_dt"),
                            queryOutput.getString("source_of_p"),
                            queryOutput.getInt("landing_pv"),
                            queryOutput.getInt("sell_v"),
                            queryOutput.getString("stock_loc"),
                            queryOutput.getString("setof"),
                            queryOutput.getString("prefix"),
                            queryOutput.getString("comment")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
