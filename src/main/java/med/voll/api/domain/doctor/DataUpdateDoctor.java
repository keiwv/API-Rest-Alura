package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.Address;

public record DataUpdateDoctor(@NotNull Long id, String name, String email, String document, Address address) {

}
