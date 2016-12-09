package com.epages.experiment.product;

import com.epages.experiment.AbstractEntity;
import com.epages.experiment.availability.Availability;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static javax.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Product extends AbstractEntity {

    @Basic(optional = false)
    @Column(unique = true, nullable = false)
    String sku;

    @OneToOne(cascade = ALL, mappedBy = "owner")
    Availability availability = Availability.builder(this).build();
}
