package com.psy888.thymeleaftest.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "public.client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Clients's name is required")
    private String name;

    @NotBlank(message = "Client's phone is required")
    private String phone;

    private String email;
}
