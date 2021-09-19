package com.combis.kontakti.service;

import com.combis.kontakti.config.BusinessException;
import com.combis.kontakti.model.Kontakt;
import com.combis.kontakti.repository.KontaktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class KontaktService {
    @Value("${mobile.validation.regex}")
    private String mobitelRegex;

    @Value("${email.validation.regex}")
    private String emailRegex;

    private KontaktRepository kontaktRepo;

    @Autowired
    public KontaktService(KontaktRepository kontaktRepo) {
        this.kontaktRepo = kontaktRepo;
    }

    public List<Kontakt> findAll() {
        return kontaktRepo.findAll();
    }

    public Kontakt findById(UUID id) throws BusinessException {
        Optional<Kontakt> kontaktOpt = kontaktRepo.findById(id);
        if (! kontaktOpt.isPresent()) {
            throw new BusinessException("Provjerite ID!");
        }

        return kontaktOpt.get();
    }

    public Kontakt insert(Kontakt entity) throws BusinessException {
        validirajBrojMobitela(entity.getMobitel());
        validirajEmail(entity.getEmail());

        return kontaktRepo.save(entity);
    }

    public Kontakt update(UUID id, Kontakt kontakt) throws BusinessException {
        validirajBrojMobitela(kontakt.getMobitel());
        validirajEmail(kontakt.getEmail());

        Kontakt dbEntity = findById(id);
        dbEntity.setIme(kontakt.getIme());
        dbEntity.setPrezime(kontakt.getPrezime());
        dbEntity.setEmail(kontakt.getEmail());
        dbEntity.setMobitel(kontakt.getMobitel());
        dbEntity.setAdresa(kontakt.getAdresa());

        return kontaktRepo.save(dbEntity);
    }

    public void delete(UUID id) throws BusinessException {
        Kontakt entity = findById(id);

        kontaktRepo.delete(entity);
    }

    private void validirajBrojMobitela(String mobitel) throws BusinessException {
        if (mobitel == null) {
            throw new BusinessException("Broj mobitela je obvezno polje!");
        }
        Pattern pattern = Pattern.compile(mobitelRegex);

        Matcher matcher = pattern.matcher(mobitel);
        if (! matcher.matches()) {
            throw new BusinessException("Provjerite broj mobitela!");
        }
    }

    private void validirajEmail(String email) throws BusinessException {
        if (email == null) {
            throw new BusinessException("Email je obvezno polje!");
        }
        Pattern pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);
        if (! matcher.matches()) {
            throw new BusinessException("Provjerite email!");
        }
    }
}
