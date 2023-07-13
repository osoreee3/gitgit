package com.example.ckb.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password1;

    @NotEmpty
    private String password2;

    @NotEmpty
    private String email;
}
