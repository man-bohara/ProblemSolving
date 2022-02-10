import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/*
ExternalSort is a technique to sort a large file which can not fit in RAM (main memory). So it's possible to sort the whole file at once.
In this technique,
1. We first split content, so that it can fit in to memory and sort it and write into multiple files.
2. Second part is to read sorted files line by line and use min heap to keep track of the smallest value and append into final sorted file.
* */
public class ExternalSort {

    /*
    FileInfo object contains first line of content and Scanner reference for the given file.
    We need to keep scanner reference so that we can read content line by line.
    This object needs to be comparable by content so that PriorityQueue (Or min heap) can prioritize is accordingly.
    * */
    class FileInfo implements Comparable<FileInfo> {
        Scanner scanner;
        int number;

        public FileInfo(Scanner scanner, int number) {
            this.scanner = scanner;
            this.number = number;
        }

        public int compareTo(FileInfo other) {
            return Integer.compare(number, other.number);
        }
    }

    /*
    This is the chunkSize. We are just using it as number of lines. We should choose this as per available memory.
    * */
    private int chunkSize;

    public ExternalSort(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    /*
    * This method reads smaller sorted files line by line and puts first value from each file in PriorityQueue.
    * Then it runs while PriorityQueue is not empty and
    * in each iteration, it writes the smallest value (i.e. top of the queue) to final sorted file and adds another smallest value from same file into PQ.
    * */
    private void mergeAndSort(int fileCount) throws IOException {
        PriorityQueue<FileInfo> pq = new PriorityQueue<>();
        int fileIndex = 1;
        for (int i = 0; i < fileCount; i++) {
            File file = new File("sorted_file_" + fileIndex + ".txt");
            Scanner scanner = new Scanner(file);
            int number = Integer.parseInt(scanner.nextLine());
            FileInfo fileInfo = new FileInfo(scanner, number);
            pq.offer(fileInfo);
            fileIndex++;
        }
        FileWriter fileWriter = new FileWriter("final_sorted_file.txt", true);
        while (!pq.isEmpty()) {
            FileInfo fileInfo = pq.poll();
            fileWriter.append(fileInfo.number + "\n");
            if (fileInfo.scanner.hasNext()) {
                int nextNumber = Integer.parseInt(fileInfo.scanner.nextLine());
                FileInfo newFileInfo = new FileInfo(fileInfo.scanner, nextNumber);
                pq.offer(newFileInfo);
            }
        }
        fileWriter.close();
    }

    /*
    This method reads a chunk from large file and sorts the content and writes to smaller sorted files.
    * */
    private int readChunksAndSort(File file) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        int lineCount = 0;
        int fileIndex = 1;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            if (lineCount < chunkSize) {
                int number = Integer.parseInt(scanner.nextLine());
                numbers.add(number);
                lineCount++;
            } else {
                FileWriter fileWriter = new FileWriter("sorted_file_" + fileIndex + ".txt");
                Collections.sort(numbers);
                for (int number : numbers) {
                    fileWriter.write(number + "\n");
                }
                fileIndex++;
                lineCount = 0;
                numbers = new ArrayList<>();
                fileWriter.close();
            }
        }

        // Handle the case if there are any numbers left in the array
        if(numbers.size() > 0) {
            Collections.sort(numbers);
            FileWriter fileWriter = new FileWriter("sorted_file_" + fileIndex + ".txt");
            for (int number : numbers) {
                fileWriter.append(number + "\n");
            }
            fileWriter.close();
        }
        return fileIndex;
    }

    public static void main(String[] args) throws IOException {
        ExternalSort es = new ExternalSort(10);
        File file = new File("unsorted_numbers.txt");
        int fileCount = es.readChunksAndSort(file);
        System.out.println(fileCount);
        es.mergeAndSort(fileCount);
        System.out.println("Final sorted file has been created");
    }

}
