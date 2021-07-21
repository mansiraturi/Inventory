package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button submit;
    @FXML
    Button back;
    @FXML
    private Button choose;
    @FXML
    Button HOME;
    @FXML
    Label wrong;
    @FXML
    Label answer;


    @FXML
    private TextField PartNumber;
    @FXML
    private TextField ReferencePartNumber;
    @FXML
    private TextField Quantity;
    @FXML
    private TextArea AddOn;
    @FXML
    private TextField LandingValue;
    @FXML
    private TextField SellValue;
    @FXML
    private TextField setof;
    @FXML
    private TextArea MiscComment;


    @FXML
    PasswordField password;

    @FXML
    private ChoiceBox<String> partFor = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> Company = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> prefix = new ChoiceBox<>();

    @FXML
    private GridPane details;
    @FXML
    private AnchorPane myAnchorPane;

    @FXML
    private DatePicker InventoryDate;

    @FXML
    private TextArea techDetails;

    @FXML
    private TextArea SourceOfPurchase;

    private final String[] partfor = {"CR Injector","CR Pump","CR Rail","EUI/EUP AII","INCLINE PUMP","VE/EDC PUMP/ VP14/VPH4","Heavy/Special","China Parts","MISCELLANEOUS"};
    private final String[] companies = {"BOSCH", "DELPHI", "DENSO","ZEXEL","CONTINENTAL VDO","REDAT","DISA","CHINA","MOTOR PAL","OTHERS", "MISCELLANEOUS"};
    private final String[] pre={"CRI","CPP","CR","EUI","EUP","VE4","VEC","VP44","VP29"};

    public void goLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void checkLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        if (password.getText().equals("1234")) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("third.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            wrong.setVisible(true);
        }
    }


    public void goSample(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goAddNewItem(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddNewItem.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goAddExistingItem(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddExisting.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goAddItem(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItem.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goThird(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("third.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partFor.getItems().addAll(partfor);
        Company.getItems().addAll(companies);
        prefix.getItems().addAll(pre);
    }


String stockImage;

    public void singleFileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.jpg"));
        if (f != null) {
            choose.setText(f.getAbsolutePath());
            stockImage = f.getAbsolutePath();
        }
    }

    public void goDelete(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void godeleteItem(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Delete_Existing.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void godeleteHistory(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DeleteItemHistory.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goSearch(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Search.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void AddPart(ActionEvent actionEvent) {

boolean flag=false;
//        Random rand= new Random();
//
//        UPC.setText(myString);
        if (PartNumber.getText().length()==0 || Quantity.getText().length()==0 || InventoryDate.getValue().toString().length()==0 || SourceOfPurchase.getText().length()==0 || LandingValue.getText().length()==0 || SellValue.getText().length()==0 ) {
            flag=true;

            Stage stage = (Stage) myAnchorPane.getScene().getWindow();

            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("Fill in details");

            alert.getDialogPane().setHeaderText("the * marked fields are compulsory");
            Optional<ButtonType> result = alert.showAndWait();
        }
        else if  (flag==false) {

            String quantity = Quantity.getText();
            int l = quantity.length();
            int ctr = 1;
            int b = 1;
            for (int i = 0; i < l; i++) {
                if (quantity.charAt(i) >= '0' && quantity.charAt(i) <= '9') {
                    ctr++;
                } else {
                    b = 0;
                }
            }
            int ans = ctr * b;

            String Partnumber = PartNumber.getText();
            String RefPartnumber = ReferencePartNumber.getText();
            String addon = AddOn.getText();
            String Sourceofpurchase = SourceOfPurchase.getText();
            String landingValue = LandingValue.getText();
            String sellvalue = SellValue.getText();
            String PartFor = partFor.getValue().toString();
            String company = Company.getValue().toString();
            String inventoryDate = InventoryDate.getValue().toString();
            String StockLocation = stockImage;
            String TechDetails = techDetails.getText();
            answer.setVisible(false);
            if (ans != 0) {
                try {

                    Stage stage = (Stage) myAnchorPane.getScene().getWindow();

                    Alert.AlertType type = Alert.AlertType.CONFIRMATION;
                    Alert alert = new Alert(type, "");

                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner(stage);

                    alert.getDialogPane().setContentText("Do you want to confirm?");

                    alert.getDialogPane().setHeaderText("You have given the correct information about the products.\nUnique Product Code Generated is ");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        // addData(Partnumber,RefPartnumber,addon,quantity, PartFor, company,inventoryDate,Sourceofpurchase,landingValue,sellvalue, StockLocation, TechDetails);

                        Code128Bean code128 = new Code128Bean();
                        String myString = PartNumber.getText();
                        String image_name = PartNumber.getText() + ".png";
                        code128.setHeight(15f);
                        code128.setModuleWidth(0.3);
                        code128.setQuietZone(10);
                        code128.doQuietZone(true);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
                        code128.generateBarcode(canvas, myString);
                        canvas.finish();
                        //write to png file
                        FileOutputStream fos = new FileOutputStream("C:\\Users\\4manm\\IdeaProjects\\GG\\INVENTORY\\Barcode\\Barcode" + image_name);
                        fos.write(baos.toByteArray());
                        fos.flush();
                        fos.close();


                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItem.fxml")));
                        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }
            } else {
                answer.setVisible(true);
                Quantity.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                Quantity.setText("*Enter digits from 0 to 9");
            }

        }
    }


    public void AddSystem(ActionEvent actionEvent) {
        boolean flag=false;
        if (PartNumber.getText().length()==0 || Quantity.getText().length()==0 || InventoryDate.getValue().toString().length()==0 || SourceOfPurchase.getText().length()==0 || LandingValue.getText().length()==0 || SellValue.getText().length()==0 || prefix.getValue().length()==0) {
            flag=true;
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();

            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("Fill in details");

            alert.getDialogPane().setHeaderText("the * marked fields are compulsory");
            Optional<ButtonType> result = alert.showAndWait();
        }
        else if  (flag==false) {

            String Partnumber = PartNumber.getText();
            String RefPartNumber = ReferencePartNumber.getText();
            String quantity = Quantity.getText();
            int l = quantity.length();
            int ctr = 1;
            int b = 1;
            for (int i = 0; i < l; i++) {
                if (quantity.charAt(i) >= '0' && quantity.charAt(i) <= '9') {
                    ctr++;
                } else {
                    b = 0;
                }
            }
            int ans = ctr * b;
            String addon = AddOn.getText();
            String Sourceofpurchase = SourceOfPurchase.getText();
            String landingValue = LandingValue.getText();
            String sellvalue = SellValue.getText();
            String PartFor = partFor.getValue();
            String company = Company.getValue();
            String inventoryDate = InventoryDate.getValue().toString();
            String StockLocation = stockImage;
            String TechDetails = "";
            String Comment = "";

            String setOf = setof.getText();
            String Prefix = prefix.getValue();
            SourceOfPurchase.setText("*Please fill the field");

            PartFor = PartFor == null ? "" : PartFor.toString();
            company = company == null ? "" : company.toString();
            RefPartNumber = RefPartNumber == null ? "" : RefPartNumber.toString();

//        Random rand= new Random();
//        UPC.setText(myString);

            if (ans != 0) {
                try {

                    Stage stage = (Stage) myAnchorPane.getScene().getWindow();

                    Alert.AlertType type = Alert.AlertType.CONFIRMATION;
                    Alert alert = new Alert(type, "");

                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner(stage);

                    alert.getDialogPane().setContentText("Do you want to confirm?");

                    alert.getDialogPane().setHeaderText("You have given the correct information about the products.\nUnique Product Code Generated is ");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        // addData(Partnumber,RefPartNumber,addon,quantity, PartFor, company,inventoryDate,Sourceofpurchase,landingValue,sellvalue, StockLocation, TechDetails,setOf, Prefix, Comment);
//
//             Code128Bean code128 = new Code128Bean();
//             String myString = PartNumber.getText();
//             String image_name = PartNumber.getText() + ".png";
//             code128.setHeight(15f);
//             code128.setModuleWidth(0.3);
//             code128.setQuietZone(10);
//             code128.doQuietZone(true);
//             ByteArrayOutputStream baos = new ByteArrayOutputStream();
//             BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
//             code128.generateBarcode(canvas, myString);
//             canvas.finish();
//             //write to png file
//             FileOutputStream fos = new FileOutputStream("C:\\Users\\Mansi\\IdeaProjects\\GG\\INVENTORY\\Barcode\\Barcode" + image_name);
//             fos.write(baos.toByteArray());
//             fos.flush();
//             fos.close();


                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItem.fxml")));
                        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }
            } else {
                // Quantity.setVisible(true);
                Quantity.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                Quantity.setText("*Enter digits from 0 to 9");
            }
        }
    }
    public void AddMisc(ActionEvent actionEvent) {
        boolean flag = false;
        if (PartNumber.getText().length() == 0) {
            flag = true;
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();

            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("Fill in details");

            alert.getDialogPane().setHeaderText("the * marked fields are compulsory");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (flag == false) {

            String Partnumber = PartNumber.getText();
            //String RefPartNumber = ReferencePartNumber.getText();
            String quantity = Quantity.getText();
            int l = quantity.length();
            int ctr = 1;
            int b = 1;
            for (int i = 0; i < l; i++) {
                if (quantity.charAt(i) >= '0' && quantity.charAt(i) <= '9') {
                    ctr++;
                } else {
                    b = 0;
                }
            }
            int ans = ctr * b;
            String RefPartNumber = "";
            String addon = "";
            String Sourceofpurchase = SourceOfPurchase.getText();
            String landingValue = LandingValue.getText();
            String sellvalue = SellValue.getText();
            String PartFor = "";
            String company = "";
            String inventoryDate = InventoryDate.getValue().toString();
            String StockLocation = stockImage;
            String TechDetails = techDetails.getText();
            String comment = MiscComment.getText();

            String setOf = "";
            String Prefix = "";

            RefPartNumber = RefPartNumber == null ? "" : RefPartNumber.toString();

//        Random rand= new Random();
//        UPC.setText(myString);
    if(ans!=0) {
        try {

        Stage stage = (Stage) myAnchorPane.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setContentText("Do you want to confirm?");

        alert.getDialogPane().setHeaderText("You have given the correct information about the products.\nUnique Product Code Generated is ");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            addData(Partnumber, RefPartNumber, addon, quantity, PartFor, company, inventoryDate, Sourceofpurchase, landingValue, sellvalue, StockLocation, TechDetails, setOf, Prefix, comment);

//                Code128Bean code128 = new Code128Bean();
//                String myString = PartNumber.getText() ;
//                String image_name = PartNumber.getText() + ".png";
//                code128.setHeight(15f);
//                code128.setModuleWidth(0.3);
//                code128.setQuietZone(10);
//                code128.doQuietZone(true);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
//                code128.generateBarcode(canvas, myString);
//                canvas.finish();
//                //write to png file
//                FileOutputStream fos = new FileOutputStream("C:\\Users\\4manm\\IdeaProjects\\GG\\INVENTORY\\Barcode\\Barcode" + image_name);
//                fos.write(baos.toByteArray());
//                fos.flush();
//                fos.close();


            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItem.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    } catch (Exception e) {
        // TODO: handle exception
    }
}
else {
    // Quantity.setVisible(true);
        Quantity.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        Quantity.setText("*Enter digits from 0 to 9");
    }
        }
    }
    public void goDELETE_PUBLIC(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DELETE_PUBLIC.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void itemDetails() {
        details.setVisible(true);
    }

    public void AddExisting(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setContentText("Do you want to confirm?");

        alert.getDialogPane().setHeaderText("You have given the correct information about the products.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItem.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void DeleteExisting(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setContentText("Do you want to confirm?");

        alert.getDialogPane().setHeaderText("You have given the correct information about the products.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

            public void DeleteHistory(ActionEvent actionEvent) throws IOException {
                Stage stage = (Stage) myAnchorPane.getScene().getWindow();

                Alert.AlertType type = Alert.AlertType.CONFIRMATION;
                Alert alert = new Alert(type, "");

                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);

                alert.getDialogPane().setContentText("Do you want to confirm?");

                alert.getDialogPane().setHeaderText("You have given the correct information about the products.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete.fxml")));
                    stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
    }

    public void DeleteLog(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setContentText("Do you want to confirm?");

        alert.getDialogPane().setHeaderText("You have given the correct information about the products.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void godeleteLog(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("deleteLog.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addData(String PartNumber,String ReferencePartNumber,String AddOn,String Quantity,String PartFor,String Company,String InventoryDate,String SourceOfPurchase,String LandingPurchaseValue,String SellValue,String StockLocation,String TechDetails,String setof, String prefix, String Comment){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "INSERT INTO `inventory_management`.`inward_item` (`part_no`,\n" +
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
                "`tech_details`,\n" +
                "`setof`,\n" +
                "`prefix`,\n" +
                "`comment`) VALUES ('"+PartNumber+"','"+ReferencePartNumber+"','"+AddOn+"','"+Quantity+"','"+PartFor+"','"+Company+"','"+InventoryDate+"','"+SourceOfPurchase+"','"+LandingPurchaseValue+"','"+SellValue+"','"+StockLocation+"','"+TechDetails+"','"+setof+"','"+prefix+"','"+Comment+"'"+")";
//        System.out.print(connectQuery);

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(connectQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goAddNewMISC(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddFormMISC.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goAddNewPart(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddFormPart.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goAddNewSystem(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddFormSystem.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void monthlylog(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("monthly log.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goAddExistingEdit(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddExistingEdit.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
