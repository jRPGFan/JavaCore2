package Java_Core_2.Homework_5;

import java.util.Arrays;

public class Main {
    static final int SIZE = 10_000_000;
    static final int HALF_SIZE = SIZE / 2;

    static class arrayCalculationsThread extends Thread{
        private float[] array;
        private int startingPosition;

        arrayCalculationsThread(String threadName, float[] array, int startingPosition){
            super(threadName);
            this.array = array;
            this.startingPosition = startingPosition;
            start();
        }

        @Override
        public void run(){
            long timerStart = System.currentTimeMillis();
            System.out.println(this.getName() + " has stated at " + timerStart);
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] = (float)(array[i] * Math.sin(0.2f + (i + startingPosition) / 5)
                        * Math.cos(0.2f + (i + startingPosition) / 5) * Math.cos(0.4f + (i + startingPosition) / 2));
            }
            System.out.println(this.getName() + " has finished in " + (System.currentTimeMillis() - timerStart));
        }
    }

    public static void main(String[] args) {
//        float[] arr1 = new float[10];
//        float[] arr2 = new float[10];
        float[] arr1 = new float[SIZE];
        float[] arr2 = new float[SIZE];

        Arrays.fill(arr1, 1f);
        Arrays.fill(arr2, 1f);

        long timerStart = System.currentTimeMillis();
        arrayCalculations(arr1);
        long timerElapsed = System.currentTimeMillis() - timerStart;
        System.out.println("Array calculations via a single thread: " + timerElapsed + "ms");
        //System.out.println(Arrays.toString(arr1));

        timerStart = System.currentTimeMillis();
        threadedArrayCalculations(arr2);
        timerElapsed = System.currentTimeMillis() - timerStart;
        System.out.println("Array calculations via two threads: " + timerElapsed + "ms");
        //System.out.println(Arrays.toString(arr2));
    }

    public static void arrayCalculations(float[] array){
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] = (float)(array[i] * Math.sin(0.2f + i / 5)
                    * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public static void threadedArrayCalculations(float[] array){
//        float[] firstHalf = new float[5];
//        float[] secondHalf = new float[5];
        float[] firstHalf = new float[HALF_SIZE];
        float[] secondHalf = new float[HALF_SIZE];

//        System.arraycopy(array, 0, firstHalf, 0, 5);
//        System.arraycopy(array, 5, secondHalf, 0, 5);
        System.arraycopy(array, 0, firstHalf, 0, HALF_SIZE);
        System.arraycopy(array, HALF_SIZE, secondHalf, 0, HALF_SIZE);

//        arrayCalculationsThread firstHalfThread = new arrayCalculationsThread("Thread_1", firstHalf);
//        arrayCalculationsThread secondHalfThread = new arrayCalculationsThread("Thread_2", secondHalf);
        arrayCalculationsThread firstHalfThread = new arrayCalculationsThread("Thread_1", firstHalf, 0);
        arrayCalculationsThread secondHalfThread = new arrayCalculationsThread("Thread_2", secondHalf, HALF_SIZE);

        try {
            firstHalfThread.join();
            secondHalfThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.arraycopy(firstHalf, 0, array, 0, 5);
//        System.arraycopy(secondHalf, 0, array, 5, 5);
        System.arraycopy(firstHalf, 0, array, 0, HALF_SIZE);
        System.arraycopy(secondHalf, 0, array, HALF_SIZE, HALF_SIZE);
    }
}
