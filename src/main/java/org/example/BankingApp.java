package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class BankingApp {

    public static final String LOGFILE = "src/main/resources/operational.log";

    public static void main(String[] args) throws IOException {
        cleanLogFile();
        SpringApplication.run(BankingApp.class, args);
    }

    private static void cleanLogFile() throws IOException {
        File file = new File(LOGFILE);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }
}
