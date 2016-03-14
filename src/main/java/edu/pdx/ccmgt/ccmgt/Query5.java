package edu.pdx.ccmgt.ccmgt;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

public class Query5 extends Query{
	public void execute(){
		//To To: Write your query here and print out the result
		System.out.println("Starting Update");
        result = session.execute("update station_data set length=2.3 where stationid=1140");
        ResultSet result2  = session.execute("select * from station_data where stationid=1140");
        for(Row row: result2){
        	System.out.format("%d %f\n", row.getInt("stationid"), row.getFloat("length"));
        }
	}
};
