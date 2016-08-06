package timertask;

import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class CrontriggerTask {
	public static void main(String[] args) throws SchedulerException {
		String startDateStr = "2016-08-06 00:00:00.0";
		String endDateStr = "2016-08-06 16:00:00.0";

		Date startDate = null;
		Date endDate = null;
		;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(startDateStr);
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(endDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//0 0/2 a trigger that will fire every other minute, between 9am and 4pm, every day:
		CronTrigger cronTrigger = newTrigger().withIdentity("trigger1", "testJob").startAt(startDate)
				.withSchedule(
						CronScheduleBuilder.cronSchedule("0 0/2 9-16 * * ?").withMisfireHandlingInstructionDoNothing())
				.endAt(endDate).build();

		JobDetail job = JobBuilder.newJob(CronJob.class).withIdentity("cronJob", "testJob").build();
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.scheduleJob(job, cronTrigger);
		scheduler.start();
	}
}
