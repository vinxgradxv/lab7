package data;

import utils.Collection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 * Класс хранимых объектов
 */

public class StudyGroup implements Comparable<StudyGroup>{
    /**
     * Уникальный идентификатор группы. Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным,
     * Значение этого поля должно генерироваться автоматически
     */
    private Long id;
    /**
     * Имя группы. Поле не может быть null, Строка не может быть пустой
     */
    private String name;
    /**
     * Координаты группы. Поле не может быть null
     */
    private Coordinates coordinates;
    /**
     * Дата создания группы. Поле не может быть null, Значение этого поля должно генерироваться автоматически
     */
    private LocalDateTime creationDate;
    /**
     * Количество студентов. Значение поля должно быть больше 0, Поле не может быть null
     */
    private Long studentsCount;
    /**
     * Количество отчисленных студентов. Значение поля должно быть больше 0, Поле может быть null
     */
    private Integer expelledStudents;
    /**
     * Количество студентов, представленных к отчислению. Значение поля должно быть больше 0
     */
    private int shouldBeExpelled;
    /**
     * Текущий семестр. Поле может быть null
     */
    private Semester semesterEnum;
    /**
     * Админ группы. Поле не может быть null
     */
    private Person groupAdmin;

    /**
     * Конструктор без параметров
     */
    public StudyGroup(){};

    /**
     * Конструктор со всеми нужными параметрами
     * @param name имя
     * @param coordinates координаты
     * @param studentsCount количество студентов
     * @param expelledStudents отчисленные студенты
     * @param shouldBeExpelled студенты, которых стоит отчислить
     * @param semesterEnum семестр
     * @param groupAdmin админ группы
     */
    public StudyGroup(String name, Coordinates coordinates, Long studentsCount,
                      Integer expelledStudents, int shouldBeExpelled, Semester semesterEnum, Person groupAdmin){
        this.id = Collection.currentId;
        Collection.currentId += 1;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.studentsCount = studentsCount;
        this.expelledStudents = expelledStudents;
        this.shouldBeExpelled = shouldBeExpelled;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    /**
     * Метод, возвращающий id группы
     * @return id
     */
    public Long getId() {
        return id;
    }
    /**
     * Метод, возвращающий координаты группы
     * @return coordinates
     */
    public Coordinates getCoordinates(){
        return  coordinates;
    }

    /**
     * Метод, возвращающий имя группы
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий дату создания группы
     * @return creationDate
     */
    public LocalDateTime getCreationDate(){
        return creationDate;
    }

    /**
     * Метод, возвращающий количество студентов
     * @return studentsCount
     */
    public Long getStudentsCount() {
        return studentsCount;
    }

    /**
     * Метод, возвращающий количество отчисленных студентов
     * @return expelledStudents
     */
    public Integer getExpelledStudents() {
        return expelledStudents;
    }

    /**
     * Метод, возвращающий количество студентов, которые должны быть отчислены
     * @return shouldBeExpelled
     */
    public int getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    /**
     * Метод, возвращающий текущий семестр
     * @return semesterEnum
     */
    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    /**
     * Метод, возвращающий админа группы
     * @return groupAdmin
     */
    public Person getGroupAdmin() {
        return groupAdmin;
    }

    /**
     * Метод, устанавливающий значение id
     * @param id уникальный идентификатор
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Метод, устанавливающий значение name
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод, устанавливающий значение coordinates
     * @param coordinates координаты
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Метод, устанавливающий значение creationDate
     * @param creationDate дата создания
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Метод, устанавливающий значение studentsCount
     * @param studentsCount количество студентов
     */
    public void setStudentsCount(Long studentsCount) {
        this.studentsCount = studentsCount;
    }

    /**
     * Метод, устанавливающий значение expelledStudents
     * @param expelledStudents отчисленные студенты
     */
    public void setExpelledStudents(Integer expelledStudents) {
        this.expelledStudents = expelledStudents;
    }

    /**
     * Метод, устанавливающий значение shouldBeExpelled
     * @param shouldBeExpelled студенты, которых стоит отчислить
     */
    public void setShouldBeExpelled(int shouldBeExpelled) {
        this.shouldBeExpelled = shouldBeExpelled;
    }

    /**
     * Метод, устанавливающий значение semesterEnum
     * @param semesterEnum семестр
     */

    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    /**
     * Метод, устанавливающий значение groupAdmin
     * @param groupAdmin админ группы
     */
    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    /**
     * Метод, возвращающий строковое представление StudyGroup
     * @return string value of StudyGroup object
     */
    @Override
    public String toString() {
        String idS = "id = " + id;
        String nameS = "name = " + name;
        String coordinatesS = "coordinates = " + coordinates.toString();
        String creationDateS = "creationDate = " + creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String studentsCountS = "studentsCount = " + studentsCount;
        String expelledStudentsS = "expelledStudents = " + expelledStudents;
        String shouldBeExpelledS = "shouldBeExpelled = " + shouldBeExpelled;
        String semesterEnumS = "semesterEnum = " + semesterEnum;
        String groupAdminS = "groupAdmin: " + groupAdmin.toString();
        return idS + '\n' + nameS + '\n' + coordinatesS + '\n' + creationDateS + '\n' + studentsCountS + '\n' +
                expelledStudentsS + '\n' + shouldBeExpelledS + '\n' + semesterEnumS + '\n' + groupAdminS;
    }

    /**
     * Метод, сравнивающий объект StudyGroup с другим объектом того же класса
     * @param o2 объект для сравнения
     * @return 1; 0; -1 в зависимости от их отношения друг к другу
     */
    @Override
    public int compareTo(StudyGroup o2) {
        if (this.getStudentsCount() - this.getExpelledStudents() - this.getShouldBeExpelled() * 0.5 > o2.getStudentsCount() - o2.getExpelledStudents() - o2.getShouldBeExpelled() * 0.5) {
            return 1;
        } else if (this.getStudentsCount() - this.getExpelledStudents() - this.getShouldBeExpelled() * 0.5 < o2.getStudentsCount() - o2.getExpelledStudents() - o2.getShouldBeExpelled() * 0.5) {
            return -1;
        } else if (this.getName().compareTo(o2.getName()) > 0) {
            return 1;
        } else if (this.getName().compareTo(o2.getName()) < 0) {
            return -1;
        } else if (this.getSemesterEnum().compareTo(o2.getSemesterEnum()) > 0) {
            return 1;
        } else if (this.getSemesterEnum().compareTo(o2.getSemesterEnum()) < 0) {
            return -1;
        } else if (this.getCreationDate().compareTo(o2.getCreationDate()) > 0) {
            return 1;
        } else if (this.getCreationDate().compareTo(o2.getCreationDate()) < 0) {
            return -1;
        } else if (this.getCoordinates().compareTo(o2.getCoordinates()) > 0) {
            return 1;
        } else if (this.getCoordinates().compareTo(o2.getCoordinates()) < 0) {
            return -1;
        } else if (this.getGroupAdmin().compareTo(o2.getGroupAdmin()) > 0) {
            return 1;
        } else if (this.getGroupAdmin().compareTo(o2.getGroupAdmin()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}

