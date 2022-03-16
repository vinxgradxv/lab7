package utils;

import data.*;
import exceptions.NumberOutOfBoundsException;
import exceptions.WrongAmountOfCoordinatesException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

/**
 * Класс, описывающий парсер из CSV формата
 */
public class Parser {
    /**
     * Место, где произошла ошибка
     */
    static String exceptionPointer;
    /**
     * Количество объектов
     */
    static private int objectsCount;

    /**
     * Метод, создающий коллекцию на основе файла
     * @param file
     * @throws FileNotFoundException
     */
    public static void setCollectionFromFile(File file) throws FileNotFoundException {
        Collection.initCollection();
        String[][] values = CSVReader.getValuesFromFile(file);
        Hashtable<Integer, StudyGroup> studyGroupHashtable;
        objectsCount = CSVReader.getResultSize();
        for(int i = 0;i<objectsCount;i++){
            try {
                String name = FieldSetterClass.getGroupName(values[i][0]);
                Coordinates coordinates = FieldSetterClass.getGroupCoordinates(values[i][1]);
                Long studentsCount = FieldSetterClass.getGroupStudentsCount(values[i][2]);
                Integer expelledStudents = FieldSetterClass.getGroupExpelledStudents(values[i][3]);
                int shouldBeExpelled = FieldSetterClass.getGroupShouldBeExpelled(values[i][4]);
                Semester semesterEnum = FieldSetterClass.getGroupSemesterEnum(values[i][5]);
                String adminName = FieldSetterClass.getAdminName(values[i][6]);
                Long height = FieldSetterClass.getAdminHeight(values[i][7]);
                Color hairColor = FieldSetterClass.getAdminHairColor(values[i][8]);
                Country nationality = FieldSetterClass.getAdminNationality(values[i][9]);
                Location location = FieldSetterClass.getAdminLocation(values[i][10]);

                Person groupAdmin = new Person(adminName, height, hairColor, nationality, location);
                StudyGroup studyGroup = new StudyGroup(name, coordinates, studentsCount, expelledStudents, shouldBeExpelled,
                semesterEnum, groupAdmin);
                Collection.add(studyGroup.getId(), studyGroup);
            }
            catch (IllegalArgumentException e){
                System.out.println("Некорректное значение в поле " + exceptionPointer + " в строке " + i);
                System.out.println("Объект из этой строки не будет добавлен в коллекцию");
            }
            catch (NumberOutOfBoundsException f){
                System.out.println("Значение не входящее в указанные рамки в поле " + exceptionPointer + " в строке" + i);
                System.out.println("Объект из этой строки не будет добавлен в коллекцию");
            }
            catch (WrongAmountOfCoordinatesException g){
                System.out.println("Неверное количество координат в поле " + exceptionPointer + " в строке " + i);
                System.out.println("Объект из этой строки не будет добавлен в коллекцию");
            }
        }
    }

    /**
     * Метод, создающий файл на основе коллекции
     */
    public static void setFileFromCollection(){
        Hashtable<Long, StudyGroup> studyGroupHashtable = Collection.getStudyGroupHashtable();
        try {
            FileWriter fileWriter = new FileWriter("output.csv", false);
            for (StudyGroup studyGroup : studyGroupHashtable.values()) {
                String s;
                s = String.valueOf(studyGroup.getId()) + ", " + studyGroup.getName() + ", " + studyGroup.getCoordinates().toString() + ", " + studyGroup.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                       + ", " + studyGroup.getStudentsCount() + ", " + studyGroup.getExpelledStudents() + ", " + studyGroup.getShouldBeExpelled() + ", " +
                        studyGroup.getSemesterEnum() + ", " + studyGroup.getGroupAdmin().getName() + ", " + studyGroup.getGroupAdmin().getHeight() + ", " +
                        studyGroup.getGroupAdmin().getHairColor() + ", " + studyGroup.getGroupAdmin().getNationality() + ", " + studyGroup.getGroupAdmin().getLocation()+ "\n";
                fileWriter.write(s);

            }
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println("Файл, в который должна происходить запись данных, не существует");
        }
    }

}
