package by.epam.aggregator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Olga Shahray on 06.07.2016.
 */
public class Main {

    private static List<File> filesList = new ArrayList<>();
    private static List<Double> results = new ArrayList<>();
    private static List<Thread> threads = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.exit(1);
        }

        File directory = new File(args[0]);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (!file.isDirectory() && fileNameValidator(file.getName())) {
                filesList.add(file);
            }
        }

        ResultSetter resultSetter = new ResultSetter() {
            @Override
            public synchronized void setResult(Double result) {
                results.add(result);
            }
        };

        for (File f : filesList) {
            Thread t = new Thread(new FileAggregator(f, resultSetter));
            t.start();
            threads.add(t);
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            getSum(directory);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void getSum(File directory) throws FileNotFoundException, UnsupportedEncodingException {
        Double result = 0.0;
        for (Double d : results) {
            result += d;
        }
        File file = new File(directory.getAbsolutePath() + "\\" + "out.dat");
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        writer.println(result);
        writer.close();
    }

    private static boolean fileNameValidator(String fileName) {
        Pattern pattern = Pattern.compile("^in\\d+\\.dat$");
        Matcher matcher = pattern.matcher(fileName);
        return matcher.matches();
    }
}
