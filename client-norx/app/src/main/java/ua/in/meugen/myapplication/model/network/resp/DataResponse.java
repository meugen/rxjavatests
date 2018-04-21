package ua.in.meugen.myapplication.model.network.resp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {

    @SerializedName("data")
    public List<String> data;
}
