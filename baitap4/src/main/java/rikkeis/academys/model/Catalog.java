package rikkeis.academys.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class Catalog {
    private Integer id;
    private String name;
    private boolean status;

}
