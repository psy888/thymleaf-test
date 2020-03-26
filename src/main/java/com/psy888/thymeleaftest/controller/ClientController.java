package com.psy888.thymeleaftest.controller;

import com.psy888.thymeleaftest.model.ClientEntity;
import com.psy888.thymeleaftest.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository repository;

    @GetMapping("/")
    public String showList(@RequestParam(defaultValue = "DEFAULT") SortBy sortBy, Model model) {
        model.addAttribute("clients", getSortedList(sortBy));
        return "index";
    }

    @GetMapping("/new")
    public String showSignUpForm(Model model) {
        model.addAttribute("client", new ClientEntity());
        return "add-client";
    }

    @PostMapping("/add")
    public String addClient(@Valid ClientEntity client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-client";
        }

        repository.save(client);
        model.addAttribute("clients", repository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        ClientEntity clientEntity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id: " + id));
        model.addAttribute("client", clientEntity);
        return "update-client";
    }

    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable long id, @Valid ClientEntity client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            client.setId(id);
            return "update-client";
        }

        repository.save(client);
        model.addAttribute("clients", repository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable long id, Model model) {
        ClientEntity clientEntity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id: " + id));
        repository.delete(clientEntity);
        model.addAttribute("clients", repository.findAll());
        return "index";
    }

    @GetMapping("/error")
    public String showErrorPage() {
        return "error";
    }

    private Iterable<ClientEntity> getSortedList(SortBy sortBy) {
        switch (sortBy) {
            case NAME:
                return repository.findAllAndOrderByName();
            case EMAIL:
                return repository.findAllAndOrderByEmail();
            case PHONE:
                return repository.findAllAndOrderByPhone();
            default:
                return repository.findAll();
        }
    }

}
