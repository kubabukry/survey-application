package com.example.survey.controller;

import com.example.survey.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
}
