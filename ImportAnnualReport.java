package regularAnnualReportGrabber;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ImportAnnualReport implements Runnable {
	
	public void run() {
		
		String csvFile = "/Users/Desktop/epicCodes.csv";
        String line = "";
        String cvsSplitBy = ",";
        
        Calendar now = Calendar.getInstance();   // Gets the current date and time
        int year = now.get(Calendar.YEAR);      // The current year as an int
        
        ArrayList<String> ticker = new ArrayList<String>();
        
		
        try {
        	
        	BufferedReader br = new BufferedReader(new FileReader(csvFile));
        

            while ((line = br.readLine()) != null) {
            	
            		String[] entry = line.split(cvsSplitBy);
            		ticker.add(entry[0]);
            	}
            	
            	System.out.println(ticker);
            	

                // use comma as separator
                //String[] entry = line.split(cvsSplitBy);
            	
            	for(int t=0; t<ticker.size(); t++){

                String fileName = "/Users/Desktop/newReports/" + ticker.get(t) + "_" + year + ".pdf";
    			
    			URL link = null;
    			try {
    				 link = new URL("http://www.annualreports.co.uk/HostedData/AnnualReports/PDF/LSE_" + ticker.get(t) + "_2015.pdf");
    				 // http://www.annualreports.co.uk/HostedData/AnnualReports/PDF/LSE_BT_2015.pdf
    				 
    				 //HttpURLConnection conn = (HttpURLConnection) link.openConnection();
    				 //conn.addRequestProperty("User-Agent", "Mozilla/4.76");
    				 //int size = conn.getContentLength();
    				 
    				 //System.out.println(size);
    				 
    				 InputStream in = new BufferedInputStream(link.openStream());
    				 ByteArrayOutputStream out = new ByteArrayOutputStream();
    				 
    				 byte[] buf = new byte[1024];
    				 int n = 0;
    				 while (-1!=(n=in.read(buf)))
    				 {
    				    out.write(buf, 0, n);
    				 }
    				 out.close();
    				 in.close();
    				 byte[] response = out.toByteArray();
    				 
    				 FileOutputStream fos = new FileOutputStream(fileName);
    				 fos.write(response);
    				 fos.close();
    				 
    				 ticker.remove(t);
    				 
    			} catch (Exception e) {
    				System.out.println("Couldn't retrieve : " + ticker.get(t) + " " + year);
    			}
            	}
    			
            
            
            br.close();
            System.out.println(ticker);
            
        } catch (Exception e) {
        	System.out.println("Cannot read : " + csvFile);
        }
        
        
        
	}
	
}


		
	
	


