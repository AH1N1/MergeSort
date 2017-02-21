import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Woj on 2016-10-31.
 */
public class FileWorker {

    private static List<String> filesLinkedList = new LinkedList<>();
    private static String spath, tmpPath;
    private static long fileNumber = 0;


    public static void main(String[] args) {

        FileWorker.sort(new File("src\\main\\resources\\warpeace.txt"), 400);


    }

    static void sort(File file, int lines) {
        try {
            spath = file.getParent();
            tmpPath = spath + "\\tmp\\";
            new File(String.valueOf(tmpPath)).mkdir();
            splitFile(lines, file);//sorts and splits on files. adds their paths to list
            mergeAllFiles();
            fileNumber = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Takes two ints that are numbers of lines of text file and returns sorted list of Strings which contains all lines between given numbers including the edge lines.
    //The file path is declared by given Path class.
    //Sort method ignores cases
    private static List<String> readLines(int from, int to, File file) throws IOException {
        List<String> listToReturn = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        from = (from <= 0 ? 1 : from);
        for (int i = 1; i <= to; i++) {
            if ((line = br.readLine()) == null) {
                listToReturn.sort((s, d) -> s.compareToIgnoreCase(d));
                return listToReturn;
            }
            if (i >= from) listToReturn.add(line);
        }
        listToReturn.sort((s, d) -> s.compareToIgnoreCase(d));
        return listToReturn;
    }

    private static void splitFile(int linesNumber, File file) throws IOException { // przerobic path na URL
        int from = 1, to = linesNumber, fileNumber = 0;
        List<String> listToPrint = new ArrayList<>();
        String fileName;
        do {
            listToPrint = readLines(from, to, file);
            fileName = "tmp" + fileNumber + ".txt";
            PrintWriter pw = new PrintWriter(new FileOutputStream(tmpPath + fileName));
            listToPrint.forEach(s -> pw.println(s));
            pw.close();
            filesLinkedList.add(fileName);
            from = to + 1;
            to += linesNumber;
            fileNumber++;
        } while (listToPrint.size() == linesNumber);
    }

    private static List<String> mergeTwoLists(List<String> list1, List<String> list2) {
        List<String> l1 = list1, l2 = list2, finalList = new ArrayList<>();
        while (!l1.isEmpty() && !l2.isEmpty()) {
            if (l1.get(0).compareTo(l2.get(0)) < 0) finalList.add(l1.remove(0));
            else finalList.add(l2.remove(0));
        }
        if (l2.isEmpty()) {
            l1.forEach(finalList::add);
        } else l2.forEach(finalList::add);
        return finalList;
    }

    private static void mergeAllFiles() throws IOException {
        while (filesLinkedList.size() > 1) {
            mergeTwoFiles(tmpPath + filesLinkedList.get(0), tmpPath + filesLinkedList.get(1));
        }
        System.out.println(filesLinkedList.get(0));
        System.out.println("all files merged. 1 file left");
    }

    private static void mergeTwoFiles(String path1, String path2) throws IOException {
        String targetFile = "tmpMerge" + fileNumber + ".txt";
        File file1 = new File(path1);
        File file2 = new File(path2);
        BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file1));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file2));
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(tmpPath + targetFile)), true);//new File(tmpPath+"tmpMerge"+fileNumber+".txt"));// nazwa plikow i sciezek powinna byc statyczna
        String line1 = bufferedReader1.readLine();
        String line2 = bufferedReader2.readLine();
        Boolean empty1, empty2 = false;

        while (!(empty1 = line1 == null) && !(empty2 = line2 == null)) {
            if (line1.compareToIgnoreCase(line2) < 0) {
                printWriter.println(line1);
                //printWriter.append(line1);
                line1 = bufferedReader1.readLine();
            } else {
                printWriter.println(line2);
                //printWriter.append(line2);
                line2 = bufferedReader2.readLine();
            }

        }
        if (empty1) {
            while (!(line2 == null)) {
                printWriter.println(line2);
                line2 = bufferedReader2.readLine();
                //printWriter.append(line2);
            }
        }
        if (empty2) {
            while (!(line1 == null)) {
                printWriter.println(line1);
                line1 = bufferedReader1.readLine();

            }

        }
        bufferedReader1.close();
        bufferedReader2.close();
        printWriter.flush();
        printWriter.close();
        System.out.println("before deleting " + filesLinkedList.size()+ " files left");
        file1.delete();
        file2.delete();
        filesLinkedList.remove(0);
        filesLinkedList.remove(0);
        filesLinkedList.add(targetFile);
        fileNumber++;
        System.out.println("after deleting and adding " + filesLinkedList.size()+ " files left");
    }


    //in case something went wrong and there are many files left ;)
    public static void delete() {
        String name = "ivnho11";
        int number = 0;
        while (true) {
            try {

                File file = new File("src\\main\\resources\\tmp\\" + name + number + ".txt");
                System.out.println(file.delete() + file.toString());

                number++;


            } catch (Exception e) {

                e.printStackTrace();

            }
        }
    }


}
