package cmpe202;

import java.io.FileNotFoundException;
import java.util.List;

import cmpe202.parsers.CSVFileParser;
import cmpe202.parsers.JSONFileParser;
import cmpe202.parsers.Parser;
import cmpe202.parsers.XMLFileParser;

public class MainClass {

	public static void main(String[] args) {
		String inputFile = args[0];
		String outputfile = args[1];
		Parser p = new Parser();
		if(inputFile.endsWith(".xml")) {
			p.changeParser(new XMLFileParser());
		}
		else if(inputFile.endsWith(".json")) {
			p.changeParser(new JSONFileParser());
		}
		else if(inputFile.endsWith(".csv")) {
			p.changeParser(new CSVFileParser());
		}
		List<CreditCard> c = p.parseFile(inputFile);
		try {
			p.writeToFile(c, outputfile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
