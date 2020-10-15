package ca.bcit.project1_final;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BaseNews {
    @SerializedName("article")
    @Expose
    private ArrayList<News> newss = new ArrayList<>();

    public ArrayList<News> getNewss() {
        return newss;
    }

    public void setNewss(ArrayList<News> newss) {
        this.newss = newss;
    }
}

