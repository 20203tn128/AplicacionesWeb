package mx.edu.utez.marketplace.category.model;

import javax.persistence.*;

@Entity  // indica al url que esto es una tabla
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "description",unique = true) // name para el nombre de la columna unique para decir que es unico
    private String description;

    public Category() {
    }

    public Category(String description) {
        this.description = description;
    }

    public Category(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
