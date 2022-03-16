import utils.Parser;
import utils.UserInterface;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main class of the program
 * @author Vinogradov Gleb
 */

public class Main {

    /**
     * Main method of the program
     * @param args name of environmental variable with file path
     */
    public static void main(String[] args){
        String path = null;
        try {

            path = System.getenv("FILE_PATH");
            File file = new File(path);
            Parser.setCollectionFromFile(file);
            UserInterface.interactiveMode();
        }
        catch (FileNotFoundException e){
            if (e.getMessage().contains("(Отказано в доступе)"))
            System.out.println(e.getMessage() + ", получите права на чтения файла или запустите программу с другим файлом");
            else System.out.println("Файл " + path + "Не найден");
        }
        catch (IndexOutOfBoundsException f){
            System.out.println("Слишком много объектов класса в одном файле");
        }
        catch (NullPointerException e){
            System.out.println("Указанной переменной окружения не существует");
        }
    }
}
