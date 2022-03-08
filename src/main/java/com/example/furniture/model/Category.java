package com.example.furniture.model;
import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "id_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique=true)
    private String name;
    @Transient
    public String msj="";
    public Category(){}
    public Category(String name){
        this.name= name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
}