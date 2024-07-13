package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

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
    private Boolean isactive;
    private String name;
    private String email;
    private String document;
    private String phonenumber;
    @Enumerated(EnumType.STRING)
    private Departament department;
    @Embedded
    private Address address;


    public Doctor(DataRegisterDoctor json) {
        this.isactive = true;
        this.name = json.name();
        this.email = json.email();
        this.department = json.departament();
        this.document = json.document();
        this.address = new Address(json.address());
        this.phonenumber = json.phonenumber();
    }

    public void updateData(DataUpdateDoctor doctorUpdate)
    {
        if (doctorUpdate.name() != null)
        {
            this.name = doctorUpdate.name();
        }
        if (doctorUpdate.email() != null)
        {
            this.email = doctorUpdate.email();
        }

        if (doctorUpdate.document() != null)
        {
            this.document = doctorUpdate.document();
        }

        if (doctorUpdate.address() != null)
        {
            this.address = doctorUpdate.address();
        }

    }

    public void desactiveDoctor() {
        this.isactive = false;
    }
}
