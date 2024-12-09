import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.module.Configuration;



public class TicketConfiguration {
    private int Total_Tickets;
    private int Ticket_Release_Rate;
    private int Customer_Retrieval_Rate;
    private int Max_Ticket_Capacity;

    public TicketConfiguration(int Total_ticket, int Ticket_Release_Rate, int Customer_Retrieval_Rate, int Max_Ticket_Capacity) {
        this.Total_Tickets = Total_ticket;
        this.Ticket_Release_Rate = Ticket_Release_Rate;
        this.Customer_Retrieval_Rate = Customer_Retrieval_Rate;
        this.Max_Ticket_Capacity = Max_Ticket_Capacity;
    }

    public TicketConfiguration() {
    }

    public void saveConfig(String filePath) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfiguration(String filePath) {
        Gson gson = new Gson();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            TicketConfiguration loadConfiguration = gson.fromJson(reader, TicketConfiguration.class);
            System.out.println(loadConfiguration.Total_Tickets);
            System.out.println(loadConfiguration.Ticket_Release_Rate);
            System.out.println(loadConfiguration.Customer_Retrieval_Rate);
            System.out.println(loadConfiguration.Max_Ticket_Capacity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotal_Tickets() {
        return Total_Tickets;
    }
    public void setTotal_Tickets(int Total_Tickets) {
        this.Total_Tickets = Total_Tickets;
    }

    public int getTicket_Release_Rate() {
        return Ticket_Release_Rate;
    }
    public void setTicket_Release_Rate(int Ticket_Release_Rate) {
        this.Ticket_Release_Rate = Ticket_Release_Rate;
    }

    public int getCustomer_Retrieval_Rate() {
        return Customer_Retrieval_Rate;
    }
    public void setCustomer_Retrieval_Rate(int Customer_Retrieval_Rate) {
        this.Customer_Retrieval_Rate = Customer_Retrieval_Rate;
    }

    public int getMax_Ticket_Capacity() {
        return Max_Ticket_Capacity;
    }
    public void setMax_Ticket_Capacity(int Max_Ticket_Capacity) {
        this.Max_Ticket_Capacity = Max_Ticket_Capacity;
    }




}
