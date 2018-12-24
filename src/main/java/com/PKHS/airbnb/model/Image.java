package com.PKHS.airbnb.model;

import javax.persistence.*;

@Entity
@Table(name = "image_house")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String link;
    private String alt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_house_id")
    private RentalHouse post;

    public Image() {
    }

    public Image(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public Image(String name, String link, RentalHouse post) {
        this.name = name;
        this.link = link;
        this.post = post;
    }

    public Image(String name, String link, String alt, RentalHouse post) {
        this.name = name;
        this.link = link;
        this.alt = alt;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public RentalHouse getPost() {
        return post;
    }

    public void setPost(RentalHouse post) {
        this.post = post;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
