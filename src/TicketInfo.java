public class TicketInfo {

    private String ticket_name;
    private String buy_ticket_name;
    private TicketPool ticket_pool;

    public TicketInfo(String Vendor_name) {
        this.ticket_name = Vendor_name;
    }

    public TicketInfo(){

    }


    public String getTicket_name() {
        return ticket_name;
    }
    public void setTicket_name(String ticket_name) {
        this.ticket_name = ticket_name;
    }
    public String getBuy_ticket_name() {
        return buy_ticket_name;
    }
}
