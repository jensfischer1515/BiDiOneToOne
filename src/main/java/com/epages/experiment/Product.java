package com.epages.experiment;

import org.springframework.data.domain.Persistable;

import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static com.epages.experiment.UUIDSequence.UUID_SEQUENCE;
import static javax.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PRIVATE;

@ToString(of = "id")
@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Persistable<UUID> {

    @Id
    @GeneratedValue(generator = UUID_SEQUENCE)
    @Column(name = "ID", columnDefinition = "BINARY(16) NOT NULL", unique = true)
    UUID id;

    @Basic(optional = false)
    @Column(unique = true, nullable = false)
    String sku;

    @OneToOne(cascade = ALL, mappedBy = "owner")
    Availability availability;

    @Override
    public boolean isNew() {
        return id == null;
    }

    public static Product newInstance() {
        Product product = new Product();
        product.availability = new Availability(product);
        return product;
    }
}
