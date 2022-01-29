package com.rdai.api.validations;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class JwtValidation {

    @NotNull
    @NotBlank
    private String token;


    public String getToken() {
        return token;
    }
}
