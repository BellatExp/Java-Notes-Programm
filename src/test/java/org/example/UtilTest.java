package org.example;

import junit.framework.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void userAuthentication() {

        User user = new User("1","2");

        assertTrue(Utility.userAuthentication(user,"1","2"));
        assertFalse(Utility.userAuthentication(user,"2","3"));
    }

    @Test
    //@DisplayName("Enterg tags")
    void checkTagsString() {

        assertTrue(Utility.checkTagsString("#Vi"));

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