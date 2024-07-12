package med.voll.api.doctor;

import med.voll.api.address.DataAddress;

public record DataRegisterDoctor(String name, String email, String document, Departament departament, DataAddress address) {


}
