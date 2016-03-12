package edu.pdx.ccmgt.ccmgt;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

public class Query3 extends Query{
	
	ResultSet result1;
	ResultSet result2;
	ResultSet result3;
	float average1, average2,totalspeed1,totalspeed2,length,avgspeed1,avgspeed2;
	long speedcount1,speedcount2;
	public void execute(){
		//To To: Write your query here and print out the result
		//System.out.println("Query 3 To Do Here");
		result1 = session.execute("Select detectorid from detector_data where locationtext='Foster NB'");
		result2 = session.execute("Select length from station_data where locationtext='Foster NB'");
		for(Row row: result2) {
			length=row.getFloat("length");
		}
		
		// For average from 7 to 9 am
		
		StringBuilder tempquery1=new StringBuilder();
		tempquery1.append("Select sum(speed) as totalspeed,count(speed) as scount1 from loop where detectorid IN (");
		int i=0;
		for (Row row : result1) {
			if(i>0){
				tempquery1.append(",");
				i+=1;
			}
			tempquery1.append("'");
			tempquery1.append(row.getInt("detectorid"));
			tempquery1.append("'");
			//System.out.println(row.getInt("detectorid"));
			i+=1;
		}
		tempquery1.append(") and starttime>='2011-09-15 07:00:00+0000' and starttime<'2011-09-15 09:00:00+0000'");
		String query2 = tempquery1.toString();
		//System.out.println(query2);
		result2=session.execute(query2);
		for(Row row:result2){
			//System.out.format("%d\n", row.getInt("speed"));
			totalspeed1 = row.getInt("totalspeed");
			speedcount1 = row.getLong("scount1");
		}
		avgspeed1 = totalspeed1/speedcount1;
		average1=(length/avgspeed1)*3600;
		System.out.println("Average travel time for 7am to 9am is "+average1+" seconds");
		
		//For average from 4 to 6 pm
		result1 = session.execute("Select detectorid from detector_data where locationtext='Foster NB'");
		StringBuilder tempquery2=new StringBuilder();
		tempquery2.append("Select sum(speed) as totalspeed,count(speed) as scount2 from loop where detectorid IN (");
		int j=0;
		for (Row row : result1) {
			if(j>0){
				tempquery2.append(",");
				j+=1;
			}
			//System.out.println(row.getInt("detectorid"));
			tempquery2.append("'");
			tempquery2.append(row.getInt("detectorid"));
			tempquery2.append("'");
			//System.out.println(row.getInt("detectorid"));
			j+=1;
		}
		tempquery2.append(") and starttime>='2011-09-15 16:00:00+0000' and starttime<'2011-09-15 18:00:00+0000'");
		String query3 = tempquery2.toString();
		//System.out.println(query3);
		result3=session.execute(query3);
		for(Row row:result3) {
			totalspeed2=row.getInt("totalspeed");
			speedcount2=row.getLong("scount2");
		}
		//System.out.println("Totalspeed2 "+totalspeed2+" speedcount2 "+speedcount2);
		avgspeed2=totalspeed2/speedcount2;
		average2=(length/avgspeed2)*3600;
		System.out.println("Average travel time for 4pm to 6pm is "+average2+" seconds");
	}
};
