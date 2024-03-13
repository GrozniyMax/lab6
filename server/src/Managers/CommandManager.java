package Managers;

import CollectionWrappers.CollectionManager;

import Commands.*;
import Commands.Parametres.ParametresBundle;
import CommonClasses.Commands.CommandDescription;
import CommonClasses.Exceptions.FunctionFailedException;
import CommonClasses.Interaction.ServerResponse;
import CommonClasses.Interaction.UserRequest;
import Exceptions.ExitCommandException;
import Main.Main;


import java.util.*;

import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * ����� ��� ���������� ���������
 */
public class CommandManager {
    CollectionManager collectionManager;
    static final Logger logger = Main.logger;


    private Map<CommandDescription, Command> commands = new HashMap<>();

    {
        //���� ������������� ����� ��������� ������ ������
        register(new Add());
//        register(new Clear());
//        register(new CountGreaterThanFurish());
//        register(new ExecuteScript());
        register(new Exit());
//        register(new GroupCountingByCreationDate());
//        register(new Help());
//        register(new History());
//        register(new Info());
//        register(new RemoveAllByView());
//        register(new RemoveById());
//        register(new RemoveFirst());
//        register(new RemoveLower());
//        register(new Save());
        register(new Show());
//        register(new Update());
    }

    /**
     * ������������ �������
     * @param newCommand - �������
     */
    public void register(Command newCommand){
        commands.put(newCommand.getCommandDescription(),newCommand);
    }


    /**
     * �����������
     *
     * @param collectionManager - �������� ���������
     */
    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * �����������
     * @param collectionManager - �������� ���������
     * @param commands - ������ ������
     */
    public CommandManager(CollectionManager collectionManager, Map<CommandDescription, Command> commands) {
        this.collectionManager = collectionManager;
        this.commands = commands;
    }

    public LinkedList<CommandDescription> getCommands(){
        return new LinkedList<>(commands.keySet());
    }

    /**
     * ������������ �������
     * @return true ���� ����� ��������� ������
     * @throws FunctionFailedException ���� ������� ����������� � �������
     */
    public ServerResponse handle(UserRequest request) throws ExitCommandException {
        Command current = commands.get(request.command());
        ServerResponse response = new ServerResponse(true);
        try {
            logger.info("Started executing of command: "+
                    current.getName());
            ParametresBundle arguments = null;

            switch (current.getRequiredParametres()) {
                case �OMMANDS -> arguments = new ParametresBundle(commands.values());
                case COLLECTION_MANAGER -> arguments = new ParametresBundle(collectionManager);
                case COLLECTION_MANAGER_AND_USER -> arguments = new ParametresBundle(collectionManager, request.data());
                case NONE -> arguments = new ParametresBundle();
            }
            List<String> result= current.execute(arguments);
//            response = new ServerResponse(result==null?new LinkedList<String>():result);
            response = result!=null?new ServerResponse(result):response;
            return response;
        }catch (FunctionFailedException e){
            logger.warning("Execution of command: "+
                    current.getName()+" failed");
            response = new ServerResponse(e);
            return response;
        }
    }
}
