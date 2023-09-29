package com.temat27zad1.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String email;
    private Boolean newsletter;

    public String userDtoInfo() {
        return "Id: " + id + ", " + firstName + " " + lastName + ", data urodzenia: " + birthDate
               + ", adres email: " + email() + ", zapisany do biuletynu: " + newsLetter();
    }

    private String email() {
        if (email != null) {
            return email;
        }
        return "Brak adresu email";
    }

    private String newsLetter() {
        if (newsletter != null) {
            return (newsletter) ? "TAK" : "NIE";
        }
        return "Brak wyboru";
    }
}
