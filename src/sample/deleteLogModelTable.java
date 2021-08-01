package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class deleteLogModelTable {
    @FXML
    private SimpleStringProperty P_partNumber;
    @FXML
    private SimpleStringProperty P_refPartNumber;
    @FXML
    private SimpleStringProperty P_emp_id;
    @FXML
    private SimpleStringProperty P_addOn;
    @FXML
    private SimpleIntegerProperty P_quantity;
    @FXML
    private SimpleIntegerProperty P_quantityUsed;
    @FXML
    private SimpleStringProperty P_date_time;
    @FXML
    private SimpleStringProperty P_partFor;
    @FXML
    private SimpleStringProperty P_company;
    @FXML
    private SimpleStringProperty P_invDate;
    @FXML
    private SimpleStringProperty P_sourceOfPurchase;
    @FXML
    private SimpleIntegerProperty P_landingPurchaseValue;
    @FXML
    private SimpleIntegerProperty P_sellingValue;
    @FXML
    private SimpleStringProperty P_stockLocation;
    @FXML
    private SimpleStringProperty P_setOf;
    @FXML
    private SimpleStringProperty P_prefix;
    @FXML
    private SimpleStringProperty P_comment;

    public String getP_partNumber() { return P_partNumber.get(); }
    public SimpleStringProperty p_partNumberProperty() {
        return P_partNumber;
    }
    public void setP_partNumber(String p_partNumber) {
        P_partNumber = new SimpleStringProperty(p_partNumber);
    }

    public String getP_refPartNumber() { return P_refPartNumber.get(); }
    public SimpleStringProperty p_refPartNumber() {
        return P_refPartNumber;
    }
    public void setP_refPartNumber(String p_refPartNumber) { P_refPartNumber = new SimpleStringProperty(p_refPartNumber); }

    public String getP_emp_id() { return P_emp_id.get(); }
    public SimpleStringProperty p_emp_id() {
        return P_emp_id;
    }
    public void setP_emp_id(String p_emp_id) { P_emp_id = new SimpleStringProperty(p_emp_id); }

    public String getP_addOn() { return P_addOn.get(); }
    public SimpleStringProperty p_addOn() {
        return P_addOn;
    }
    public void setP_addOn(String p_addOn) {
        P_addOn = new SimpleStringProperty(p_addOn);
    }

    public Integer getP_quantityUsed() { return P_quantityUsed.get(); }
    public SimpleIntegerProperty p_quantityUsed() {
        return P_quantityUsed;
    }
    public void setP_quantityUsed(Integer p_quantityUsed) { P_quantityUsed = new SimpleIntegerProperty(p_quantityUsed); }

    public Integer getP_quantity() { return P_quantity.get(); }
    public SimpleIntegerProperty p_quantity() { return P_quantity; }
    public void setP_quantity(Integer p_quantity) { P_quantity = new SimpleIntegerProperty(p_quantity); }

    public String getP_partFor() { return P_partFor.get(); }
    public SimpleStringProperty p_partFor() {
        return P_partFor;
    }
    public void setP_partFor(String p_partFor) {
        P_partFor = new SimpleStringProperty(p_partFor);
    }

    public String getP_company() { return P_company.get(); }
    public SimpleStringProperty p_company() {
        return P_company;
    }
    public void setP_company(String p_company) {
        P_company = new SimpleStringProperty(p_company);
    }

    public String getP_invDate() { return P_invDate.get(); }
    public SimpleStringProperty p_invDate() {
        return P_invDate;
    }
    public void setP_invDate(String p_invDate) { P_invDate = new SimpleStringProperty(p_invDate); }

    public String getP_date_time() { return P_date_time.get(); }
    public SimpleStringProperty p_date_time() { return P_date_time; }
    public void setP_date_time(String p_date_time) { P_date_time = new SimpleStringProperty(p_date_time); }

    public String getP_sourceOfPurchase() { return P_sourceOfPurchase.get(); }
    public SimpleStringProperty p_sourceOfPurchase() {
        return P_sourceOfPurchase;
    }
    public void setP_sourceOfPurchase(String p_sourceOfPurchase) { P_sourceOfPurchase = new SimpleStringProperty(p_sourceOfPurchase); }

    public Integer getP_landingPurchaseValue() { return P_landingPurchaseValue.get(); }
    public SimpleIntegerProperty p_landingPurchaseValue() {
        return P_landingPurchaseValue;
    }
    public void setP_landingPurchaseValue(Integer p_landingPurchaseValue) { P_landingPurchaseValue = new SimpleIntegerProperty(p_landingPurchaseValue); }

    public Integer getP_sellingValue() { return P_sellingValue.get(); }
    public SimpleIntegerProperty p_sellingValue() {
        return P_sellingValue;
    }
    public void setP_sellingValue(Integer p_sellingValue) { P_sellingValue = new SimpleIntegerProperty(p_sellingValue); }

    public String getP_stockLocation() { return P_stockLocation.get(); }
    public SimpleStringProperty p_stockLocation() {
        return P_stockLocation;
    }
    public void setP_stockLocation(String p_stockLocation) { P_stockLocation = new SimpleStringProperty(p_stockLocation); }

    public String getP_setOf() { return P_setOf.get(); }
    public SimpleStringProperty p_setOf() {
        return P_setOf;
    }
    public void setP_setOf(String p_setOf) { P_setOf = new SimpleStringProperty(p_setOf); }

    public String getP_prefix() { return P_prefix.get(); }
    public SimpleStringProperty p_prefix() {
        return P_prefix;
    }
    public void setP_prefix(String p_prefix) { P_prefix = new SimpleStringProperty(p_prefix); }

    public String getP_comment() { return P_comment.get(); }
    public SimpleStringProperty p_comment() {
        return P_comment;
    }
    public void setP_comment(String p_comment) { P_comment = new SimpleStringProperty(p_comment); }


    public deleteLogModelTable(String p_partNumber, String p_refPartNumber,String p_emp_id, String p_addOn, int p_quantity, int p_quantityUsed, String p_partFor, String p_company, String p_invDate, String p_date_time,
                           String p_sourceOfPurchase, int p_landingPurchaseValue, int p_sellingValue, String p_stockLocation, String p_setOf, String p_prefix, String p_comment) {
        this.P_partNumber = new SimpleStringProperty(p_partNumber);
        this.P_refPartNumber = new SimpleStringProperty(p_refPartNumber);
        this.P_emp_id = new SimpleStringProperty(p_emp_id);
        this.P_addOn = new SimpleStringProperty(p_addOn);
        this.P_quantity = new SimpleIntegerProperty(p_quantity);
        this.P_quantityUsed = new SimpleIntegerProperty(p_quantityUsed);
        this.P_partFor = new SimpleStringProperty(p_partFor);
        this.P_company = new SimpleStringProperty(p_company);
        this.P_invDate = new SimpleStringProperty(p_invDate);
        this.P_date_time = new SimpleStringProperty(p_date_time);
        this.P_sourceOfPurchase = new SimpleStringProperty(p_sourceOfPurchase);
        this.P_landingPurchaseValue = new SimpleIntegerProperty(p_landingPurchaseValue);
        this.P_sellingValue = new SimpleIntegerProperty(p_sellingValue);
        this.P_stockLocation = new SimpleStringProperty(p_stockLocation);
        this.P_setOf = new SimpleStringProperty(p_setOf);
        this.P_prefix = new SimpleStringProperty(p_prefix);
        this.P_comment = new SimpleStringProperty(p_comment);
    }
}

