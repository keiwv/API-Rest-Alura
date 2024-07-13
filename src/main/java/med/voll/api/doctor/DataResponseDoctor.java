package med.voll.api.doctor;

import med.voll.api.address.DataAddress;

public record DataResponseDoctor(Long id, String name, String email, String phonenumber, String document, DataAddress address) {
}
