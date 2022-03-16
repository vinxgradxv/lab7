package data;

/**
 * Класс, описывающий человека
 */
public class Person implements Comparable<Person>{
    /**
     *
     *  Имя человека. Поле не может быть null, Строка не может быть пустой
     */
    private String name;
    /**
     * Рост человека. Поле может быть null, Значение поля должно быть больше 0
     */
    private Long height;
    /**
     * Цвет волос человека. Поле не может быть null
     */
    private Color hairColor;
    /**
     * Национальность человека. Поле может быть null
     */
    private Country nationality;
    /**
     * Локация человека. Поле может быть null
     */
    private Location location;


    /**
     * Конструктор
     * @param name имя
     * @param height рост
     * @param hairColor цвет волос
     * @param nationality национальность
     * @param location локация
     */
    public Person(String name, Long height, Color hairColor, Country nationality, Location location){
        this.name = name;
        this.height = height;
        this. hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    /**
     * Метод, возвращающий строковое представление объекта Person
     * @return string value of Person object
     */
    @Override
    public String toString() {
        String nameS = "name = " + name;
        String heightS = "height = " + height;
        String hairColorS = "hairColor = " + hairColor;
        String nationalityS = "nationality = " + nationality;
        String locationS = "location = " + location;
        return nameS + '\n' + heightS + '\n' + hairColorS + '\n' + nationalityS + '\n' + locationS;
    }

    /**
     * Метод, возвращающий имя человека
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий рост человека
     * @return height
     */
    public Long getHeight() {
        return height;
    }

    /**
     * Метод, возвращающий национальность человека
     * @return nationality
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * Метод, возвращающий локацию человека
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Метод, возвращающий цвет волос человека
     * @return hairColor
     */
    public Color getHairColor() {
        return hairColor;
    }

    /**
     * Метод, сравнивающий 2 объекта класса Coordinates
     * @param o2 объект для сравнения
     * @return 1; 0; -1 в зависимости от отношения объектов друг к другу
     */
    @Override
    public int compareTo(Person o2) {
        if (this.getHeight() > o2.getHeight()){
            return 1;
        }
        else if(this.getHeight() < o2.getHeight()){
            return -1;
        }
        else if(this.getName().compareTo(o2.getName()) > 0){
            return 1;
        }
        else if(this.getName().compareTo(o2.getName()) < 0){
            return -1;
        }
        else if(this.getHairColor().compareTo(o2.getHairColor()) > 0){
            return 1;
        }
        else if(this.getHairColor().compareTo(o2.getHairColor()) < 0){
            return -1;
        }
        else if(this.getNationality().compareTo(o2.getNationality()) > 0){
            return 1;
        }
        else if(this.getNationality().compareTo(o2.getNationality()) < 0){
            return -1;
        }
        else if(this.getLocation().compareTo(o2.getLocation()) > 0){
            return 1;
        }
        else if(this.getLocation().compareTo(o2.getLocation()) < 0){
            return -1;
        }
        else{
            return 0;
        }
    }
}
