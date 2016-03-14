package edu.pdx.ccmgt.ccmgt;

import java.util.*;

import com.datastax.driver.core.Row;


public class Query4 extends Query{
	class Station{
		public String locationtext;
		public int stationid;
		public int downstream;
		public int upstream;
		
		public Station(String loc, int statid, int down, int up){
			this.locationtext = loc;
			this.stationid = statid;
			this.downstream = down;
			this.upstream = up;
		}
	}
	
	public void execute(){
		//To To: Write your query here and print out the result
		int begin=0, end=0;
		
		Map<String, Station> m = new HashMap<String, Station>();
		Queue<Station> up = new LinkedList<Station>();
		Queue<Station> down = new LinkedList<Station>();
	    result = session.execute("select locationtext, stationid, downstream, upstream from station_data");
	    for(Row row: result){
	        Station s = new Station(row.getString("locationtext"), 
	        		row.getInt("stationid"), 
	        		row.getInt("downstream"),
	        		row.getInt("upstream"));
	        m.put(row.getString("stationid"), s);
	        
	        if(row.getString("locationtext").equals("Johnson Cr NB")){
	        	begin = row.getInt("stationid");
	        }
	        
	        if(row.getString("locationtext").equals("Columbia to I-205 NB")){
	        	end = row.getInt("stationid");
	        }   
	    }
	    
	    if(begin == 0 || end == 0){
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
	    		if(cur.stationid == end){
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
	    		if(cur.stationid == end){
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
