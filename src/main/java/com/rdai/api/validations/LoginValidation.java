package com.rdai.api.validations;


import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
public class LoginValidation {

    @NotBlank
    @NotNull
    private String nombreUsuario;

    @NotBlank
    @NotNull
    private String password;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }
}
