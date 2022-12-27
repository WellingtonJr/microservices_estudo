package com.ead.authuser.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    public interface UserView {
        public static interface RegistrationPost {
        }

        public static interface UserPut {
        }

        public static interface PasswordPut {
        }

        public static interface ImagePut {
        }
    }

    // não aceita valores NULOS e nem VAZIOS
    @NotBlank(groups = UserView.RegistrationPost.class)
    @Size(min = 4, max = 50) // define min e max de caracteres
    @JsonView(UserView.RegistrationPost.class)
    private String username;

    @NotBlank(groups = UserView.RegistrationPost.class)
    @Email(groups = UserView.RegistrationPost.class) // verifica se email esta dentro de um padrao de email
    @Size(min = 4, max = 50, groups = UserView.RegistrationPost.class)
    @JsonView(UserView.RegistrationPost.class)
    private String email;

    @NotBlank(groups = { UserView.RegistrationPost.class, UserView.PasswordPut.class })
    @Size(min = 6, max = 20, groups = { UserView.RegistrationPost.class, UserView.PasswordPut.class })
    @JsonView({ UserView.RegistrationPost.class, UserView.PasswordPut.class })
    private String password;

    @NotBlank(groups = UserView.PasswordPut.class)
    @Size(min = 6, max = 20, groups = UserView.PasswordPut.class)
    @JsonView(UserView.PasswordPut.class)
    private String oldPassword;

    @JsonView({ UserView.RegistrationPost.class, UserView.UserPut.class })
    private String fullName;

    @JsonView({ UserView.RegistrationPost.class, UserView.UserPut.class })
    private String phoneNumber;

    @JsonView({ UserView.RegistrationPost.class, UserView.UserPut.class })
    private String cpf;

    @NotBlank(groups = UserView.ImagePut.class)
    @JsonView(UserView.ImagePut.class)
    private String imageUrl;
}
