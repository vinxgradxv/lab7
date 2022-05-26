package utils;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Predicate;

public class Asker {

    IOManager ioManager;

    public Asker(IOManager ioManager){
        this.ioManager = ioManager;
    }

    public <T> T ask(Function<String, T> function,
                       Predicate<T> predicate,
                       String message,
                       String negativeResponse,
                       boolean nullable) throws IOException {

        T value = null;
        while (true){
            ioManager.println(message);
            ioManager.prompt();
            try {
                String input = ioManager.readLine();
                if (input.equals("") && nullable) {
                    return null;
                }
                value = function.apply(input);
                if (predicate.test(value)){
                    return value;
                }
                else {
                    ioManager.printerr(negativeResponse);
                }
            }catch (IllegalArgumentException e){
                ioManager.printerr("Значение неверного формата");
            }
            catch (NullPointerException e){
                ioManager.printerr("Значение не может быть null");
            }
        }

    }
}
