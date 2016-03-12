package edu.pdx.ccmgt.ccmgt;

import java.util.Scanner;

public class App 
{
	private static Scanner in;
	
	public static int menu(){
		int selection;
		in = new Scanner(System.in);
		
		System.out.println("\n\nCS510 Cloud Computing Demo  ");
		System.out.println("----------------------------");
		System.out.println("[1] Counting High Speeds    ");
		System.out.println("[2] Calculating Volume      ");
		System.out.println("[3] Peak Period Travel Time ");
		System.out.println("[4] Route Finding           ");
		System.out.println("[5] Updating Station Length ");
		System.out.println("----------------------------");
		System.out.println("Please press 1-5 for a demo ");
		System.out.println("Please any other key to exit");
		
		selection = in.nextInt();
		return selection;
	}
	
    public static void main( String[] args )
    {
    	
    	Query q = new Query();
    	
    	boolean flag = true;
    	
    	while(flag){
    		switch(menu()){
    		case 0:
    			System.out.println("Start a example query");
    			q = new Query();
    			break;
    		case 1:
    			System.out.println("Start Counting High Speeds");
    			q = new Query1();
    			break;
    		case 2:
    			System.out.println("Start Calculating Valume");
    			q = new Query2();
    			break;
    		case 3:
       			System.out.println("Start Calculating Peak Period Travel Time");
       			q = new Query3();
    			break;
    		case 4:
       			System.out.println("Start Finding a Route");
       			q = new Query4();
    			break;
    		case 5:
    			System.out.println("Start Updating Station Length");
    			q = new Query5();
    			break;
    		default:
    			System.out.println("Exit Demo!");
    			flag = false;
    			break;
    		}
    		
    		if(flag){
    			q.connect("127.0.0.1", "freeway");
    			q.execute();
    			
    		}
    	}
    	
    }
}
