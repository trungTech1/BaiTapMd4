package com.example.demo.model;

public class Book {

    Integer id;
    Integer category_id;
    String name;
    Double price;
    Integer stock;
    Integer totalPages;
    Integer yearCreated;
    String author;
    Boolean status;
    String category_name;

    public Book() {
    }
    public Book(Integer id, Integer category_id, String name, Double price, Integer stock, Integer totalPages, Integer yearCreated, String author, Boolean status) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.totalPages = totalPages;
        this.yearCreated = yearCreated;
        this.author = author;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(Integer yearCreated) {
        this.yearCreated = yearCreated;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", totalPages=" + totalPages +
                ", yearCreated=" + yearCreated +
                ", author='" + author + '\'' +
                ", status=" + status +
                '}';
    }
}
