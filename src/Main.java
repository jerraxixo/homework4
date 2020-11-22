import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите размерность зала: ");
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt();
        int seats = in.nextInt();
        Thread t1 = new Thread(new Showtime(rows, seats));
        Thread t2 = new Thread(new Showtime(rows, seats));

        t1.start();
        t2.start();
    }
}