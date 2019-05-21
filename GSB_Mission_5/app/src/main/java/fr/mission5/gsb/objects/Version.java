package fr.mission5.gsb.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Version {

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("data")
    @Expose
    private String data;

    @SerializedName("message")
    @Expose
    private String message;

    public boolean isError() { return error; }

    public String getData() { return data; }

    public String getMessage(){ return message; }

}
