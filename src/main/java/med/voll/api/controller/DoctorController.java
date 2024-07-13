package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.address.DataAddress;
import med.voll.api.domain.doctor.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired //No recomendable usarla
    private DoctorRepository doctorRepository;

    @PostMapping
    public ResponseEntity<DataResponseDoctor> RegisterDoctor(@RequestBody @Valid DataRegisterDoctor json, UriComponentsBuilder uriBuilder)
    {
        Doctor doctor = doctorRepository.save(new Doctor(json));
        DataResponseDoctor dataReturnDoctor =  new DataResponseDoctor(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhonenumber(),
                doctor.getDepartment().toString(), new DataAddress(doctor.getAddress().getStreet(),
                doctor.getAddress().getDistrict(), doctor.getAddress().getCity(), doctor.getAddress().getNumber(),
                doctor.getAddress().getComplement()));
        URI url = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(url).body(dataReturnDoctor);
    }

    @GetMapping
    public ResponseEntity<Page<DataReturnDoctor>> getDoctors(@PageableDefault(size = 2) Pageable pageable) {
        return ResponseEntity.ok(doctorRepository.findByisactiveTrue(pageable).map(DataReturnDoctor::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DataUpdateDoctor doctorUpdate)
    {
        Doctor doctor = doctorRepository.getReferenceById(doctorUpdate.id());
        doctor.updateData(doctorUpdate);
        return ResponseEntity.ok(new DataResponseDoctor(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhonenumber(),
                doctor.getDepartment().toString(), new DataAddress(doctor.getAddress().getStreet(),
                doctor.getAddress().getDistrict(), doctor.getAddress().getCity(), doctor.getAddress().getNumber(),
                doctor.getAddress().getComplement())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id)
    {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.desactiveDoctor();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseDoctor> returnDataDoctor(@PathVariable Long id)
    {
        Doctor doctor = doctorRepository.getReferenceById(id);
        var dataDoctor = new DataResponseDoctor(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhonenumber(),
                doctor.getDepartment().toString(), new DataAddress(doctor.getAddress().getStreet(),
                doctor.getAddress().getDistrict(), doctor.getAddress().getCity(), doctor.getAddress().getNumber(),
                doctor.getAddress().getComplement()));
        return ResponseEntity.ok(dataDoctor);
    }
}


