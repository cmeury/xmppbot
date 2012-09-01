package de.raion.xmppbot.command;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.Parameter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

import de.raion.xmppbot.context.XmppContext;

@CLICommand(name="dilbert", description="posts dilbert strips (default: strip of the day)")
public class DilbertCommand extends AbstractXmppCommand {

	private static final String BASE_URL = "http://www.dilbert.com";

	private static final String RANDOM_BASE_URL = "http://www.dilbert.com/strips/comic/";
	
	private static Logger log = LoggerFactory.getLogger(DilbertCommand.class);

	
	@Parameter(names = { "-r", "--random" }, description = "posts a random strip")
    private boolean random = false;
	
	
	
	
	@Override
	protected CommandResult innerExecute(XmppContext context) {
		
		String postUrl = null; 
		
		if(random)
			postUrl = getImageUrl(createRandomUrl());
		else
			postUrl = getImageUrl(BASE_URL);
		
		if(postUrl != null)
			println(postUrl);
		
		return CommandResult.OK;
	}


	private String getImageUrl(String url) {
		
		try {
				
			Client client = new Client();
			ClientResponse response = client.resource(url).get(ClientResponse.class);
			
			if(response.getClientResponseStatus() == Status.OK) {
					
				Document doc = Jsoup.parse(response.getEntityInputStream(), "UTF-8", BASE_URL);
					
				Element imageDiv = doc.select("div.STR_Image").first();
				Element imageSrc = imageDiv.select("img[src]").first();
				
				return imageSrc.absUrl("src");
			}
			
			println("couldn't fetch random url, sry!");
			return null;
			
			
					
		} catch (IOException e) {
			log.error("getRandomUrl()", e);
			println("couldn't fetch random url, sry!");
			return null;
			
		}
	}


	private void postNewestUrl() {
		// TODO Auto-generated method stub
		
	}


	private String createRandomUrl() {

		 DateTime minDate = new DateTime(2006, 1, 1, 0, 0 ,0);
		 DateTime maxDate = new DateTime(System.currentTimeMillis());
		 
		 int randomYear  = getRandomInRange(minDate.getYear(), maxDate.getYear());
		 int randomMonth = getRandomInRange(1, 12);
		 int randomDay   = getRandomInRange(1,getDaysInMonth(randomYear, randomMonth));
		 
		 DateTime randomDate = new DateTime(randomYear, randomMonth, randomDay, 0, 0, 0);
		 DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		 
		 
		 StringBuilder builder = new StringBuilder(RANDOM_BASE_URL);
		 builder.append(formatter.print(randomDate));
		 return builder.toString();
		
	}

	private  int getDaysInMonth(int year, int month)
    {
      
        Chronology chrono = ISOChronology.getInstance();
        DateTimeField dayField = chrono.dayOfMonth();        
        LocalDate monthDate = new LocalDate(year, month, 1);
        return dayField.getMaximumValue(monthDate);
    }
	
	
	private int getRandomInRange(int min, int max) {
		Random random = new Random(System.currentTimeMillis());
		return random.nextInt(max - min + 1) + min;
	}

}
