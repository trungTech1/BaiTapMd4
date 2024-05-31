package rikkeis.academys.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor@Setter@Getter@AllArgsConstructor
public class Book {
    private Integer id;
    private String name;
    private Integer catalog;
    private Integer author;
    private boolean status;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", catalog=" + catalog +
                ", author=" + author +
                ", status=" + status +
                '}';
    }
}
