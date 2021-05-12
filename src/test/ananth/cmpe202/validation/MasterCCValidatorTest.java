package cmpe202.validation;

import static org.junit.Assert.*;

import org.junit.Test;

public class MasterCCValidatorTest {

	@Test
	public void testValidate() {
		MasterCCValidator master = new MasterCCValidator();
		assertSame(master.validate("5410000000000000"), "MasterCC");
	}
	
	@Test
	public void testValidateCard() {
		MasterCCValidator master = new MasterCCValidator();
		assertTrue(master.validateCard("5410000000000000"));
	}

}
