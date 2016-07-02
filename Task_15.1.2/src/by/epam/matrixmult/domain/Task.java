package by.epam.matrixmult.domain;

/**
 * Created by Olga Shahray on 02.07.2016.
 */
public class Task implements Comparable<Task>{
    private volatile int i;
    private volatile int j;
    private volatile Matrix matrix1;
    private volatile Matrix matrix2;
    private volatile Matrix result;

    public Task(int i, int j, Matrix matrix1, Matrix matrix2, Matrix result) {
        this.i = i;
        this.j = j;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result = result;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Matrix getMatrix1() {
        return matrix1;
    }

    public Matrix getMatrix2() {
        return matrix2;
    }

    public Matrix getResult() {
        return result;
    }

    @Override
    public int compareTo(Task o) {
        if (this.i != o.getI()){
            return this.i - o.getI();
        }
        else{
            return this.j - o.getJ();
        }

    }
}
