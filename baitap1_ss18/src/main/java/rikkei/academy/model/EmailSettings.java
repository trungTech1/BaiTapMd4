package rikkei.academy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailSettings {
    private String language;
    private int pageSize;
    private boolean spamFilter;
    private String signature;
}
