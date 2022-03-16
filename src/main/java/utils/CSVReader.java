package utils;

import exceptions.WrongLineSizeInFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класс, который осуществляет чтение из CSV файла
 */
public class CSVReader {
    /**
     * Текущее количество элементов, которое будет добавлено в коллекцию
     */
    private static int resultSize = 0;

    /**
     * Метод, возвращающий двумерный массив значений
     * @param file
     * @return двумерный массив String
     * @throws FileNotFoundException
     * @throws IndexOutOfBoundsException
     */
    public static String[][] getValuesFromFile(File file) throws FileNotFoundException, IndexOutOfBoundsException {
        String[][] result = new String[1000][11];
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String s = reader.nextLine();
                String[] resultLine = normalizeString(s);
                for (int i = 0;i<11;i++){
                    result[resultSize][i] = resultLine[i];
                }
                resultSize += 1;
            }
        }
        catch (WrongLineSizeInFileException e){
            System.out.println("Неверное количество значений в одной из строк файла");
            System.out.println("Объект из этой строки не будет добавлен в коллекцию");
        }
        return result;
    }


    /**
     * Метод, приводящий строку к нормальному виду
     * @param s
     * @return массив строк
     * @throws WrongLineSizeInFileException
     */
    private static String[] normalizeString(String s) throws WrongLineSizeInFileException{
        boolean status = false;
        s = "," + s;
        String[] result = new String[11];
        for(int i = 0;i<11;i++){
            result[i] = "";
        }
        int resultSize = -1;
        for(int i = 0;i<s.length();i++){

            if (resultSize == 11){break;}

            if (s.charAt(i) == ',' && !status && s.charAt(i+1) == '"'){
                status = true;
                resultSize += 1;
            }
            else if(s.charAt(i) == ',' && status){
                result[resultSize] += s.charAt(i);
            }
            else if(status && s.charAt(i) == '"' && s.charAt(i+1) == ','){
                status = false;
            }
            else if(!status && s.charAt(i) == ','){
                resultSize += 1;
            }
            else if(s.charAt(i) != '"'){
                result[resultSize] += s.charAt(i);
            }
        }
        if (resultSize != 10){
            throw new WrongLineSizeInFileException();
        }
        return result;
    }

    /**
     * Метод, возвращающий количество элементов, которые будут добавлены в коллекцию
     * @return size
     */
    public static int getResultSize(){
        return resultSize;
    }
}

