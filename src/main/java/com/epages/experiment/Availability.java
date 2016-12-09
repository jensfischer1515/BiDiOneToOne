package com.epages.experiment;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@ToString(of = "owner")
@Entity
@Table(name = "AVAILABILITIES")
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Availability implements Serializable {

    @Id
    UUID productId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    Product owner;

    @Basic
    String state;

    public Availability(Product owner) {
        this.owner = owner;
    }
}
