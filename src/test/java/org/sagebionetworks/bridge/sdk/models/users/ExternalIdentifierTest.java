package org.sagebionetworks.bridge.sdk.models.users;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class ExternalIdentifierTest {
    
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(ExternalIdentifier.class).allFieldsShouldBeUsed().verify();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void cannotConstructNullIdentifier() {
        new ExternalIdentifier(null, false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void cannotConstructEmptyIdentifier() {
        new ExternalIdentifier("", false);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void cannotConstructBlankIdentifier() {
        new ExternalIdentifier("   ", false);
    }
    
}
