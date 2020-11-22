import java.util.LinkedHashSet;
import java.util.Scanner;

public class Showtime implements Runnable {
    private static final Object lock = new Object();
    private LinkedHashSet<Seat> freeSeats = new LinkedHashSet<Seat>();
    private int rows;
    private int seats;

    public Showtime(int rows, int seats){
        this.rows = rows;
        this.seats = seats;
        generateSeats();
    }

     private void generateSeats(){
        for (int  i = 1; i <= rows ; i ++){
            for (int j = 1; j <= seats; j++ ){
                freeSeats.add(new Seat(i, j));
            }
        }
    }

    public boolean bookSeat(Seat seat) {
        boolean result = false;
        for (Seat freeSeat : freeSeats) {
            if (seat.getRow() == freeSeat.getRow() && seat.getSeat() == freeSeat.getSeat()) {
                freeSeats.remove(freeSeat);
                result = true;
                break;
            }
        }
        return result;
    }

    private Seat booking() {
        System.out.print("Какое место?: ");
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int seat = in.nextInt();
        return new Seat(row, seat);
    }

    public void run() {
        while (!freeSeats.isEmpty()) {
            synchronized (lock) {
                Seat bookingSeat = booking();
                if (bookSeat(bookingSeat)) {
                    System.out.println("Вы успешно забронировали " + bookingSeat.toString());
                    break;
                } else {
                    System.out.println(bookingSeat.toString() + " уже забронировано");
                }
            }
        }
    }
}
