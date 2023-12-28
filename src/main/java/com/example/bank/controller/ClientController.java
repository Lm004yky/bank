package com.example.bank.controller;
import com.example.bank.dto.ClientDto;
import com.example.bank.entity.Client;
import com.example.bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/by-gender")
    public List<ClientDto> getClientsGender(@RequestParam String gender) {
        return clientService.findByGender(gender);
    }

    @GetMapping("/by-branch-id/{id}")
    public List<ClientDto> getClientsBranchId(@PathVariable Long id) {
        return clientService.findByBranchId(id);
    }

    @GetMapping("/by-client-name-surname")
    public List<ClientDto> getClientsByNameAndSurname(@RequestParam String clientName, @RequestParam String clientSurname) {
        return clientService.findClientNameSurname(clientName, clientSurname);
    }

    @GetMapping("/by-client-birth-date")
    public List<ClientDto> getClientsByBirthDate(@RequestParam String birthDate) {
        return clientService.findByBirthDate(birthDate);
    }

    @PostMapping("/create")
    public ClientDto createClient(@RequestBody ClientDto clientDto) {
        return clientService.create(clientDto);
    }

    @PutMapping("/update/{id}")
    public ClientDto updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        return clientService.update(id, clientDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}
