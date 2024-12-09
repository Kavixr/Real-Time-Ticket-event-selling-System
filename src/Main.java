import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int ticket_Count;
    static int ticket_Release_Rate;
    static int Cus_Retrieval_Rate;
    static int Max_Ticket_Capacity;

    public static void main(String[] args) {
        System.out.println("*".repeat(54));
        System.out.println("* WELCOME TO THE REAL-TIME EVENT TICKETING SYSTEM! * ");
        System.out.println("*".repeat(54));

        while (true) {
            try {
                while (true) {
                    System.out.println(" 1. Configuration--> ");
                    System.out.println(" 2. Save Configuration--> ");
                    System.out.println(" 3. Load Configuration--> ");
                    System.out.println(" 4. Exit--> ");
                    System.out.println("*".repeat(54));
                    System.out.println();
                    System.out.println("Enter the Number: ");

                    int UserChoice = sc.nextInt();
                    switch (UserChoice) {
                        case 1:
                            ticket_Count = configticket_count();
                            ticket_Release_Rate = configticket_release_rate();
                            Cus_Retrieval_Rate = configretrieval_rate();
                            Max_Ticket_Capacity = configmax_capacity();
                            break;

                        case 2:
                            TicketConfiguration();
                            break;

                        case 3:
                            loadSetting();
                            break;

                        case 4:
                            System.exit(0);
                            break;

                        case 5:
                            Threading_Start();
                            break;

                        default:
                            System.out.println("Invalid Selection !!. Please Chooses again");
                    }

                }
            } catch(InputMismatchException e){
                System.out.println("Invalid Input!!.Please add valid number");
                sc.nextLine();
            }
        }


    }

    public static int configticket_count() {
        int ticket_Count = 0;

        while (true) {
            try {
                System.out.println("Please enter total number of tickets: ");
                ticket_Count = sc.nextInt();
                if (ticket_Count >= 0) {
                    return ticket_Count;
                } else {
                    System.out.println("OOPS!..Number of tickets must be positive. Please try again!!... ");
                }

            } catch (InputMismatchException e) {
                System.out.println("OOH!!.. Please try again... ");
                sc.nextLine();
            }
        }
    }

    public static int configticket_release_rate() {
        int ticket_Release_Rate = 0;
        while (true) {
            try {
                System.out.println("Please enter the ticket release rate: ");
                ticket_Release_Rate = sc.nextInt();
                if (ticket_Release_Rate > 0) {
                    return ticket_Release_Rate;
                } else {
                    System.out.println("OOPS!..Invalid. Please try again!!... ");
                    sc.nextLine();
                }

            } catch (InputMismatchException e) {
                System.out.println("OOPS!!.. Please try again... ");
            }
        }
    }

    public static int configretrieval_rate() {
        int Cus_Retrieval_Rate = 0;
        while (true) {
            try {
                System.out.println("Please enter the customer retrieval rate: ");
                Cus_Retrieval_Rate = sc.nextInt();
                if (Cus_Retrieval_Rate > 0) {
                    return Cus_Retrieval_Rate;
                } else {
                    System.out.println("OOPS!..Invalid. Please try again!!... ");
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("OOPS!!.. Please try again... ");
            }
        }
    }

    public static int configmax_capacity() {
        int Max_Ticket_Capacity = 0;
        while (true) {
            try {
                System.out.println("Please enter the max: ");
                Max_Ticket_Capacity = sc.nextInt();
                if (Max_Ticket_Capacity > 0) {
                    return Max_Ticket_Capacity;
                } else {
                    System.out.println("OOPS!..Invalid. Please try again!!... ");
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("OOPS!!.. Please try again... ");
            }
        }
    }

    public static void TicketConfiguration() {
        TicketConfiguration configuration = new TicketConfiguration(ticket_Count, ticket_Release_Rate, Cus_Retrieval_Rate, Max_Ticket_Capacity);
        configuration.saveConfig("Saveconfig.json");
    }

    public static void loadSetting() {
        TicketConfiguration configuration = new TicketConfiguration();
        configuration.loadConfiguration("Saveconfig.json");
    }

    public static void Threading_Start() {

        String[] Vendor_name = {"V1", "V2", "V3", "V4", "V5"};
        String[] Customer_name = {"C1", "C2", "C3", "C4", "C5"};

        TicketPool ticketpoolinstance = new TicketPool(Max_Ticket_Capacity);

        List<Vendor> vendor_list = new ArrayList<>();
        List<Customer> customer_list = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        // Creating vendors and customers
        for (int i = 0; i < Vendor_name.length; i++) {
            String Vendorname = Vendor_name[i];
            Vendor vr = new Vendor(i, Vendorname, ticket_Release_Rate, 3000, ticketpoolinstance);
            vendor_list.add(vr);
        }

        for (int i = 0; i < Customer_name.length; i++) {
            String Customername = Customer_name[i];
            Customer cr = new Customer(i, Customername, Cus_Retrieval_Rate, ticketpoolinstance);
            customer_list.add(cr);
        }

        for (Vendor vendor : vendor_list) {
            Thread ven_thread = new Thread(vendor);
            ven_thread.start();
            threads.add(ven_thread);
        }

        for (Customer customer : customer_list) {
            Thread cus_thread = new Thread(customer);
            cus_thread.start();
            threads.add(cus_thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}