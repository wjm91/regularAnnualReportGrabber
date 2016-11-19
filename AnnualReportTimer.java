package regularAnnualReportGrabber;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AnnualReportTimer {
	

	
	private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public static void main(String[] args) { 
	
	scheduler.scheduleAtFixedRate(new ImportAnnualReport(), Long.valueOf(0), Long.valueOf(1), TimeUnit.DAYS);
	
	System.out.println("Running regular AR Grabber");
	
}


}
