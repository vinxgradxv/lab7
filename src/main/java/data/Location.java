package data;

/**
 * Класс, описывающий локацию
 */
public class Location implements Comparable<Location>{
    /**
     * Координата x. Поле не может быть null
     */
    private Double x;
    /**
     * Координата y. Поле не может быть null
     */
    private Double y;
    /**
     * Координата z
     */
    private double z;

    /**
     * Конструктор без параметров
     */
    public Location(){}

    /**
     * Конструктор со всеми необходимыми параметрами
     * @param x Координата
     * @param y Координата
     * @param z Координата
     */
    public Location(Double x, Double y, double z){
        this.x = x;
        this.y=  y;
        this.z = z;
    }

    /**
     * Метод, возвращающий объект класса Location
     * @param value строковое представление объекта Location
     * @return Location object
     */
    public static Location valueOf(String value){
        String[] input = value.split(" ");
        Double x = Double.valueOf(input[0]);
        Double y = Double.valueOf(input[1]);
        double z = Double.valueOf(input[2]);
        return new Location(x, y, z);
    }

    /**
     * Метод, возвращающий координату x
     * @return x
     */
    public Double getX() {
        return x;
    }

    /**
     * Метод, возвращающий координату y
     * @return y
     */
    public Double getY() {
        return y;
    }

    /**
     * Метод, возвращающий координату z
     * @return z
     */
    public double getZ() {
        return z;
    }

    /**
     * Метод, устанавливающий значение координаты x
     * @param x координата
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * Метод, устанавливающий значение координаты y
     * @param y координата
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * Метод, устанавливающий значение координаты z
     * @param z координата
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Метод, возвращающий строковое представление объекта класса Location
     * @return string value of Location object
     */
    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }
    /**
     * Метод, сравнивающий 2 объекта класса Coordinates
     * @param o объект для сравнения
     * @return 1; 0; -1 в зависимости от отношения объектов друг к другу
     */
    @Override
    public int compareTo(Location o) {
        if(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2) > Math.pow(o.x,2)+Math.pow(o.y,2)+Math.pow(o.z,2)){
            return 1;
        }
        else if(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2) < Math.pow(o.x,2)+Math.pow(o.y,2)+Math.pow(o.z,2)){
            return -1;
        }
        else {
            return 0;
        }
    }
}
