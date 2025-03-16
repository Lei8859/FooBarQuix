package com.example.FooBarQuix.batch;

import com.example.FooBarQuix.sevice.FooBarQuixService;
import org.springframework.batch.item.util.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class FileProcessor implements CommandLineRunner {

    private final FooBarQuixService fooBarQuixService;

    public FileProcessor(FooBarQuixService fooBarQuixService) {
        this.fooBarQuixService = fooBarQuixService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java -jar foobarquix.jar <inputFile> <outputFile>");
            return;
        }

        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);

        List<String> lines = Files.readAllLines(Paths.get(inputFile.toURI()), StandardCharsets.UTF_8);
        List<String> results = new java.util.ArrayList<>();

        for (String line : lines) {
            try {
                int number = Integer.parseInt(line.trim());
                results.add(line + " -> " + fooBarQuixService.transformerNombre(number));
            } catch (NumberFormatException e) {
                results.add("Erreur : '" + line + "' n'est pas un nombre valide.");
            }
        }

        Files.write(Paths.get(args[1]), results, StandardCharsets.UTF_8);
        System.out.println("Fichier de sortie généré : " + args[1]);
    }
}
