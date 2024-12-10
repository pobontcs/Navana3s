package com.example.navana3s;

public class data {
    private String Name;
    private String Status;
    data(String Name, String Status) {
        this.Name = Name;
        this.Status = Status;
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
