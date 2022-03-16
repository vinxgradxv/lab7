package data;

/**
 * Класс, описывающий координаты
 */
public class Coordinates implements Comparable<Coordinates> {
    /**
     * Координата x. Максимальное значение поля: 722, Поле не может быть null
     */
    private Long x;
    /**
     * Координата y. Максимальное значение поля: 102, Поле не может быть null
     */
    private Long y;

    /**
     * Конструктор класса
     * @param x
     * @param y
     */
    public Coordinates(Long x, Long y){
        this.x = x;
        this.y = y;
    }

    /**
     * Метод, устанавливающий значение координаты x
     * @param x
     */
    public void setX(Long x) {
        this.x = x;
    }

    /**
     * Метод, устанавливающий значение координаты y
     * @param y
     */
    public void setY(Long y) {
        this.y = y;
    }

    /**
     * Метод, возвращающий значение координаты x
     * @return x
     */
    public Long getX() {
        return x;
    }

    /**
     * Метод, возвращающий значение координаты y
     * @return y
     */
    public Long getY() {
        return y;
    }

    /**
     * Метод, возвращающий строковое представление объекта класса Coordinates
     * @return string value of Coordinates object
     */
    @Override
    public String toString() {
        return String.valueOf(x) + " " + String.valueOf(y);
    }

    /**
     * Метод, возвращающий объект класса Coordinates
     * @param s строковое представление объекта
     * @return объект класса Coordinates
     */
    public static Coordinates valueOf(String s){
        String[] values = s.split(" ");
        return new Coordinates(Long.valueOf(values[0]), Long.valueOf(values[1]));
    }

    /**
     * Метод, сравнивающий 2 объекта класса Coordinates
     * @param o2
     * @return 1; 0; -1 в зависимости от отношения объектов друг к другу
     */
    public int compareTo(Coordinates o2){
        if (this.getX() * this.getX() + this.getY()*this.getY() > o2.getX()*o2.getX()+o2.getY()*o2.getY()){
            return 1;
        }
        else if(this.getX() * this.getX() + this.getY()*this.getY() < o2.getX()*o2.getX()+o2.getY()*o2.getY()){
            return -1;
        }
        else {return 0;}
    }

}
