package by.epam.aggregator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Olga Shahray on 06.07.2016.
 */
public class FileAggregator implements Runnable {

    private volatile File fileName;
    private volatile AggregateMethod aggregateMethod;
    private volatile List<Double> fileContent = new ArrayList<>();
    private volatile ResultSetter resultSetter;

    public FileAggregator(File fileName, ResultSetter resultSetter) {
        this.fileName = fileName;
        this.resultSetter = resultSetter;
    }

    @Override
    public void run() {
        try {
            readFile();
            if (fileContent.size() != 0) {
                Double result = aggregation(aggregateMethod, fileContent);
                resultSetter.setResult(result);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void readFile() throws FileNotFoundException {
        try (Scanner sc = new Scanner(new FileInputStream(fileName))) {

            if (sc.hasNextLine()) {
                String operation = sc.nextLine();
                aggregateMethod = AggregateMethod.valueOf(Integer.parseInt(operation));
            }

            if (sc.hasNextLine()) {
                String floats = sc.nextLine();
                String[] ff = floats.split(" +");
                for (String s : ff) {
                    fileContent.add(Double.parseDouble(s));
                }
            }
        }
    }

    private Double aggregation(AggregateMethod aggregateMethod, List<Double> doubles) {
        Double result = 0.0;
        switch (aggregateMethod) {
            case SUM:
                for (Double d : doubles) {
                    result += d;
                }
                break;
            case MULTIPLICATION:
                for (Double d : doubles) {
                    if (result == 0) {
                        result = d;
                    } else {
                        result *= d;
                    }
                }
                break;
            case SQUARES_SUM:
                for (Double d : doubles) {
                    result += d * d;
                }
                break;
        }
        return result;
    }
}
