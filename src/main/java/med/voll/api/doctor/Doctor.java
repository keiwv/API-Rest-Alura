package med.voll.api.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String document;
    private String phonenumber;
    @Enumerated(EnumType.STRING)
    private Departament department;
    @Embedded
    private Address address;


    public Doctor(DataRegisterDoctor json) {
        this.name = json.name();
        this.email = json.email();
        this.department = json.departament();
        this.document = json.document();
        this.address = new Address(json.address());
        this.phonenumber = json.phonenumber();
    }

}
