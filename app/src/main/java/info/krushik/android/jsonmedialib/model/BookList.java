package info.krushik.android.jsonmedialib.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class BookList implements Parcelable {

    @SerializedName("list")
    private List<Book> list = new ArrayList<>();


    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
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

    public BookList() {
    }

    protected BookList(Parcel in) {
        this.list = new ArrayList<Book>();
        in.readList(this.list, Book.class.getClassLoader());
    }

    public static final Creator<BookList> CREATOR = new Creator<BookList>() {
        @Override
        public BookList createFromParcel(Parcel source) {
            return new BookList(source);
        }

        @Override
        public BookList[] newArray(int size) {
            return new BookList[size];
        }
    };
}
