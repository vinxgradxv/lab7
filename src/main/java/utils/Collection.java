package utils;

import data.StudyGroup;

import java.time.LocalDateTime;
import java.util.Hashtable;

/**
 * Класс, описывающий коллекцию и определяющий взаимодействие с ней
 */
public class Collection {
    /**
     * Максимальное количество элементов, которое может хранить наша коллекция
     */
    public static final long MAX_SIZE = (long) Math.pow(10, 6);
    /**
     * Время инициализации коллекции
     */
    private static LocalDateTime initializationTime;
    /**
     * Текущий id коллекции
     */
    public static Long currentId = 0L;
    /**
     * HashTable, где хранятся все элементы коллекции
     */
    private static Hashtable<Long, StudyGroup> studyGroupHashtable = new Hashtable<>();

    /**
     * Метод, добавляющий новый элемент в коллекцию
     * @param key
     * @param studyGroup
     */
    public static void add(Long key, StudyGroup studyGroup){
        studyGroupHashtable.put(key, studyGroup);
    }

    /**
     * Метод, удаляющий элемент из коллекции
     * @param key
     */
    public static void remove(Long key){
        if (studyGroupHashtable.containsKey(key)) {
            studyGroupHashtable.remove(key);
            System.out.println("Элемент с ключом " + key + " был удален из коллекции");
        } else{
            System.out.println("Коллекция не содержит данный ключ");
        }
    }

    /**
     * Метод, удаляющий все элементы коллекции
     */
    public static void clear(){
        studyGroupHashtable.clear();
    }

    /**
     * Метод, возвращающий HashTable со всеми элементами коллекции
     * @return studyGroupHashTable
     */
    public static Hashtable<Long, StudyGroup> getStudyGroupHashtable() {
        return studyGroupHashtable;
    }

    /**
     * Метод, возвращающий класс, реализуемой коллекции
     * @return type
     */
    public static Class getType(){
        return studyGroupHashtable.getClass();
    }

    /**
     * Метод возвращающий время инициализации коллекции
     * @return initializationTime
     */
    public static LocalDateTime getInitializationTime(){
        return initializationTime;
    }

    /**
     * Метод, инициализирующий коллекцию
     */
    public static void initCollection(){
        initializationTime = LocalDateTime.now();
    }

    /**
     * Метод, возвращающий размер коллекции
     * @return size
     */
    public static int getSize(){return studyGroupHashtable.size();}
}
