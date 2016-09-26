
package info.krushik.android.jsonmedialib.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class VideoList implements Parcelable {

    @SerializedName("list")
    private List<Video> list = new ArrayList<>();


    public List<Video> getList() {
        return list;
    }

    public void setList(List<Video> list) {
        this.list = list;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.list);
    }

    public VideoList() {
    }

    protected VideoList(Parcel in) {
        this.list = new ArrayList<Video>();
        in.readList(this.list, Video.class.getClassLoader());
    }

    public static final Creator<VideoList> CREATOR = new Creator<VideoList>() {
        @Override
        public VideoList createFromParcel(Parcel source) {
            return new VideoList(source);
        }

        @Override
        public VideoList[] newArray(int size) {
            return new VideoList[size];
        }
    };
}
