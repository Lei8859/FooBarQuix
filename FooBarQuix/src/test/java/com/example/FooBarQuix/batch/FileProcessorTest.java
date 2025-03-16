package com.example.FooBarQuix.batch;

import com.example.FooBarQuix.sevice.FooBarQuixService;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class FileProcessorTest {

    @Mock
    private FooBarQuixService fooBarQuixService;

    @InjectMocks
    private FileProcessor fileProcessor;

    private File inputFile;
    private File outputFile;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Fichiers temporaires pour le test
        inputFile = File.createTempFile("input", ".txt");
        outputFile = File.createTempFile("output", ".txt");

        // Écriture de données de test
        List<String> inputLines = Arrays.asList("3", "5", "15", "7", "51");
        FileUtils.writeLines(inputFile, StandardCharsets.UTF_8.name(), inputLines);
    }

    @Test
    void testBatchProcessing() throws Exception {
        // Simulation du comportement du service
        when(fooBarQuixService.transformerNombre(3)).thenReturn("FOOFOO");
        when(fooBarQuixService.transformerNombre(5)).thenReturn("BARBAR");
        when(fooBarQuixService.transformerNombre(15)).thenReturn("FOOBARBAR");
        when(fooBarQuixService.transformerNombre(7)).thenReturn("QUIX");
        when(fooBarQuixService.transformerNombre(51)).thenReturn("FOOBAR");

        // Exécution du batch avec les fichiers temporaires
        fileProcessor.run(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());

        // Lecture des résultats
        List<String> outputLines = FileUtils.readLines(outputFile, StandardCharsets.UTF_8);

        // Vérification des résultats attendus
        List<String> expectedLines = Arrays.asList(
                "3 -> FOOFOO",
                "5 -> BARBAR",
                "15 -> FOOBARBAR",
                "7 -> QUIX",
                "51 -> FOOBAR"
        );

        Assertions.assertIterableEquals(expectedLines, outputLines);

        // Vérification que `transformerNombre` a bien été appelé
        verify(fooBarQuixService, times(1)).transformerNombre(3);
        verify(fooBarQuixService, times(1)).transformerNombre(5);
        verify(fooBarQuixService, times(1)).transformerNombre(15);
        verify(fooBarQuixService, times(1)).transformerNombre(7);
        verify(fooBarQuixService, times(1)).transformerNombre(51);
    }

    @AfterEach
    void cleanUp() {
        inputFile.delete();
        outputFile.delete();
    }
}
