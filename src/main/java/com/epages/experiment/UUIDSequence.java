package com.epages.experiment;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sequencing.Sequence;
import org.eclipse.persistence.sessions.Session;

import java.util.UUID;
import java.util.Vector;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UUIDSequence extends Sequence implements SessionCustomizer {

    public static final String UUID_SEQUENCE = "uuid-sequence";

    private static final long serialVersionUID = -8111815193483132234L;

    private static final UUIDPersistenceConverter CONVERTER = new UUIDPersistenceConverter();

    public UUIDSequence(String name) {
        super(name);
    }

    @Override
    public void customize(Session session) throws Exception {
        UUIDSequence sequence = new UUIDSequence(UUID_SEQUENCE);
        session.getLogin().addSequence(sequence);
    }

    @Override
    public Object getGeneratedValue(Accessor accessor, AbstractSession writeSession, String seqName) {
        return CONVERTER.convertToDatabaseColumn(UUID.randomUUID());
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Vector getGeneratedVector(Accessor accessor, AbstractSession writeSession, String seqName, int size) {
        return null; // NOSONAR - this method is unused anyway.
    }

    @Override
    public void onConnect() { // nothing to do
    }

    @Override
    public void onDisconnect() { // nothing to do
    }

    @Override
    public boolean shouldAcquireValueAfterInsert() {
        return false;
    }

    @Override
    public boolean shouldUseTransaction() {
        return false;
    }

    @Override
    public boolean shouldUsePreallocation() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}