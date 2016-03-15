package edu.pdx.ccmgt.ccmgt;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

public class Query2 extends Query{
	ResultSet result1;
	ResultSet result2;
	@Override
	public void execute(){
		//super.connect("localhost",9042,"freeway");
		System.out.println("Query 2 To Do Here");
		result1=session.execute("select detectorid from detector_data where locationtext='Foster NB'");
		
		int volume=0;
		for (Row row : result1) {
			String query2="Select sum(volume) as sum_volume from loop where detectorid=";
			query2+=row.getInt("detectorid");
			query2+=" and starttime>='2011-09-15 00:00:00+0000' and starttime<'2011-09-16 00:00:00+0000'";
			result2=session.execute(query2);
			for(Row row1:result2){
				volume+=row1.getInt("sum_volume");
			}
		}
		System.out.format("%d\n",volume);

	}
};