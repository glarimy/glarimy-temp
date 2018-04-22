package currency;

import java.io.FileReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class CurrencyTrackingJob implements Runnable {
	private String uri = "https://rates.fxcm.com/RatesXML";
	private String symbol;
	private double target;
	private double current;

	@Override
	public void run() {
		try {
			getUserSettings();
			getCurrent();
			
			//TODO: apply whatever ever logic you want and send alert
			if (current > target)
				alert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getUserSettings() throws Exception {
		// 2. Start with hard coded user configuration. For example. Pair:
		// EURUSD, Target rate: 1.381
		// 3. Make it user entry once you have working code.

		Properties props = new Properties();
		props.load(new FileReader("user.properties"));
		symbol = props.getProperty("symbol");
		target = Double.parseDouble(props.getProperty("target"));
	}

	private void getCurrent() throws Exception {
		// 1. You will need a parser to parse the XML. There are several
		// examples online.
		try {
			HttpURLConnection connection = (HttpURLConnection) (new URL(uri)).openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
			InputStream in = (InputStream) connection.getInputStream();
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(in);
			XPath xPath = XPathFactory.newInstance().newXPath();
			// 2. Start with hard coded user configuration. For example. Pair:
			// EURUSD, Target rate: 1.381
			// 3. Make it user entry once you have working code.
			
			//TODO: change the xpath according to your requirement
			String bid = xPath.compile("/Rates/Rate[@Symbol='" + symbol + "']/Bid").evaluate(document);
			current = Double.parseDouble(bid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void alert() {
		NotificationService.onAlert(new Date(), symbol, target, current);
	}

}