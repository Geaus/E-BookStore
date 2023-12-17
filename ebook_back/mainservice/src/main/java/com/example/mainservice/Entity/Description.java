package com.example.mainservice.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "description")
public class Description {
    @Id
    private String _id;

    private String des;

    public Description(String _id , String des) {
        this._id = _id;
        this.des = des;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
