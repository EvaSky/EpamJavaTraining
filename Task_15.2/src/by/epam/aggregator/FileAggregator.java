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
    private volatile List<Float> fileContent = new ArrayList<>();
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
               Float result = aggregation(aggregateMethod, fileContent);
                resultSetter.setResult(result);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void readFile() throws FileNotFoundException {
        try(Scanner sc = new Scanner(new FileInputStream(fileName))) {

            if (sc.hasNextLine()) {
                String operation = sc.nextLine();
                aggregateMethod = AggregateMethod.valueOf(Integer.parseInt(operation));
            }

            if (sc.hasNextLine()) {
                String floats = sc.nextLine();
                String[] ff = floats.split(" +");
                for (String s : ff) {
                    fileContent.add(Float.parseFloat(s));
                }
            }
        }
    }

    private Float aggregation(AggregateMethod aggregateMethod, List<Float> floats) {
        Float result = 0f;
        switch (aggregateMethod) {
            case SUM:
                for (Float f : floats) {
                    result += f;
                }
                break;
            case MULTIPLICATION:
                for (Float f : floats) {
                    if (result == 0) {
                        result = f;
                    } else {
                        result *= f;
                    }
                }
                break;
            case SQUARES_SUM:
                for (Float f : floats) {
                    result += f * f;
                }
                break;
        }
       return result;
    }
}
