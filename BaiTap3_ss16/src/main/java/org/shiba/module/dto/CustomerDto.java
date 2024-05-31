package org.shiba.module.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.shiba.module.model.Customer;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto {
    String message;
    Customer data;
}
