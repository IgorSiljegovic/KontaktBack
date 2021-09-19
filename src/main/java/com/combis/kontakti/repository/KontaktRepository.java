package com.combis.kontakti.repository;

import com.combis.kontakti.model.Kontakt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KontaktRepository extends JpaRepository<Kontakt, UUID> {
}
