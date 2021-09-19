package com.combis.kontakti.controller;

import com.combis.kontakti.config.BusinessException;
import com.combis.kontakti.model.Kontakt;
import com.combis.kontakti.service.KontaktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("kontakti")
public class KontaktController {
    private KontaktService kontaktService;

    @Autowired
    public KontaktController(KontaktService kontaktService) {
        this.kontaktService = kontaktService;
    }

    @GetMapping
    public List<Kontakt> findAll() {
        return kontaktService.findAll();
    }

    @GetMapping("{id}")
    public Kontakt findById(@PathVariable UUID id) throws BusinessException {
        return kontaktService.findById(id);
    }

    @PostMapping
    public Kontakt insert(@RequestBody Kontakt entity) throws BusinessException {
        return kontaktService.insert(entity);
    }

    @PutMapping("{id}")
    public Kontakt update(@PathVariable UUID id, @RequestBody Kontakt entity) throws BusinessException {
        return kontaktService.update(id, entity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable UUID id) throws BusinessException {
        kontaktService.delete(id);

        return ResponseEntity.ok().build();
    }
}
