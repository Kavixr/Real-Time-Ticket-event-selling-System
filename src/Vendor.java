public class Vendor implements Runnable{
    private int Vendor_ID;
    private String Vendor_Name;
    private int Tickets_per_Release;
    private int Release_interval;
    private TicketPool ticket_Pool;

    public Vendor(int vendorID, String vendorName, int ticketsPerRelease, int releaseInterval, TicketPool ticketPool) {
        this.Vendor_ID = vendorID;
        this.Vendor_Name = vendorName;
        this.Tickets_per_Release = ticketsPerRelease;
        this.Release_interval = releaseInterval;
        this.ticket_Pool = ticketPool;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 0; i < Tickets_per_Release; i++) {
                    Thread.sleep(1000 * Release_interval);
                    TicketInfo Vendor_ticket = new TicketInfo(Vendor_Name);
                    ticket_Pool.addTicket_Info(Vendor_Name, Vendor_ticket);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor " + Vendor_ID + " is Stopped");
            }
        }
    }

}
