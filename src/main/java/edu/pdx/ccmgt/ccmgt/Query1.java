package edu.pdx.ccmgt.ccmgt;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.ResultSet;


public class Query1 extends Query{
	ResultSet result1;
	ResultSet result2;
	ResultSet result3;
	ResultSet result4;
	Long dqflagFour; 
	Long dqflagFive; 
	Long dqflagSix; 
	Long dqflagSeven; 
	
	public void execute(){
		//To To: Write your query here and print out the result
		//System.out.println("Query 1 To Do Here");
		System.out.println("Detector Id's When Speed > 100:");
		
        //Executing the query
        result1 = session.execute("select count(*) as count from QOEdit1 where dqflags = 4");
        result2 = session.execute("select count(*) as count from QOEdit1 where dqflags = 5");
        result3 = session.execute("select count(*) as count from QOEdit1 where dqflags = 6");
        result4 = session.execute("select count(*) as count from QOEdit1 where dqflags = 7");
        
        for(Row row: result1){
        	System.out.println("When dqflags=4");
        	System.out.format("%d\n", row.getLong("count"));
        	dqflagFour = row.getLong("count");
        	
        }
        for(Row row:result2){
        	System.out.println("when dqflags=5");
        	System.out.format("%d\n", row.getLong("count"));
        	 dqflagFive = row.getLong("count");
        	
        }
        for(Row row:result3){
        	System.out.println("when dqflags=6");
        	System.out.format("%d\n", row.getLong("count"));
        	 dqflagSix = row.getLong("count");
        	
        }
        for(Row row:result4){
        	System.out.println("when dqflags=7");
        	System.out.format("%d\n", row.getLong("count"));
        	 dqflagSeven = row.getLong("count");
        
        }
        
        Long finalResult = dqflagFour + dqflagFive + dqflagSix + dqflagSeven;
        System.out.print("Total:");
        System.out.print(finalResult);
        
	}
}