package edu.pdx.ccmgt.ccmgt;

import java.util.*;

import com.datastax.driver.core.Row;


public class Query4 extends Query{
	class Station{
		public String locationtext;
		public String stationid;
		public String downstream;
		public String upstream;
		
		public Station(String loc, String statid, String down, String up){
			this.locationtext = loc;
			this.stationid = statid;
			this.downstream = down;
			this.upstream = up;
		}
	}
	
	public void execute(){
		//To To: Write your query here and print out the result
		String begin="", end="";
		Map<String, Station> m = new HashMap<String, Station>();
		Queue<Station> up = new LinkedList<Station>();
		Queue<Station> down = new LinkedList<Station>();
	    result = session.execute("select locationtext, stationid, downstream, upstream from station_data");
	    for(Row row: result){
	        Station s = new Station(row.getString("locationtext"), 
	        		row.getString("stationid"), 
	        		row.getString("downstream"),
	        		row.getString("upstream"));
	        m.put(row.getString("stationid"), s);
	        
	        if(row.getString("locationtext").equals("Johnson Cr NB")){
	        	begin = row.getString("stationid");
	        }
	        
	        if(row.getString("locationtext").equals("Columbia to I-205 NB")){
	        	end = row.getString("stationid");
	        }   
	    }
	    
	    if(begin.equals("") || end.equals("")){
	    	System.out.println("Cannot find begin or end station");
	    	return;
	    }
	    
	    
	    //Check Downstream
	    Station cur = m.get(begin);
	    down.add(m.get(begin));
	    while(true){
	    	cur = m.get(cur.downstream);
	    	if(cur == null){
	    		break;
	    	}else{
	    		down.add(cur);
	    		if(cur.stationid.equals(end)){
	    			System.out.println("Found Path in Downstream:");
	    			while(!down.isEmpty()){
	    				System.out.println(down.remove().locationtext);
	    			}
	    			return;
	    		}
	    	}
	    }
	    
	    	    
	    //Check Upstream
	    cur = m.get(begin);
	    up.add(m.get(begin));
	    while(true){
	    	cur = m.get(cur.upstream);
	    	if(cur == null){
	    		break;
	    	}else{
	    		up.add(cur);
	    		if(cur.stationid.equals(end)){
	    			System.out.println("Found Path in Upstream:");
	    			while(!up.isEmpty()){
	    				System.out.println(up.remove().locationtext);
	    			}
	    			return;
	    		}
	    	}
	    }
	    
	    
	    
	    
	}
};
