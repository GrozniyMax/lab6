package Managers;

import CommonClasses.Exceptions.FunctionFailedException;
import CommonClasses.Exceptions.InvalidInputException;
import Input.ScriptInputManager;
import Managers.CommandManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class ScriptExecutor {
    CommandManager previousCommandManager;

    public ScriptExecutor(CommandManager previousCommandManager) {
        this.previousCommandManager = previousCommandManager;
    }

    static Set<String> executedFiles = new HashSet<>();

    public void execute(String filepath) {
        File scriptFile = new File(filepath.strip());

        if (executedFiles.contains(scriptFile.getAbsolutePath())){
            System.err.println("�� ������� ����������� ��������. ������� ���������� ������������");
            return;
        }
        executedFiles.add(scriptFile.getAbsolutePath());
        CommandManager scriptCommandManager = null;
        try {
            scriptCommandManager = this.previousCommandManager.inheritate(new ScriptInputManager(new FileInputStream(scriptFile)));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("������������ ���� � �����");
        }
        boolean noExceptionsThrown = true;
        boolean exit = false;
        while (!exit){
            try {
                exit = scriptCommandManager.handle();
            }catch (InvalidInputException e){
                noExceptionsThrown = false;
                System.err.println("������ � ���������� �������: "+e.getMessage());
                exit = true;
            }
        }
        if (noExceptionsThrown) System.out.printf("������ %s �������� �������\n",scriptFile.getName());
        executedFiles.remove(scriptFile.getAbsolutePath());
    }

}
