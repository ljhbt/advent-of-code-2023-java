package de.ljhbt;

import de.ljhbt.days.Day;
import de.ljhbt.days.Day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        Day d = new Day01();
        List<String> input;
        //input = d.getInput(2022, 1, args[0]);
        input = mockInput();
        input.forEach(System.out::println);
        System.out.println("Part 1: \n" + d.solvePart1(input));
        System.out.println("Part 2: \n" + d.solvePart2(input));
    }

    private static List<String> mockInput() {
        InputStream input = Main.class.getClassLoader().getResourceAsStream("input.txt");
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.add(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}