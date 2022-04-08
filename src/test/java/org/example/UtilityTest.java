package org.example;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UtilityTest extends TestCase {

    @Test
    @DisplayName("Enter HashTags ")
    public void testCheckTagsString() {

        assertTrue(Utility.checkTagsString("#work"));
        assertTrue(Utility.checkTagsString(""));
        assertTrue(Utility.checkTagsString("#123213213#23_21_3"));

        assertTrue(Utility.checkTagsString("#Work"));

        assertFalse(Utility.checkTagsString("#31321     "));
        assertFalse(Utility.checkTagsString("       #31321     "));
        assertFalse(Utility.checkTagsString("#"));
        assertFalse(Utility.checkTagsString("-"));
        assertFalse(Utility.checkTagsString("#?--4234324_423"));

    }
}