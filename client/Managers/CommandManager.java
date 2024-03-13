package Managers;

import CommonClasses.Commands.CommandDescription;
import CommonClasses.Commands.UserData;
import CommonClasses.Entities.Flat;
import CommonClasses.Exceptions.*;
import CommonClasses.Interaction.ServerResponse;
import CommonClasses.Interaction.UserRequest;
import Input.InputManager;

import java.util.HashMap;


/**
 * ����� ��� ���������� ���������
 */
public class CommandManager {
    private InputManager inputManager;
    private UDPManager connectionManager;

    private final HashMap<String,CommandDescription> commands = new HashMap<>();


    /**
     * �����������
     * @param inputManager - �������� �����
     */
    public CommandManager(InputManager inputManager, UDPManager connectionManager) {
        this.inputManager = inputManager;
        this.connectionManager = connectionManager;

        connectionManager.getCommands().stream().forEach((command)->commands.put(command.comandName(),command));
        System.out.println("���������������� �������");
        System.out.println(commands.keySet());
    }
    public CommandManager(){

    }
    public CommandManager inheritate(InputManager inputManager){
        CommandManager result = new CommandManager(inputManager,this.connectionManager);
        return result;
    }

    /**
     * ������������ �������
     * @return true ���� ����� ��������� ������
     * @throws FunctionFailedException ���� ������� ����������� � �������
     */
    public boolean handle() throws InvalidInputException  {
        try {
            String line = inputManager.readLine("#");
            String[] splitted = line.strip().split(" +");

            CommandDescription currentCommand = null;
            for (String commandName :
                    commands.keySet()) {
                if (commandName.equals(splitted[0])) {
                    currentCommand = commands.get(commandName);
                    break;
                }
            }
            if (currentCommand == null){
                throw new InvalidInputException("������� �� ����������");
            }
            if (currentCommand.equals("execute_script")){
                ScriptExecutor scriptExecutor = new ScriptExecutor(this);
                scriptExecutor.execute(splitted[1]);
            }

            UserRequest  userRequest = null;
            switch (currentCommand.userParams()){
                case NONE -> userRequest = new UserRequest(currentCommand);
                case ARGUMENT -> {
                    Object argument = currentCommand.argumentParseFunction().apply(splitted[1]);
                    argument = argument.getClass().cast(argument);
                    UserData data = new UserData(argument,null);
                    userRequest = new UserRequest(currentCommand,data);
                }
                case OBJECT -> {
                    Flat object = inputManager.readFlat();
                    UserData data = new UserData(null,object);
                    userRequest = new UserRequest(currentCommand, data);
                }
                case BOTH -> {
                    Object argument = currentCommand.argumentParseFunction().apply(splitted[1]);
                    argument = argument.getClass().cast(argument);
                    Flat object = inputManager.readFlat();
                    UserData data = new UserData(argument,object);
                    userRequest = new UserRequest(currentCommand, data);
                }
            }
            connectionManager.sendUserRequest(userRequest);
            if (currentCommand.comandName().equals("exit")) return true;
            ServerResponse response = connectionManager.getServerResponce();
            print(response);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("������ ������������ �������� �������. "+e.getMessage());
        } catch (EndOfStreamException e){
            return true;
        } catch (InvalidInputException e){
            throw new InvalidInputException("������ �����. "+e.getMessage());
        }catch (IndexOutOfBoundsException e){
            throw  new InvalidInputException("������ �����. �� ������ �������� �������");
        }

        return false;
    }

    private void print(ServerResponse response){
        if (response.status()){
            System.out.println("������ ������� ��������");
            if (response.output()!=null) response.output().stream().forEach(System.out::println);
        }else {
            System.out.println("������ � ���������� ������� "+ response.exception().getMessage());
        }
    }

    public void finish(){
            connectionManager.sendUserRequest(new UserRequest(commands.get("exit")));
    }
}
