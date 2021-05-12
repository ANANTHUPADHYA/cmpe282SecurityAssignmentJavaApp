package cmpe202.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cmpe202.CreditCard;
import cmpe202.factory.CreditCardFactoryAbstractClass;
import cmpe202.validation.AmexCCValidator;
import cmpe202.validation.DiscoverCCValidator;
import cmpe202.validation.MasterCCValidator;
import cmpe202.validation.ValidationHandler;
import cmpe202.validation.VisaCCValidator;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVFileParser implements FileParser{

	@Override
	public List<CreditCard> parseFile(String fileName) {
		List<CreditCard> listCC = new ArrayList<CreditCard>();
		try { 
			  
	        FileReader filereader = new FileReader(fileName); 
	        CSVReader csvReader = new CSVReader(filereader); 
	        String[] nextRecord;
	        csvReader.readNext();
	        
	        while ((nextRecord = csvReader.readNext()) != null) { 
	        	CreditCard cd = new CreditCard();
	        	cd.setCardNumber(nextRecord[0]);
	            cd.setExpiryDate(nextRecord[1]);
	            cd.setCardHolderName(nextRecord[2]);
	        	try {
		        	String cardNumber = String.format("%.0f",Double.valueOf(nextRecord[0]));
		        	
		            cd.setCardNumber(cardNumber);
		            
		            ValidationHandler visaHandler = new VisaCCValidator();
					ValidationHandler masterHandler = new MasterCCValidator();
					ValidationHandler amexHandler = new AmexCCValidator();
					ValidationHandler discoverHandler = new DiscoverCCValidator();	
					
					visaHandler.nextHandler(masterHandler);
					masterHandler.nextHandler(amexHandler);
					amexHandler.nextHandler(discoverHandler);
					
					String cc = visaHandler.validate(cardNumber);
					if(cc==null) {
						cd.setCardType("Invalid Card");
						cd.setError("Error");
					}else {
						CreditCardFactoryAbstractClass cdf = new CreditCardFactoryAbstractClass();
						String name = cdf.getCreditCardType(cc).getClass().getSimpleName();
						cd.setCardType(name);
						cd.setError("No Error");
					}
				}catch(NumberFormatException e){
					cd.setCardType("Invalid Card");
					cd.setError("Error");
				}
				listCC.add(cd);
	        }
	        csvReader.close();
	    } 
	    catch (Exception e) { 
	        e.printStackTrace(); 
	    }
		return listCC;
	}
	
	public String writeToFile(List<CreditCard> list, String outputFile) throws FileNotFoundException {
		File file = new File(outputFile); 
	    try {  
	        FileWriter outputfile = new FileWriter(file); 
	        
	        CSVWriter writer = new CSVWriter(outputfile); 

	        String[] header = { "CardNumber", "ExpirationDate", "NameOfCardHolder", "CardType", "Error" };
	        writer.writeNext(header); 
	        
	        for(CreditCard cd : list) {
	        	String[] row = new String[5];
	        	row[0] = cd.getCardNumber();
	        	row[1] = cd.getExpiryDate();
    			row[2] = cd.getCardHolderName();
    			row[3] = cd.getCardType();
    			row[4] = cd.getError();
    			writer.writeNext(row); 
	        }
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        e.printStackTrace(); 
	        return "Failure";
	    } 
	    return "Success";
	}

}
