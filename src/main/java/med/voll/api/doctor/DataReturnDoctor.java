package med.voll.api.doctor;

public record DataReturnDoctor(String name, String department, String document, String email) {

        public DataReturnDoctor(Doctor doctor) {
            this(doctor.getName(), doctor.getDepartment().toString(), doctor.getDocument(), doctor.getEmail());
        }

}
