package com.example.rodrigo_gangone.testclima.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private List<com.example.rodrigo_gangone.testclima.Model.List> list = null;

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<com.example.rodrigo_gangone.testclima.Model.List> getList() {
        return list;
    }

    public void setList(List<com.example.rodrigo_gangone.testclima.Model.List> list) {
        this.list = list;
    }
}

