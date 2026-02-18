package br.com.gabrielwederson.financial.model;


import jakarta.persistence.*;

@Entity
public class LaunchData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 120, nullable = false)
    private String name;

    @Column(nullable = false)
    private double value;

    @Enumerated(EnumType.STRING)
    private Type type;

    public LaunchData() {

    }

    public LaunchData(String name, double value, Type type) {
        this.name = name;
        this.value = value;
        this.type = type;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
