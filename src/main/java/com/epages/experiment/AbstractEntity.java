package com.epages.experiment;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static com.epages.experiment.UUIDSequence.UUID_SEQUENCE;
import static javax.persistence.AccessType.FIELD;
import static lombok.AccessLevel.PRIVATE;

@Access(FIELD)
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@ToString(of = "id")
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public abstract class AbstractEntity implements Persistable<UUID> {

    @Id
    @GeneratedValue(generator = UUID_SEQUENCE)
    @Column(name = "ID", columnDefinition = "BINARY(16) NOT NULL", unique = true)
    UUID id;

    @Version
    @Column(name = "OPT_LOCK", nullable = false)
    @Getter
    Long optLock;

    @Basic
    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, insertable = true, updatable = false, columnDefinition = "DATETIME(3) NOT NULL")
    @Getter
    LocalDateTime createdAt;

    @Basic
    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_AT", nullable = false, insertable = true, updatable = true, columnDefinition = "DATETIME(3) NOT NULL")
    @Getter
    LocalDateTime lastModifiedAt;

    protected AbstractEntity(UUID uuid) {
        this.id = uuid;
    }

    @Override
    @Transient
    public boolean isNew() {
        return id == null;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
