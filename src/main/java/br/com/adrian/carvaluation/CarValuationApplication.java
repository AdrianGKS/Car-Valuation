package br.com.adrian.carvaluation;

import br.com.adrian.carvaluation.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarValuationApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(CarValuationApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main();
        main.menu();
    }
}
