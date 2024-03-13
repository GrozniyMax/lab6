package Commands;

import Commands.Parametres.Description;
import Commands.Parametres.ParametresBundle;
import Commands.Parametres.ServerParams;
import CommonClasses.Exceptions.FunctionFailedException;
import Exceptions.ExitCommandException;

import java.io.Serializable;
import java.util.List;

public interface Command extends Description {
    /**
     * @return ��� �������
     */
     String getName();

    /**
     * @return �������� �������
     */
     String getDescription();

     ServerParams getRequiredParametres();

    /**
     * ���������� �������
     *
     * @param parametresBundle ����� ����������
     * @param arguments
     */
     List<String> execute(ParametresBundle arguments) throws FunctionFailedException, ExitCommandException;



}
