

import CommonClasses.Exceptions.InvalidInputException;
import CommonClasses.Exceptions.EndOfStreamException;
import Input.ConsoleInputManager;
import Managers.CommandManager;
import Managers.UDPManager;

import java.io.FileNotFoundException;
import java.net.PortUnreachableException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    /**
     * Точка входа в программу
     * @param args - аргументы командной строки
     */
    public static void main(String[] args) {
        boolean exit = false;

        CommandManager commandManager = null;
        try {
            commandManager = new CommandManager(ConsoleInputManager.getInstance(), new UDPManager());
        } catch (RuntimeException e) {
            if (e.getCause().getClass().equals(PortUnreachableException.class)) {
                System.out.println("Сервер недоступен. Попробуйте позже");
                exit = true;
            }
        }

        while (!exit) {
            try {
                exit = commandManager.handle();
            } catch (InvalidInputException e) {
                System.err.println(e.getMessage());
            } catch (EndOfStreamException e) {
                exit = true;
            } catch (RuntimeException e) {
                if (e.getCause().getClass().equals(PortUnreachableException.class)) {
                    System.out.println("Сервер недоступен. Попробуйте позже");
                    exit = true;
                }
            }
        }
        CommandManager finalCommandManager = commandManager;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {if (finalCommandManager !=null){
        finalCommandManager.finish();}
        }));

    }

}