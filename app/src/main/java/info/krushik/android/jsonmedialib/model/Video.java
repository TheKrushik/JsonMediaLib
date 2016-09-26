package info.krushik.android.jsonmedialib.model;


import com.google.gson.annotations.SerializedName;


public class Video {

    @SerializedName("title")
    private String title;

    @SerializedName("picture")
    private String picture;

    @SerializedName("desc")
    private String desc;

    @SerializedName("length")
    private String length;

    @SerializedName("dt")
    private String dt;

    @SerializedName("video")
    private String video;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

}
