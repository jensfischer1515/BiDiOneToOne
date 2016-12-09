package com.epages.experiment.availability;

import com.epages.experiment.product.Product;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static javax.persistence.AccessType.FIELD;
import static lombok.AccessLevel.PRIVATE;

@Access(FIELD)
@Entity
@Table(name = "AVAILABILITIES")
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Availability implements Persistable<UUID> {

    @Id
    private UUID id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    Product owner;

    @Basic
    @Column(name = "STATE", nullable = false)
    @Setter
    String state;

    @Version
    @Column(name = "OPT_LOCK", nullable = false)
    private Long optLock;

    @Basic
    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, insertable = true, updatable = false, columnDefinition = "DATETIME(3) NOT NULL")
    private LocalDateTime createdAt;

    @Basic
    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_AT", nullable = false, insertable = true, updatable = true, columnDefinition = "DATETIME(3) NOT NULL")
    private LocalDateTime lastModifiedAt;

    @Override
    @Transient
    public boolean isNew() {
        return id == null;
    }

    public static AvailabilityBuilder builder(Product owner) {
        return new AvailabilityBuilder(owner);
    }

    public static class AvailabilityBuilder {
        private String state = "ON_STOCK";

        AvailabilityBuilder(Product owner) {
            owner(owner);
        }
    }
}
