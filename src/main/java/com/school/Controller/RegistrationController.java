package com.school.Controller;

import com.school.Services.RegistrationServices;
import com.school.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/registrations")
public class RegistrationController {
@Autowired
    private RegistrationServices registrationService;

    @PostMapping("/addRegistration")
    public RegistrationDTO addRegistration(@RequestBody RegistrationDTO registrationDTO) {
        return registrationService.insertRegistration(registrationDTO);
    }

    @PutMapping("/updateRegistration/{id}")
    public RegistrationDTO updateRegistration(@PathVariable Long id, @RequestBody RegistrationDTO registrationDTO) {
        return registrationService.updateRegistration(id, registrationDTO);
    }

    @DeleteMapping("/deleteRegistration/{id}")
    public void deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
    }

    @GetMapping("/findRegistrationById/{id}")
    public RegistrationDTO findRegistrationById(@PathVariable Long id) {
        return registrationService.findRegistrationById(id);
    }

    @GetMapping("/findAllRegistration")
    public List<RegistrationDTO> findAllRegistrations() {
        return registrationService.findAllRegistration();
    }



}
