import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private int max_Capacity;
    private TicketInfo ticket_info;
    private List<TicketInfo> ticketInfoList;
    private List<TicketInfo> threadsafeticketlist;
    private volatile boolean isoperationstopped;

    public TicketPool(int max_Capacity) {
        this.max_Capacity = max_Capacity;
        this.isoperationstopped = false;
        ticketInfoList = new ArrayList<>();
        threadsafeticketlist = Collections.synchronizedList(ticketInfoList);
    }

    public TicketPool(TicketInfo ticket_info) {
        this.ticket_info = ticket_info;
    }

    public void addTotal_tickets(TicketInfo ticket_info) {
        ticketInfoList.add(ticket_info);
    }

    public synchronized void addTicket_Info(String Vendor_name, TicketInfo ticket_info) {
        while (ticketInfoList.size() >= max_Capacity ) {
            try {
                System.out.println("TicketPool has reach out its capacity. Vendor " + Vendor_name + " is waiting...");
                wait(); // Wait until a ticket is removed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

        ticketInfoList.add(this.ticket_info);
        System.out.println("Ticket added successfully by vendor " +Vendor_name + " Now pool size is: " + ticketInfoList.size());

        notifyAll();
    }

    public synchronized void RemoveTicket_Info(String Customer_Name, TicketInfo ticket_info) {

        while (ticketInfoList.isEmpty()) {
            try {
                System.out.println("TicketPool is out of tickets. Customer " + Customer_Name + " is on hold...");
                wait(); // Wait until a ticket is added
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

//
        TicketInfo RemoveTicket_Info = ticketInfoList.remove(0);
        System.out.println("Customer " + Customer_Name + " has successfully retrieved ticket.Remaining tickets in pool: " + ticketInfoList.size());
        notifyAll(); //
    }

    public void display_tickets() {
        synchronized (ticketInfoList) {
            for (TicketInfo ticketInfo : ticketInfoList) {
                System.out.println("Ticket: " + ticketInfo.getTicket_name());
            }
        }
    }

    public synchronized int getTicket_pool() {
        return ticketInfoList.size();
    }

    public boolean isIsoperationstopped() {
        return isoperationstopped;
    }

    public void resetoperationFlag() {
        isoperationstopped = false;

    }

}
