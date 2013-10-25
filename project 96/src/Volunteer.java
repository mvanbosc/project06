import edu.purdue.cs.cs180.channel.ChannelException;
import edu.purdue.cs.cs180.channel.MessageListener;
import edu.purdue.cs.cs180.channel.TCPChannel;

import java.util.*;

public class Volunteer implements MessageListener{
	TCPChannel channel = new TCPChannel(8888);
	Volunteer() {
		try {
            this.channel = new TCPChannel("localhost", 8888);
        } catch (ChannelException e) {
            e.printStackTrace();
        }
        // inform the channel that when new messages are received forward them
        // to the current client object.
        this.channel.setMessageListener(this);
	}
public static void main(String[] args) throws ChannelException {
	
	Scanner scans = new Scanner(System.in);
	
	 
     
     String id = "";
     String input="";
     
   
    while (true) {
    	System.out.println("Press ENTER when ready:");
    	Volunteer vol = new Volunteer();
    	
    	input = scans.nextLine();
     if (input.equals("")) {
    	 vol.sendMessage("hi"); // sends returned string
         //thread to wait
         try {
             System.out.println("Waiting for assignment...");
             Thread.sleep(2000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         vol.channel.close();
     }
        
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

