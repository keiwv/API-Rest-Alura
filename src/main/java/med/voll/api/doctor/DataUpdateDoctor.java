package med.voll.api.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.Address;

public record DataUpdateDoctor(@NotNull Long id, String name, String email, String document,Address address) {

}
