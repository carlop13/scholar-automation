package org.example.view;

import org.example.model.ProfileResult;
import org.example.model.Publication;
import java.util.List;

/**
 * Represents the View in the MVC pattern.
 * Its sole responsibility is to display the researcher's data in the console.
 */
public class ConsoleView {
    public void displayPublications(String authorName, List<Publication> publications) {
        System.out.println("\n==============================================");
        System.out.println("      Publications for: " + authorName);
        System.out.println("==============================================");

        if (publications == null || publications.isEmpty()) {
            System.out.println("No publications found.");
        } else {
            System.out.println("Found " + publications.size() + " publications:");
            for (Publication pub : publications) {
                System.out.println("\n- Title: " + pub.getTitle());
                if (pub.getCitedBy() != null) {
                    System.out.println("  Cited By: " + pub.getCitedBy().getTotal());
                }
            }
        }
        System.out.println("==============================================");
    }

    public void displayError(String message) {
        System.err.println("ERROR: " + message);
    }
}