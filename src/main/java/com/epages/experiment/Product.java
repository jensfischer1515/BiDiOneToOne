package com.epages.experiment;

import org.springframework.data.domain.Persistable;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static com.epages.experiment.UUIDSequence.UUID_SEQUENCE;

@Access(AccessType.FIELD)
@Entity
@Table(name = "PRODUCTS")
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product implements Persistable<UUID> {

    @Id
    @GeneratedValue(generator = UUID_SEQUENCE)
    @Column(name = "ID", columnDefinition = "BINARY(16) NOT NULL", unique = true)
    UUID id;

    @Basic(optional = false)
    @Column(unique = true, nullable = false)
    String sku;

    @OneToOne(cascade = CascadeType.ALL)
    Availability availability;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
