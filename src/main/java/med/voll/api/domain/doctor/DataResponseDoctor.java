package med.voll.api.domain.doctor;

import med.voll.api.domain.address.DataAddress;

public record DataResponseDoctor(Long id, String name, String email, String phonenumber, String document, DataAddress address) {
}
