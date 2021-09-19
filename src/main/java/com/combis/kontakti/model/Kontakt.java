package com.combis.kontakti.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "kontakti")
public class Kontakt {
    @Id
    @NotNull
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Type(type = "pg-uuid")
    private UUID id;

    @Basic(optional = false)
    @NotNull(message = "{model.NotNull.message}")
    @Size(min = 1, max = 30)
    private String ime;

    @Basic(optional = false)
    @NotNull(message = "{model.NotNull.message}")
    @Size(min = 1, max = 50)
    private String prezime;

    @Basic(optional = false)
    @NotNull(message = "{model.NotNull.message}")
    private String email;

    @Basic(optional = false)
    @NotNull(message = "{model.NotNull.message}")
    private String mobitel;

    @Basic(optional = false)
    @NotNull(message = "{model.NotNull.message}")
    @Size(min = 1, max = 100)
    private String adresa;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobitel() {
        return mobitel;
    }

    public void setMobitel(String mobitel) {
        this.mobitel = mobitel;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kontakt kontakt = (Kontakt) o;
        return Objects.equals(id, kontakt.id) &&
                Objects.equals(prezime, kontakt.prezime)
                && Objects.equals(email, kontakt.email)
                && Objects.equals(mobitel, kontakt.mobitel)
                && Objects.equals(adresa, kontakt.adresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prezime, email, mobitel, adresa);
    }
}
