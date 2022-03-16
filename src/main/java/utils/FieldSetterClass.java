package utils;

import data.*;
import exceptions.ExitException;
import exceptions.NumberOutOfBoundsException;
import exceptions.WrongAmountOfCoordinatesException;

/**
 * Класс, устанавливающий значения полей объекта класса StudyGroup
 */
public class FieldSetterClass {
    /**
     * Метод, устанавливающий значение groupName
     * @param s
     * @return groupName
     */
    public static String getGroupName(String s){
        Parser.exceptionPointer = "name";
        ValidationClass.checkString(s, false);
        String name = new String(s);
        return name;
    }
    /**
     * Метод, устанавливающий значение groupCoordinates
     * @param s
     * @return groupCoordinates
     * @throws NumberOutOfBoundsException
     * @throws WrongAmountOfCoordinatesException
     */
    public static Coordinates getGroupCoordinates(String s) throws NumberOutOfBoundsException, WrongAmountOfCoordinatesException {
        Parser.exceptionPointer = "coordinates";
        ValidationClass.checkCoordinates(s, false);
        Coordinates coordinates = Coordinates.valueOf(s);
        if (s.equals("")){return null;}
        return coordinates;
    }
    /**
     * Метод, устанавливающий значение groupStudentsCount
     * @param s
     * @return groupStudentsCount
     * @throws NumberOutOfBoundsException
     */
    public static Long getGroupStudentsCount(String s) throws NumberOutOfBoundsException{
        Parser.exceptionPointer = "studentsCount";
        ValidationClass.checkLong(s, false, true, 0L);
        Long studentsCount = Long.valueOf(s);
        if (s.equals("")){return null;}
        return studentsCount;
    }
    /**
     * Метод, устанавливающий значение groupExpelledStudents
     * @param s
     * @return groupExpelledStudents
     * @throws NumberOutOfBoundsException
     */
    public static Integer getGroupExpelledStudents(String s) throws NumberOutOfBoundsException{
        Parser.exceptionPointer = "expelledStudents";
        ValidationClass.checkInt(s, true, true, 0);
        Integer expelledStudents = Integer.valueOf(s);
        if (s.equals("")){return null;}
        return expelledStudents;
    }
    /**
     * Метод, устанавливающий значение groupShouldBeExpelled
     * @param s
     * @return groupShouldBeExpelled
     * @throws NumberOutOfBoundsException
     */
    public static int getGroupShouldBeExpelled(String s) throws NumberOutOfBoundsException{
        Parser.exceptionPointer = "shouldBeExpelled";
        ValidationClass.checkInt(s, false, true, 0);
        int shouldBeExpelled = Integer.valueOf(s);
        return shouldBeExpelled;
    }
    /**
     * Метод, устанавливающий значение groupSemesterEnum
     * @param s
     * @return groupSemesterEnum
     */
    public static Semester getGroupSemesterEnum(String s){
        Parser.exceptionPointer = "semesterEnum";
        ValidationClass.checkSemester(s, true);
        Semester semester = Semester.valueOf(s);
        if (s.equals("")){return null;}
        return semester;
    }
    /**
     * Метод, устанавливающий значение adminName
     * @param s
     * @return adminName
     */
    public static String getAdminName(String s){
        Parser.exceptionPointer = "groupAdmin - name";
        ValidationClass.checkString(s, false);
        if (s.equals("")){return null;}
        return s;
    }
    /**
     * Метод, устанавливающий значение adminHeight
     * @param s
     * @return adminHeight
     * @throws NumberOutOfBoundsException
     */
    public static Long getAdminHeight(String s) throws NumberOutOfBoundsException{
        Parser.exceptionPointer = "groupAdmin - height";
        ValidationClass.checkLong(s, true, true, 0L);
        Long height = Long.valueOf(s);
        if (s.equals("")){return null;}
        return height;
    }
    /**
     * Метод, устанавливающий значение adminHairColor
     * @param s
     * @return adminHairColor
     */
    public static Color getAdminHairColor(String s){
        Parser.exceptionPointer = "groupAdmin - hairColor";
        ValidationClass.checkColor(s, false);
        Color hairColor = Color.valueOf(s);
        if (s.equals("")){return null;}
        return hairColor;
    }
    /**
     * Метод, устанавливающий значение adminNationality
     * @param s
     * @return adminNationality
     */
    public static Country getAdminNationality(String s){
        Parser.exceptionPointer = "groupAdmin - nationality";
        ValidationClass.checkCountry(s, true);
        Country nationality = Country.valueOf(s);
        if (s.equals("")){return null;}
        return nationality;
    }
    /**
     * Метод, устанавливающий значение adminLocation
     * @param s
     * @return adminLocation
     * @throws WrongAmountOfCoordinatesException
     */
    public static Location getAdminLocation(String s) throws WrongAmountOfCoordinatesException {
        Parser.exceptionPointer = "groupAdmin - location";
        ValidationClass.checkLocation(s, true);
        Location location = Location.valueOf(s);
        if (s.equals("")){return null;}
        return location;
    }
}
