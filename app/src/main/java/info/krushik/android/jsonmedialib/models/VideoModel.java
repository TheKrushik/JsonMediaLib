package info.krushik.android.jsonmedialib.models;

public class VideoModel {

    private String title; // название
    private String picture; // полная ссылка на обложку, с http
    private String desc; // описание, с тегами
    private String length; // 01:01:01
    private String video; //полная ссылка с http

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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
