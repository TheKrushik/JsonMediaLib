package info.krushik.android.jsonmedialib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Book {

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

    public static class BookFiles {

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


    }
}
