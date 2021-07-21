package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class modelTableDelete {
    @FXML
    private SimpleIntegerProperty Pid;
    @FXML
    private SimpleIntegerProperty Uid;
    @FXML
    private SimpleStringProperty Pmfd;
    @FXML
    private SimpleStringProperty Ptype;
    @FXML
    private SimpleStringProperty Pcompany;
    @FXML
    private SimpleIntegerProperty Pid2;
    @FXML
    private SimpleStringProperty Plastdate;

    public modelTableDelete(int unique_code, int product_code, String date_of_manufacture, String type, String company, int count) {
        this.Uid = new SimpleIntegerProperty(unique_code);
        this.Pid = new SimpleIntegerProperty(product_code);
        this.Pmfd= new SimpleStringProperty(date_of_manufacture);
        this.Ptype = new SimpleStringProperty(type);
        this.Pcompany = new SimpleStringProperty(company);
        this.Pid2 = new SimpleIntegerProperty(count);
    }


    public int getPid() {
        return Pid.get();
    }

    public void setPid(int pid) {
        Pid = new SimpleIntegerProperty(pid);
    }

    public int getUid() {
        return Uid.get();
    }
    public void setUid(int pid) {
        Uid = new SimpleIntegerProperty(pid);
    }


    public String getPmfd() {
        return Pmfd.get();
    }

    public void setPmfd(String pmfd) {
        Pmfd = new SimpleStringProperty(pmfd);
    }

    public Integer getPid2() {
        return Pid2.get();
    }

    public void setPid2(Integer pid2) {
        Pid2 = new SimpleIntegerProperty(pid2);
    }

    public String getPtype() {
        return Ptype.get();
    }

    public void setPtype(String ptype) {
        Ptype = new SimpleStringProperty(ptype);
    }

    public String getPcompany() {
        return Pcompany.get();
    }

    public void setPcompany(String pcompany) {
        Pcompany = new SimpleStringProperty(pcompany);
    }

    public String getPlastdate() {
        return Plastdate.get();
    }

    public void setPlastdate(String plastdate) {
        Plastdate =new SimpleStringProperty(plastdate);
    }
}