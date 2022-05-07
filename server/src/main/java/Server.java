import commands.CommandManger;
import exceptions.NumberOutOfBoundsException;
import exceptions.WrongAmountOfCoordinatesException;
import utils.Message;
import utils.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Server {
    private final static int PORT = 4531;
    private static DatagramSocket socket;
    private static byte[] buf = new byte[2048];
    private static boolean running;
    private static StudyGroupCollection studyGroupCollection;
    private static ParserCSV parserCSV;
    private static Logger logger;

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException, NumberOutOfBoundsException, WrongAmountOfCoordinatesException {
        String path = "";
        try {
            logger = LogManager.getLogger("server");
            path = System.getenv("FILE_PATH");
            File file = new File(path);
            socket = new DatagramSocket(PORT);
            CommandManger.setCommands();
            logger.info("Установили команды, которые может исполнить сервер.");
            parserCSV = new ParserCSV(logger);
            studyGroupCollection = new StudyGroupCollection(parserCSV, file);
            logger.info("Инициализировали коллекцию по значениям из файла");
            logger.info("Сервер начинает работу");
            run();
        }
        catch (SocketException e){}
        catch (FileNotFoundException e){
            if (e.getMessage().contains("(Отказано в доступе)"))
                logger.fatal(e.getMessage() + ", получите права на чтения файла или запустите программу с другим файлом");
            else logger.fatal("Файл " + path + "Не найден");
        }
    }

    public static void run() throws NumberOutOfBoundsException, WrongAmountOfCoordinatesException {
        running = true;
        while (running){
            try {
                ReceiveManager receiveManager = new ReceiveManager(socket, logger);
                logger.info("receiveManager инициализирован");
                Message received = receiveManager.receiveMessage();

                InetAddress clientAddress = receiveManager.getAddress();
                int clientPort = receiveManager.getPort();
                logger.info("Получен порт и адрес клиента");

                Response response = received.getCommand().execute(received.getParam(), received.getStudyGroup(), studyGroupCollection);
                logger.info("Получен результат выполнения команды");

                SendManager sendManager = new SendManager(socket, clientAddress, clientPort);
                sendManager.sendResponse(response);

            }
            catch (IOException e){}
        }
    }
}
