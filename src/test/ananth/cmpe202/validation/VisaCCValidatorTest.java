package cmpe202.validation;

import static org.junit.Assert.*;

import org.junit.Test;

public class VisaCCValidatorTest {

	@Test
	public void testValidate() {
		VisaCCValidator visa = new VisaCCValidator();
		assertSame(visa.validate("4120000000000"), "VisaCC");
	}
	
	@Test
	public void testValidateCard() {
		VisaCCValidator visa = new VisaCCValidator();
		assertTrue(visa.validateCard("4120000000000"));
	}

}
