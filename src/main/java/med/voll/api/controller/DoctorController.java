package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.*;
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
        return doctorRepository.findByisactiveTrue(pageable).map(DataReturnDoctor::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid DataUpdateDoctor doctorUpdate)
    {
        Doctor doctor = doctorRepository.getReferenceById(doctorUpdate.id());
        doctor.updateData(doctorUpdate);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id)
    {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.desactiveDoctor();
    }
}


