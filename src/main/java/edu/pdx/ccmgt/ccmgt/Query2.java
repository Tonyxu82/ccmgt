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
		StringBuilder tempquery=new StringBuilder();
		tempquery.append("Select sum(volume) as sum_volume from loopdata where detectorid IN (");

		int i=0;
		for (Row row : result1) {
			if(i>0){
				tempquery.append(",");
				i+=1;
			}
			
			tempquery.append(row.getInt("detectorid"));
			System.out.println(row.getInt("detectorid"));
			i+=1;
		}
		tempquery.append(") and starttime>='2011-09-15 00:00:00+0000' and starttime<'2011-09-16 00:00:00+0000'");
		String query2 = tempquery.toString();
		System.out.println(query2);
		result2=session.execute(query2);
		for(Row row:result2){
			System.out.format("%d\n", row.getInt("sum_volume"));
		}
	}
};