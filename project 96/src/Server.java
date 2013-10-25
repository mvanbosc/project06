import edu.purdue.cs.cs180.channel.ChannelException;
import edu.purdue.cs.cs180.channel.MessageListener;
import edu.purdue.cs.cs180.channel.TCPChannel;
class Server implements MessageListener {
    /**
     * Creates a server channel on the current machine and assigns port 8888 to it.
     */
    TCPChannel channel = new TCPChannel(8888);

    /**
     * Constructor.
     */
    public Server() {
        // inform the channel that when new messages are received forward them
        // to the current server object.
        channel.setMessageListener(this);
    }
    @Override
    public void messageReceived(String message, int clientID) {
        System.out.println(message);
        // simple reply that message received, send it to the same client it
        // came from.
        try {
            channel.sendMessage(message + ": GOTCHA SLOOT", clientID);
        } catch (ChannelException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws ChannelException {
    	Server run = new Server();
    	
    }

}