package org.academiadecodigo.bootcamp.model;


import java.util.Date;

public class ImagePost {

    private Date postDate = new Date();
    private String urlImage;

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
