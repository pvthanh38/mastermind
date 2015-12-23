package com.mastermind.thanh.mastermind.models;

import java.util.List;

/**
 * Created by Thanh on 12/22/2015.
 */
public class ListGroup {
    public static ListGroup instance;
    public int id;
    public String name;
    public String code;
    public String status;
    public String full_name;

    public List<ListGroup> getListGroup() {
        return listGroups;
    }

    public void setListGroup(List<ListGroup> listGroups) {
        this.listGroups = listGroups;
    }
    public List<ListGroup> listGroups;
    public static ListGroup getInstance() {
        if (instance == null) {
            instance = new ListGroup();
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    @Override
    public String toString() {
        return name;
    }
}
