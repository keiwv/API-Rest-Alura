package med.voll.api.doctor;

import med.voll.api.address.DataAddress;

public record DataReturnDoctor(Long id, String name, String department, String document, String email) {

        public DataReturnDoctor(Doctor doctor) {
            this(doctor.getId(),doctor.getName(), doctor.getDepartment().toString(), doctor.getDocument(), doctor.getEmail());
        }
}
