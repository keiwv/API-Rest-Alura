package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.DataAddress;

public record DataRegisterDoctor(@NotBlank
                                 String name,
                                 @NotBlank
                                 @Email
                                 String email,
                                 @NotBlank
                                 @Pattern(regexp = "\\d{4,6}")
                                 String document,
                                 @NotNull
                                 Departament departament,
                                 @NotNull
                                 @Valid
                                 DataAddress address, @NotBlank String phonenumber) {


}
