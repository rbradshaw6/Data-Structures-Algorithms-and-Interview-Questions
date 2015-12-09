import java.util.ArrayList;
import java.util.List;

public class NumberTheory {

    public NumberTheory() {

    }

    public boolean isPrime(int integer) {
        if (integer < 1) {
            return false;
        }

        for (int i = 2; i <= integer / 2; i++) {
            if (integer % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isPerfect(int integer) {

        int sum = 0;
        for (int i = 1; i <= integer / 2; i++) {
            if (integer % i == 0) {
                sum += i;
            }
        }

       return (sum == integer) ? true : false;
    }

    public static void main(String[] args) {
        NumberTheory test = new NumberTheory();

        System.out.println(test.isPerfect(4967));
    }


}
