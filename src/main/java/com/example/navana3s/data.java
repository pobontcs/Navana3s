package com.example.navana3s;

public class data {
    private String Name;
    private String Status;
    private String Assigned;
    data(String Name, String Status) {
        this.Name = Name;
        this.Status = Status;
    }
    data(String Name, String Status, String Assigned) {
        this.Name = Name;
        this.Status = Status;
        this.Assigned = Assigned;
    }
    public String getAssigned() {
        return Assigned;
    }
    public void setAssigned(String Assigned) {
        this.Assigned = Assigned;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String Status) {
        this.Status = Status;
    }
}
