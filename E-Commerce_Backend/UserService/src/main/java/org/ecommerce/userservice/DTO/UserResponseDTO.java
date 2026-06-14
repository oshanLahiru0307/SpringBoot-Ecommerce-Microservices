package org.ecommerce.userservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Integer id;
    private String name;
    private String email;
    private String role;
    private String phone;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
