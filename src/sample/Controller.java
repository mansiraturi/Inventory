package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import java.util.Objects;
import java.awt.image.BufferedImage;

//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    Label mismatch;

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
    private TextField SECODE;

    @FXML
    PasswordField password;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;

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
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select count(1) from password WHERE pwd = '" + password.getText() + "'";
        try {
            Statement stmt = connectDB.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("third.fxml")));
                    stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    wrong.setVisible(true);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//            if (password.getText().equals(pass)){
//                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("third.fxml")));
//                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//            } else {
//                wrong.setVisible(true);
//            }


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
        if (PartNumber.getText().length()==0 || Quantity.getText().length()==0 || InventoryDate.getValue()==null || LandingValue.getText().length()==0 || SellValue.getText().length()==0 ) {
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

        else if  (!flag) {
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
            int ans1 = ctr * b;


        String Partnumber = PartNumber.getText();
        String RefPartNumber = ReferencePartNumber.getText();
//        String quantity = Quantity.getText();
        String addon = AddOn.getText();
        String Sourceofpurchase = SourceOfPurchase.getText();
        String landingValue= LandingValue.getText();
        int l2 = landingValue.length();
            int ctr2 = 1;
            int a = 1;
            for (int i = 0; i < l2; i++) {
                if (landingValue.charAt(i) >= '0' && landingValue.charAt(i) <= '9') {
                    ctr2++;
                } else {
                    a = 0;
                }
            }
            int ans2 = ctr2 * a;
        String sellvalue= SellValue.getText();
            int l3 = sellvalue.length();
            int ctr3 = 1;
            int c = 1;
            for (int i = 0; i < l3; i++) {
                if (sellvalue.charAt(i) >= '0' && sellvalue.charAt(i) <= '9') {
                    ctr3++;
                } else {
                    c = 0;
                }
            }
            int ans3 = ctr3 * c;
        String PartFor = partFor.getValue();
        String company = Company.getValue();
        String inventoryDate = InventoryDate.getValue().toString();
        String StockLocation = stockImage;
        String Comment = "";

        String setOf = "";
        String Prefix = "";

        PartFor = PartFor==null ? "" : PartFor.toString();
        company = company==null ? "" : company.toString();
        RefPartNumber = RefPartNumber==null ? "" : RefPartNumber.toString();

//        Random rand= new Random();
//        UPC.setText(myString);
        if (ans1 != 0 && ans2!=0 && ans3!=0) {
            try {

                Stage stage = (Stage) myAnchorPane.getScene().getWindow();

                Alert.AlertType type = Alert.AlertType.CONFIRMATION;
                Alert alert = new Alert(type, "");

                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);

                alert.getDialogPane().setContentText("Do you want to confirm?");

                alert.getDialogPane().setHeaderText("You have given the correct information about the products.");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    addData(Partnumber, RefPartNumber, addon, quantity, PartFor, company, inventoryDate, Sourceofpurchase, landingValue, sellvalue, StockLocation, setOf, Prefix, Comment);

                Code128Bean code128 = new Code128Bean();
                String myString = PartNumber.getText() ;
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
        }
        else if  (ans1==0) {
            Quantity.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            Quantity.setText("*Enter digits from 0 to 9");
            if ((ans2 == 0) && (ans3 == 0)) {
                LandingValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                LandingValue.setText("*Enter digits from 0 to 9");
                SellValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                SellValue.setText("*Enter digits from 0 to 9");
            } else if (ans2 == 0) {
                LandingValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                LandingValue.setText("*Enter digits from 0 to 9");
            } else if (ans3 == 0) {
                SellValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                SellValue.setText("*Enter digits from 0 to 9");
            } else{
                ;
            }
        }
        else if(ans2==0) {
            LandingValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            LandingValue.setText("*Enter digits from 0 to 9");
            if (ans3 == 0) {
                SellValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                SellValue.setText("*Enter digits from 0 to 9");
            }
            else{;}
        }
        else if(ans3==0) {
            SellValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            SellValue.setText("*Enter digits from 0 to 9");
        }
            }

        }

    public void AddSystem(ActionEvent actionEvent) {
        boolean flag = false;
        if (PartNumber.getText().length() == 0 || Quantity.getText().length() == 0 || InventoryDate.getValue() == null || LandingValue.getText().length() == 0 || LandingValue.getText().length()==0 || SellValue.getText().length() == 0 || prefix.getValue() == null) {
            flag = true;
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();

            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("Fill in details");

            alert.getDialogPane().setHeaderText("the * marked fields are compulsory");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (!flag) {

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
            int ans1 = ctr * b;

//        String Partnumber = PartNumber.getText();
//        String RefPartNumber = ReferencePartNumber.getText();
//        String quantity = Quantity.getText();
            String addon = AddOn.getText();
            String Sourceofpurchase = SourceOfPurchase.getText();
            String landingValue = LandingValue.getText();
            int l2 = landingValue.length();
            int ctr2 = 1;
            int a = 1;
            for (int i = 0; i < l2; i++) {
                if (landingValue.charAt(i) >= '0' && landingValue.charAt(i) <= '9') {
                    ctr2++;
                } else {
                    a = 0;
                }
            }
            int ans2 = ctr2 * a;
            String sellvalue= SellValue.getText();
            int l3 = sellvalue.length();
            int ctr3 = 1;
            int c = 1;
            for (int i = 0; i < l2; i++) {
                if (sellvalue.charAt(i) >= '0' && sellvalue.charAt(i) <= '9') {
                    ctr3++;
                } else {
                    c = 0;
                }
            }
            int ans3 = ctr3 * c;
            String PartFor = partFor.getValue();
            String company = Company.getValue();
            String inventoryDate = InventoryDate.getValue().toString();
            String StockLocation = stockImage;
            String Comment = "";

            String setOf = setof.getText();
            String Prefix = prefix.getValue();

            PartFor = PartFor == null ? "" : PartFor.toString();
            company = company == null ? "" : company.toString();
            RefPartNumber = RefPartNumber == null ? "" : RefPartNumber.toString();

//        Random rand= new Random();
//        UPC.setText(myString);
            if ((ans1 != 0) && (ans2!=0) && (ans3!=0)) {
                try {

                    Stage stage = (Stage) myAnchorPane.getScene().getWindow();

                    Alert.AlertType type = Alert.AlertType.CONFIRMATION;
                    Alert alert = new Alert(type, "");

                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner(stage);

                    alert.getDialogPane().setContentText("Do you want to confirm?");

                    alert.getDialogPane().setHeaderText("You have given the correct information about the products. ");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        addData(Partnumber, RefPartNumber, addon, quantity, PartFor, company, inventoryDate, Sourceofpurchase, landingValue, sellvalue, StockLocation, setOf, Prefix, Comment);

                Code128Bean code128 = new Code128Bean();
                String myString = PartNumber.getText() ;
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
            } else if  (ans1==0) {
                Quantity.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                Quantity.setText("*Enter digits from 0 to 9");
                if ((ans2 == 0) && (ans3 == 0)) {
                    LandingValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    LandingValue.setText("*Enter digits from 0 to 9");
                    SellValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    SellValue.setText("*Enter digits from 0 to 9");
                } else if (ans2 == 0) {
                    LandingValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    LandingValue.setText("*Enter digits from 0 to 9");
                } else if (ans3 == 0) {
                    SellValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    SellValue.setText("*Enter digits from 0 to 9");
                } else{
                    ;
                }
            }
            else if(ans2==0) {
                LandingValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                LandingValue.setText("*Enter digits from 0 to 9");
                if (ans3 == 0) {
                    SellValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    SellValue.setText("*Enter digits from 0 to 9");
                }
                else{;}
            }
            else if(ans3==0) {
                SellValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                SellValue.setText("*Enter digits from 0 to 9");
            }
        }
    }

    public void AddMisc(ActionEvent actionEvent) {
        boolean flag = false;
        if (PartNumber.getText().length() == 0 || Quantity.getText().length() == 0 || InventoryDate.getValue() == null || LandingValue.getText().length()==0 || SellValue.getText().length()==0 ) {
            flag = true;
            Stage stage = (Stage) myAnchorPane.getScene().getWindow();

            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("Fill in details");

            alert.getDialogPane().setHeaderText("the * marked fields are compulsory");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (!flag) {

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
            int ans1 = ctr * b;
//        String Partnumber = "fhghjk5657";
        String RefPartNumber = "";
//        String quantity = Quantity.getText();
        String addon = "";
        String Sourceofpurchase = SourceOfPurchase.getText();
        String landingValue= LandingValue.getText();
            int l2 = landingValue.length();
            int ctr2 = 1;
            int a = 1;
            for (int i = 0; i < l2; i++) {
                if (landingValue.charAt(i) >= '0' && landingValue.charAt(i) <= '9') {
                    ctr2++;
                } else {
                    a = 0;
                }
            }
            int ans2 = ctr2 * a;
            String sellvalue= SellValue.getText();
            int l3 = sellvalue.length();
            int ctr3 = 1;
            int c = 1;
            for (int i = 0; i < l3; i++) {
                if (sellvalue.charAt(i) >= '0' && sellvalue.charAt(i) <= '9') {
                    ctr3++;
                } else {
                    c = 0;
                }
            }
            int ans3 = ctr3 * c;
        String PartFor = "";
        String company = "";
        String inventoryDate = InventoryDate.getValue().toString();
        String StockLocation = stockImage;
        String comment = MiscComment.getText();

        String setOf = "";
        String Prefix = "";

//        Random rand= new Random();
//        UPC.setText(myString);

            if((ans1!=0) && (ans2!=0) && (ans3!=0)) {

        try {

            Stage stage = (Stage) myAnchorPane.getScene().getWindow();

            Alert.AlertType type = Alert.AlertType.CONFIRMATION;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("Do you want to confirm?");

            alert.getDialogPane().setHeaderText("You have given the correct information about the products.");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                addData(Partnumber,RefPartNumber,addon,quantity, PartFor, company,inventoryDate,Sourceofpurchase,landingValue,sellvalue, StockLocation, setOf, Prefix, comment);

                Code128Bean code128 = new Code128Bean();
                String myString = PartNumber.getText() ;
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
                String directory = System.getProperty("user.dir");
                FileOutputStream fos = new FileOutputStream(directory+"\\Barcode\\" + image_name);
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
    } else if  (ans1==0) {
                Quantity.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                Quantity.setText("*Enter digits from 0 to 9");
                if ((ans2 == 0) && (ans3 == 0)) {
                    LandingValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    LandingValue.setText("*Enter digits from 0 to 9");
                    SellValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    SellValue.setText("*Enter digits from 0 to 9");
                } else if (ans2 == 0) {
                    LandingValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    LandingValue.setText("*Enter digits from 0 to 9");
                } else if (ans3 == 0) {
                    SellValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    SellValue.setText("*Enter digits from 0 to 9");
                } else{
                    ;
                }
            }
            else if(ans2==0) {
                LandingValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                LandingValue.setText("*Enter digits from 0 to 9");
                if (ans3 == 0) {
                    SellValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    SellValue.setText("*Enter digits from 0 to 9");
                }
                else{;}
            }
            else if(ans3==0) {
                SellValue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                SellValue.setText("*Enter digits from 0 to 9");
            }
        }
    }
    public void confirm(ActionEvent actionEvent) throws IOException {
        System.out.println(SECODE.getText());
        if(SECODE.getText().equals("1995"))
        {
            // update the password in database
            String newpwd = newPassword.getText();
            if(newPassword.getText().equals(confirmPassword.getText()))
            {
                Stage stage = (Stage) myAnchorPane.getScene().getWindow();

                Alert.AlertType type = Alert.AlertType.CONFIRMATION;
                Alert alert = new Alert(type, "");

                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);

                alert.getDialogPane().setContentText("Do you want to confirm?");

                alert.getDialogPane().setHeaderText("You want to set this as new password?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    DatabaseConnection connectNow = new DatabaseConnection();
                    Connection connectDB = connectNow.getConnection();

                    String sql= String.format("UPDATE password SET pwd = '%s';", newPassword.getText());
                    System.out.println(sql);

                    try{
                        Statement statement = connectDB.createStatement();
                        statement.executeUpdate(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
                    stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
            else
            {
                mismatch.setVisible(true);
                newPassword.clear();
                confirmPassword.clear();
            }
        }
        else
        {
            SECODE.setText("WRONG ANSWER!");
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

    public void addData(String PartNumber,String ReferencePartNumber,String AddOn,String Quantity,String PartFor,String Company,String InventoryDate,String SourceOfPurchase,String LandingPurchaseValue,String SellValue,String StockLocation,String setof, String prefix, String Comment){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery1 = "INSERT INTO `inventory_management`.`inward_item` (`part_no`,\n" +
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
                "`comment`) VALUES ('"+PartNumber+"','"+ReferencePartNumber+"','"+AddOn+"','"+Quantity+"','"+PartFor+"','"+Company+"','"+InventoryDate+"','"+SourceOfPurchase+"','"+LandingPurchaseValue+"','"+SellValue+"','"+StockLocation+"','"+setof+"','"+prefix+"','"+Comment+"'"+")";

        String connectQuery2 = "INSERT INTO `deletelog`.`deletemaster` (`part_no`,\n" +
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
                "`transaction_qt`) VALUES ('"+PartNumber+"','"+ReferencePartNumber+"','"+AddOn+"','"+Quantity+"','"+PartFor+"','"+Company+"','"+InventoryDate+"','"+SourceOfPurchase+"','"+LandingPurchaseValue+"','"+SellValue+"','"+StockLocation+"','"+setof+"','"+prefix+"','"+Comment+"','"+Quantity+"'"+")";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(connectQuery1);
            statement.executeUpdate(connectQuery2);
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

    public void goadminSearch(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminSearch.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gochangepassword(ActionEvent actionEvent) throws IOException  {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ChangePassword.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//    public void downloadLog(ActionEvent actionEvent) throws SQLException {
//        JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\RAHUL\\Desktop");
//        excelFileChooser.setDialogTitle("Save As");
//        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "XLS", "XLSX", "XLSM");
//        excelFileChooser.setFileFilter(fnef);
//        int excelChooser = excelFileChooser.showSaveDialog(null);
//
//        if (excelChooser == JFileChooser.APPROVE_OPTION) {
//
//            DatabaseConnection connectNow = new DatabaseConnection();
//            Connection connectDB = connectNow.getConnection();
//
//            try {
//                String connectQuery = "SELECT * FROM `deletelog`.`deletemaster`";
//
//                Statement statement = connectDB.createStatement();
//                ResultSet queryOutput = statement.executeQuery(connectQuery);
//
//                XSSFWorkbook excelJTableExporter = new XSSFWorkbook();
//                XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");
//
//                XSSFRow header=excelSheet.createRow(0);
//                header.createCell(0).setCellValue("PART NUMBER");
//                header.createCell(1).setCellValue("REFERENCE PART NUMBER");
//                header.createCell(2).setCellValue("ADD ON");
//                header.createCell(3).setCellValue("QUANTITY USED");
//                header.createCell(4).setCellValue("PART FOR");
//                header.createCell(5).setCellValue("COMPANY");
//                header.createCell(6).setCellValue("INVENTORY DATE");
//                header.createCell(7).setCellValue("SOURCE OF PURCHASE");
//                header.createCell(8).setCellValue("LANDING PURCHASE VALUE");
//                header.createCell(9).setCellValue("SELLING VALUE");
//                header.createCell(10).setCellValue("STOCK LOCATION");
//                header.createCell(11).setCellValue("SET OF");
//                header.createCell(12).setCellValue("PREFIX");
//                header.createCell(13).setCellValue("COMMENT");
//
//
//                int index = 1;
//                while (queryOutput.next()) {
//                    XSSFRow row = excelSheet.createRow(index);
//                    row.createCell(0).setCellValue(queryOutput.getString("part_no"));
//                    row.createCell(1).setCellValue(queryOutput.getString("ref_part_no"));
//                    row.createCell(2).setCellValue(queryOutput.getString("add_on"));
//                    row.createCell(3).setCellValue(queryOutput.getString("quantity"));
//                    row.createCell(4).setCellValue(queryOutput.getString("part_for"));
//                    row.createCell(5).setCellValue(queryOutput.getString("company"));
//                    row.createCell(6).setCellValue(queryOutput.getString("inventory_date"));
//                    row.createCell(7).setCellValue(queryOutput.getString("source_of_p"));
//                    row.createCell(8).setCellValue(queryOutput.getString("landing_pv"));
//                    row.createCell(9).setCellValue(queryOutput.getString("sell_v"));
//                    row.createCell(10).setCellValue(queryOutput.getString("stock_loc"));
//                    row.createCell(11).setCellValue(queryOutput.getString("setof"));
//                    row.createCell(12).setCellValue(queryOutput.getString("prefix"));
//                    row.createCell(13).setCellValue(queryOutput.getString("comment"));
//
//                    index++;
//                }
//                FileOutputStream fileOut = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
//                BufferedOutputStream bFIleOut=new BufferedOutputStream(fileOut);
//                excelJTableExporter.write(bFIleOut);
//                bFIleOut.close();
//                fileOut.close();
//                statement.close();
//                queryOutput.close();
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
