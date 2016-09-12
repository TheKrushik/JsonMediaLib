package info.krushik.android.jsonmedialib.models;

import java.util.List;

public class BookModel {

    private String title; // название
    private String аuthor; // автор
    private String anons; // текст анонса книги
    private String picture; // ссылка на картинку обложки
    private List<Files> filesList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getАuthor() {
        return аuthor;
    }

    public void setАuthor(String аuthor) {
        this.аuthor = аuthor;
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

    public List<Files> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<Files> filesList) {
        this.filesList = filesList;
    }

    public static class Files {
        private String type; // pdf/epub
        private String url; // http://...

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
