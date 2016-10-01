package info.krushik.android.jsonmedialib.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Video implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.picture);
        dest.writeString(this.desc);
        dest.writeString(this.length);
        dest.writeString(this.dt);
        dest.writeString(this.video);
    }

    public Video() {
    }

    protected Video(Parcel in) {
        this.title = in.readString();
        this.picture = in.readString();
        this.desc = in.readString();
        this.length = in.readString();
        this.dt = in.readString();
        this.video = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
