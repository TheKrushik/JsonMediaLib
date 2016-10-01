package info.krushik.android.jsonmedialib.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Book implements Parcelable {

    @SerializedName("title")
    private String title;

    @SerializedName("author")
    private String author;

    @SerializedName("anons")
    private String anons;

    @SerializedName("picture")
    private String picture;

    @SerializedName("files")
    private List<BookFiles> files;

    public String getFileByType(String type) {
        for (Book.BookFiles bookFiles : files) {
            if (type.equals(bookFiles.getType())) {
                return bookFiles.getUrl();
            }
        }
        return "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<BookFiles> getFiles() {
        return files;
    }

    public void setFiles(List<BookFiles> files) {
        this.files = files;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.author);
        dest.writeString(this.anons);
        dest.writeString(this.picture);
        dest.writeList(this.files);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.title = in.readString();
        this.author = in.readString();
        this.anons = in.readString();
        this.picture = in.readString();
        this.files = new ArrayList<BookFiles>();
        in.readList(this.files, BookFiles.class.getClassLoader());
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public static class BookFiles implements Parcelable {

        @SerializedName("type")
        private String type;

        @SerializedName("url")
        private String url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.type);
            dest.writeString(this.url);
        }

        public BookFiles() {
        }

        protected BookFiles(Parcel in) {
            this.type = in.readString();
            this.url = in.readString();
        }

        public static final Creator<BookFiles> CREATOR = new Creator<BookFiles>() {
            @Override
            public BookFiles createFromParcel(Parcel source) {
                return new BookFiles(source);
            }

            @Override
            public BookFiles[] newArray(int size) {
                return new BookFiles[size];
            }
        };
    }
}
