package cmpe202.validation;

import static org.junit.Assert.*;

import org.junit.Test;


public class DiscoverCCValidatorTest extends DiscoverCCValidator {

	@Test
	public void testValidate() {
		DiscoverCCValidator discover = new DiscoverCCValidator();
		assertSame(discover.validate("6011000000000000"), "DiscoverCC");
	}
	
	@Test
	public void testValidateCard() {
		DiscoverCCValidator discover = new DiscoverCCValidator();
		assertFalse(discover.validateCard("invalid"));
	}

}
