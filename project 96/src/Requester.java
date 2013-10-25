import edu.purdue.cs.cs180.channel.ChannelException;
import edu.purdue.cs.cs180.channel.MessageListener;
import edu.purdue.cs.cs180.channel.TCPChannel;

import java.util.*;
public class Requester implements MessageListener{
	TCPChannel channel = new TCPChannel(8888);
    Requester() {
    	try {
            this.channel = new TCPChannel("localhost", 8888);
        } catch (ChannelException e) {
            e.printStackTrace();
        }
        // inform the channel that when new messages are received forward them
        // to the current client object.
        this.channel.setMessageListener(this);
    }

    public String getInput() { //gets input form user Requester

        Scanner scan = new Scanner(System. in );
        System.out.println("Enter your location (1-5):");
        System.out.println("1. CL50 - Class of 1950 Lecture Hall");
        System.out.println("2. EE - Electrical Engineering Building");
        System.out.println("3. LWSN - Lawson Computer Science Building");
        System.out.println("4. PMU - Purdue Memorial Union");
        System.out.println("5. PUSH - Purdue University Student Health Center");
        ArrayList<String> a = new ArrayList<String>();
        a.add("CL50");
        a.add("EE");
        a.add("LWSN");
        a.add("PMU");
        a.add("PUSH"); // adds abrev's
        String nums = scan.nextLine(); //get input from user
        String num = (nums.substring(0, 1));
    
        String returned = "";
        // add check to make sure the first value is an integer
        int parsed = 0;
        Scanner check = new Scanner(nums);
        if  (check.hasNextInt()) {
        	parsed = Integer.parseInt(nums);
        }
        else {
        	parsed = -1;
        }
        // end check
       
        if (parsed >0 && parsed <= 5) {
        if (num.equals("1") || num.equals("2") || num.equals("3") || num.equals("4") || num.equals("5")) { // check if valid integer 1-5
            System.out.println(num);
            
            returned = ("Request" + " " + a.get(parsed-1));
         
        } } else {
	System.out.println("Error: Incorrect Format, try again!");
         returned =  "ERROR";
          
        } 
        return returned;
        
    }



    public static void main(String[] args) throws ChannelException {
    	Requester rec = new Requester();
    	Server server = new Server();
    	String a="";
    	a = rec.getInput(); // stores the inputed string
    	if (a.equals("ERROR")) {
    		rec.channel.close(); //kills program
            server.channel.close();
    	}
    	else {

    	
        rec.sendMessage(a); // sends returned string
        //thread to wait
        try {
            System.out.println("Waiting for volunteer...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        rec.channel.close();
        server.channel.close();

    	}


    }

    @Override
    public void messageReceived(String message, int channelID) {
        // simply print the message.
        System.out.println(message);
    }

    public void sendMessage(String message) {
        // send a message, since we did not specify a client ID, then the
        // message will be sent to the server.
        try {
            channel.sendMessage(message);
        } catch (ChannelException e) {
            e.printStackTrace();
        }
    }
}