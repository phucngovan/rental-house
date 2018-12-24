package com.PKHS.airbnb.model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rental_house")
public class RentalHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rental_house_id")
    private List<Order> orders;

    @OneToMany(targetEntity = Image.class)
    private List<Image> images;

    private String title;
    private String description;
    private boolean status;
    private String post_date;
    private int price;

    private String quantity_bedroom;
    private String quantity_toilet;
    private String quantity_storey;
    private String province;
    private String address;
    private String featured;
    private int house_area;

    public RentalHouse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getQuantity_bedroom() {
        return quantity_bedroom;
    }

    public void setQuantity_bedroom(String quantity_bedroom) {
        this.quantity_bedroom = quantity_bedroom;
    }

    public String getQuantity_toilet() {
        return quantity_toilet;
    }

    public void setQuantity_toilet(String quantity_toilet) {
        this.quantity_toilet = quantity_toilet;
    }

    public String getQuantity_storey() {
        return quantity_storey;
    }

    public void setQuantity_storey(String quantity_storey) {
        this.quantity_storey = quantity_storey;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public int getHouse_area() {
        return house_area;
    }

    public void setHouse_area(int house_area) {
        this.house_area = house_area;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
