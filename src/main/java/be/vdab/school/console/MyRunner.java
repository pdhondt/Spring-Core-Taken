package be.vdab.school.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("Welkom in de school");
    }
}
