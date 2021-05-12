package cmpe202.parsers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cmpe202.CreditCard;

public class CSVFileParserTest {

	@Test
	public void testParseFile() {
		CSVFileParser xmlParser = new CSVFileParser();
		try {
			String s = xmlParser.parseFile("Sample.csv").get(0).getCardType();
			System.out.print(s);
			assertEquals(s,"Master");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void testWriteToFile() {
		CSVFileParser xmlParser = new CSVFileParser();
		try {
			List<CreditCard> list = new ArrayList<CreditCard>();
			CreditCard card = new CreditCard();
			card.setCardNumber("5410000000000000");
			card.setCardHolderName("Alice");
			card.setExpiryDate("3/20/30");
			list.add(card);
			String s = xmlParser.writeToFile(list, "SampleOutput.csv");
			assertEquals(s,"Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
