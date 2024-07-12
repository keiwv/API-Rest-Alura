package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.DataRegisterDoctor;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired //No recomendable usarla
    private DoctorRepository doctorRepository;

    @PostMapping
    public void RegisterDoctor(@RequestBody @Valid DataRegisterDoctor json)
    {
        doctorRepository.save(new Doctor(json));
    }

}


