package org.mw.mwws;


import org.junit.jupiter.api.Test;
import org.mw.mwws.security.token.SecretKeys;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class KeyGeneration {

	@Test
	void generate() {
		
		var key1 = SecretKeys.getKey();
		var value1 = SecretKeys.keyToString(key1);
		
		System.out.println(value1);
		
		var key2 = SecretKeys.stringToKey(value1);
		var value2 = SecretKeys.keyToString(key2);
		
		System.out.println(value2);
		
		assertEquals(value1, value2);
		var test1 = SecretKeys.stringToKey("CJxD7RA0InBYKIAyyq0LdgyqUFS4vXNkntiGvnjNe2Nkpzfe1PGdyISMDfMmxtxFc5yE7zBOjLBA");
		var test2 = SecretKeys.keyToString(test1);
		System.out.println("test1 "+test1);
		System.out.println("test2 "+test2);
	}
}
