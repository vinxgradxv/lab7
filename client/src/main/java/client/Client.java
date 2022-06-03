package client;

import commands.CommandManger;
import commands.LogInCommand;
import commands.SignUpCommand;
import data.User;
import exceptions.NullValueException;
import exceptions.NumberOutOfBoundsException;
import utils.*;

import java.io.*;
import java.net.*;

public class Client {
    private final static int PORT = 5432;
    private static IOManager ioManager;
    private static DatagramSocket socket;
    private static InetAddress address;
    private static RequestMaker requestMaker;
    private static CommandManger commandManger = new CommandManger();
    private static SendManager sendManager;
    private static ReceiveManager receiveManager;
    private static User currentUser = null;

    private static byte[] buf;

    public Client() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(System.out, true);
            ioManager = new IOManager(reader, writer, "$");
            socket = new DatagramSocket();
            socket.setSoTimeout(15 * 1000);
            address = InetAddress.getByName("localhost");
            sendManager = new SendManager(address, socket, PORT);
            requestMaker = new RequestMaker();
            receiveManager = new ReceiveManager(socket);
            CommandManger.setCommands();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Response logInUser(String login, String password){
        try {
            Message message = new Message(new LogInCommand(), null, null, new User(login, password));
            sendManager.sendMessage(message);
            Response response = receiveManager.receiveMessage();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response signUpUser(String login, String password){
        try {
            Message message = new Message(new SignUpCommand(), null, null, new User(login, password));
            sendManager.sendMessage(message);
            Response response = receiveManager.receiveMessage();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void run() throws IOException, NumberOutOfBoundsException, NullValueException, ClassNotFoundException {
        boolean running = true;
        String input = "";
        while (running){
            try {
                ioManager.prompt();
                if (ioManager.getFileMode()) {
                    input = ioManager.readFile();
                }
                if (!ioManager.getFileMode()) {
                    input = ioManager.readLine();
                }
                Message message = requestMaker.getCommandFromInput(input, commandManger, ioManager);
                if (message != null) {
                    sendManager.sendMessage(message);
                    Response response = receiveManager.receiveMessage();
                    if (response.getType() == ResponseType.USER){
                        currentUser = response.getUser();
                        ioManager.println("sasat");
                        ioManager.println(response.getMessage());
                    } else
                    if (response.getType() == ResponseType.SCRIPT) {
                        ioManager.turnOnFileMode(response.getMessage());
                    } else if (response.getType() == ResponseType.EXIT) {
                        ioManager.println(response.getMessage());
                        ioManager.println("Работа клиента прекращена");
                        running = false;
                    } else if (response.getType() == ResponseType.ERROR) {
                        ioManager.printerr(response.getMessage());
                    } else {
                        ioManager.println(response.getMessage());
                    }
                }

            }catch (NullPointerException e){
                ioManager.printerr("Такой команды нет");
            }catch (SocketTimeoutException e){
                ioManager.printerr("Не удалось связаться с сервером, попробуйте ввести команду еще раз");
            }
        }
    }

    public static User getCurrentUser(){
        return currentUser;
    }



}
