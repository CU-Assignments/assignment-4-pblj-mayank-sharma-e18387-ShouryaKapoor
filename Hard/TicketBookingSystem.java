import java.util.*;

class TicketBookingSystem {
    private final Set<Integer> bookedSeats = new HashSet<>();
    private final int totalSeats;
    
    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
    }
    
    public synchronized boolean bookSeat(int seatNumber, String customerType) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            System.out.println(customerType + " Booking failed: Invalid seat number " + seatNumber);
            return false;
        }
        if (!bookedSeats.contains(seatNumber)) {
            bookedSeats.add(seatNumber);
            System.out.println(customerType + " Booking successful: Seat " + seatNumber);
            return true;
        } else {
            System.out.println(customerType + " Booking failed: Seat " + seatNumber + " is already booked");
            return false;
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String customerType;

    public BookingThread(TicketBookingSystem system, int seatNumber, String customerType, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerType = customerType;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerType);
    }
}

public class TicketBookingMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(10);
        
        List<Thread> threads = new ArrayList<>();
        
        threads.add(new BookingThread(system, 3, "VIP", Thread.MAX_PRIORITY));
        threads.add(new BookingThread(system, 3, "Regular", Thread.MIN_PRIORITY));
        threads.add(new BookingThread(system, 5, "VIP", Thread.MAX_PRIORITY));
        threads.add(new BookingThread(system, 5, "Regular", Thread.MIN_PRIORITY));
        threads.add(new BookingThread(system, 7, "Regular", Thread.NORM_PRIORITY));
        
        for (Thread thread : threads) {
            thread.start();
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
