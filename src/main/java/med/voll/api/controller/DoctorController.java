package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.DataRegisterDoctor;
import med.voll.api.doctor.DataReturnDoctor;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping
    public Page<DataReturnDoctor> getDoctors(@PageableDefault(size = 2) Pageable pageable) {
        return doctorRepository.findAll(pageable).map(DataReturnDoctor::new);
    }
}


