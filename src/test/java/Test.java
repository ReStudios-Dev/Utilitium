import org.ReStudios.utitlitium.Colorizium;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true){
            input = scanner.nextLine();

            System.out.println(Colorizium.apply(input, "&", null, "")+Colorizium.reset());
        }
    }
}
