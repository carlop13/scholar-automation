package org.example;

import org.example.controller.ScholarController;
import org.example.view.ConsoleView;
import java.util.List;

/**
 * Main class that launches the application.
 * Creates instances of MVC components and begins the execution flow.
 */
public class App {

    public static void main(String[] args) {
        // --- CONFIGURATION ---
        String apiKey = "40dc14212f3327df40583ec1fe5480f60422f5556db31e9ce27911a731b1bbb6";

        List<String> researchersToProcess = List.of("Alan Turing", "Geoffrey Hinton");
        //String authorNameToSearch  = "Geoffrey Hinton";//z_vS-Y0AAAAJ - Alan Turing / VRg602UAAAAJ - Geoffrey Hinton


        if (apiKey.contains("TU_API_KEY")) {
            System.err.println("ERROR: API Key is not configured. Please edit Main.java.");
            return;
        }

        ConsoleView view = new ConsoleView();
        ScholarController controller = new ScholarController(view, apiKey);

        //System.out.println("Searching for publications of: " + authorNameToSearch + "...");
        //controller.findAndDisplayPublications(authorNameToSearch);
        for (String authorName : researchersToProcess) {
            System.out.println("\nProcessing researcher: " + authorName + "...");
            controller.findAndPersistPublications(authorName);
        }
    }
}