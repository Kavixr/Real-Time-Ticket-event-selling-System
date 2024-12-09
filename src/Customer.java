import java.util.PrimitiveIterator;

public class Customer implements Runnable{
    private int Customer_ID;
    private String Customer_Name;
    private int Retrieval_interval;
    private TicketPool ticketPool;


    public Customer(int ID, String Name, int Retrieval_interval, TicketPool ticketPool) {
        this.Customer_ID = ID;
        this.Customer_Name = Name;
        this.Retrieval_interval = Retrieval_interval;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (true) {
            try{
                Thread.sleep(1000 * Retrieval_interval);
                TicketInfo Customer_Ticket = new TicketInfo(Customer_Name);
                ticketPool.RemoveTicket_Info(Customer_Name,Customer_Ticket);



            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Customer " + Customer_Name + " is Stopped");

            }
        }
    }
    public void Stop() {

    }

}
