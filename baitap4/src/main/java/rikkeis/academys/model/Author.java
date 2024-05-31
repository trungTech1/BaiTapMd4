package rikkeis.academys.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter @NoArgsConstructor

public class Author {
    private Integer id;
    private String name;
    private boolean status;
}
