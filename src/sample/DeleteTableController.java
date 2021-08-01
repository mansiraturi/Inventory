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
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteTableController implements Initializable {
    public TextField employee_id;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String connectQuery;
    String landing_pv;
    String sell_v;

    @FXML
    AnchorPane myAnchorPane;
    @FXML
    private TextField selectedQuantity;
    @FXML
    public TextField filterBox;
    @FXML
    public TableView<modelTable> tableView = new TableView<>();
    @FXML
    public TableColumn<modelTable, String> col_partNo = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_refPartNo = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_addOn = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, Integer> col_quantity = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_partFor = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_company = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_inventoryDate = new TableColumn<>();
    @FXML
    public TableColumn<adminModelTable, String> col_sourceOfPurchase = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_stockLocation = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_setOf = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_prefix = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_comment = new TableColumn<>();


    @FXML
    private TextField enteredProdCode;


    ObservableList<modelTable> observableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT * FROM `inventory_management`.`inward_item`";

        try {

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
            col_stockLocation.setCellValueFactory(new PropertyValueFactory<>("P_stockLocation"));
            col_setOf.setCellValueFactory(new PropertyValueFactory<>("P_setOf"));
            col_prefix.setCellValueFactory(new PropertyValueFactory<>("P_prefix"));
            col_comment.setCellValueFactory(new PropertyValueFactory<>("P_comment"));
            tableView.setItems(observableList);

            FilteredList<modelTable> filteredData = new FilteredList<>(observableList, b -> true);
            filterBox.textProperty().addListener((observableValue, s, t1) -> {
                filteredData.setPredicate(modelTable -> {
                    if (t1 == null || t1.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = t1.toLowerCase();

                    if (modelTable.getP_partNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (String.valueOf(modelTable.getP_refPartNumber()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (String.valueOf(modelTable.getP_quantity()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (modelTable.getP_invDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (modelTable.getP_partFor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<modelTable> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedData);

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
//    public void goLogin(javafx.event.ActionEvent actionEvent) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
//        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void goSearch(ActionEvent actionEvent) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminSearch.fxml")));
//        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void goDELETE_PUBLIC(ActionEvent actionEvent) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DELETE_PUBLIC.fxml")));
//        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    public void DeleteLog(ActionEvent actionEvent) throws IOException {
        ObservableList<modelTable> selectedItems = tableView.getSelectionModel().getSelectedItems();
        String E1 = "Please select a valid row and then proceed for delete.";
        String E2 = "Please enter a valid employee id and quantity and then proceed for delete.";
        if (selectedItems.size()==0 || selectedQuantity.getText().length()==0 || employee_id.getText().length()==0){
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();
            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Do you want to continue?");
            alert.getDialogPane().setHeaderText((selectedItems.size()==0) ? E1 : E2 );
            Optional<ButtonType> result = alert.showAndWait();
        }

        else {
            String selectedProdID = selectedItems.get(0).getP_partNumber();

//        String connectQuery1 = String.format("DELETE FROM `inventory_management`.`inward_item` WHERE part_no = '%s'", selectedProdID);
            String selectedquantity = selectedQuantity.getText();
            String employeeID = employee_id.getText();
            int newCount = selectedItems.get(0).getP_quantity() - Integer.parseInt(selectedquantity);
            ResultSet queryOutput;

            try {
                DatabaseConnectionDelete connectNow = new DatabaseConnectionDelete();
                Connection connectDB = connectNow.getConnection();
                String connectQuery0 = String.format("SELECT landing_pv,sell_v FROM `inventory_management`.`inward_item` WHERE part_no='%s'", selectedItems.get(0).getP_partNumber());
//            String connectQuery1 = String.format("SELECT sell_v FROM `inventory_management`.`inward_item` WHERE part_no='%s'", selectedItems.get(0).getP_partNumber());
                Statement statement = connectDB.createStatement();
                queryOutput = statement.executeQuery(connectQuery0);
                while (queryOutput.next()) {
                    landing_pv = queryOutput.getString(1);
                    sell_v = queryOutput.getString(2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String connectQuery3 = "INSERT INTO `deletelog`.`outward_item` (\n" +
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
                    "`emp_id`,\n" +
                    "`transaction_qt`) VALUES ('" + selectedItems.get(0).getP_partNumber() + "','" + selectedItems.get(0).getP_refPartNumber() + "','" + selectedItems.get(0).getP_addOn() + "','" + newCount + "','" + selectedItems.get(0).getP_partFor() + "','" + selectedItems.get(0).getP_company() + "','" + selectedItems.get(0).getP_invDate() + "','" + selectedItems.get(0).getP_sourceOfPurchase() + "','" + landing_pv + "','" + sell_v + "','" + selectedItems.get(0).getP_stockLocation() + "','" + selectedItems.get(0).getP_setOf() + "','" + selectedItems.get(0).getP_prefix() + "','" + selectedItems.get(0).getP_comment() + "','" + employeeID + "','" + selectedquantity + "'"+")";
//        System.out.println(connectQuery3);
//         String connectQuery2 = String.format("UPDATE `deletelog`.`outward_item` SET `quantity` = (SELECT `quantity` FROM (SELECT `quantity` FROM deletelog.outward_item WHERE `part_no` = '%s') as lpv ) - %s WHERE `part_no` = '%s';",selectedProdID,selectedquantity,selectedProdID);


//        String connectQuery2 = String.format("UPDATE `inventory_management`.`inward_item` SET `quantity` = (SELECT `quantity` FROM (SELECT `quantity` FROM inventory_management.inward_item WHERE `part_no` = '%s') as lpv ) - %s WHERE `part_no` = '%s';",selectedProdID,selectedquantity,selectedProdID);

            try {
                DatabaseConnectionDelete connectNow = new DatabaseConnectionDelete();
                Connection connectDB = connectNow.getConnection();

                Statement statement = connectDB.createStatement();
//            statement.executeUpdate(connectQuery1);
//            statement.executeUpdate(connectQuery2);
                statement.executeUpdate(connectQuery3);

            } catch (Exception e) {
                e.printStackTrace();
            }

            Stage stage = (Stage) myAnchorPane.getScene().getWindow();

            Alert.AlertType type = Alert.AlertType.CONFIRMATION;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("Do you want to confirm?");

            alert.getDialogPane().setHeaderText("Are you sure you want to delete" + Integer.parseInt(selectedquantity) + "of selected product.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
//        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
        }
    }
//    public void retrieveSearchedItems(ActionEvent actionEvent) {
//    }
}
