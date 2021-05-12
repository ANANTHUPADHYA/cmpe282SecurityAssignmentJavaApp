package cmpe202.factory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CreditCardFactoryAbstractClassTest {
	
	@Test
	public void testGetCreditCardType() {
		try {
			CreditCardFactoryAbstractClass ccf = new CreditCardFactoryAbstractClass();
			CreditCardFactory ccfObj = ccf.getCreditCardType("VisaCC");
			assertNotNull(ccfObj);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testGetCreditCardTypeInvalid() {
		try {
			CreditCardFactoryAbstractClass ccf = new CreditCardFactoryAbstractClass();
			CreditCardFactory ccfObj = ccf.getCreditCardType("Invalid");
			assertNull(ccfObj);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
