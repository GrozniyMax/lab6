package Commands;

import Commands.Parametres.ParametresBundle;
import Commands.Parametres.ServerParams;
import CommonClasses.Commands.CommandDescription;
import CommonClasses.Commands.UserParams;
import Exceptions.ExitCommandException;

import java.util.List;

/**
 * ����� ������� ���������� ���������
 */
public class Exit extends BaseCommand {
    /**
     * ����������� ������ �������
     * @see BaseCommand
     */
    public Exit() {
        super("exit",
                "��������� ���������� ���������");
    }


    /**
     * @see Command#getRequiredParametres()
     */
    @Override
    public ServerParams getRequiredParametres() {
        return ServerParams.NONE;
    }

    /**
     * @see Command#execute(ParametresBundle) ()
     */
    @Override
    public List<String> execute(ParametresBundle parametresBundle) throws ExitCommandException{
        throw new ExitCommandException();
    }

    @Override
    public CommandDescription getCommandDescription() {
        return new CommandDescription(name,
                UserParams.NONE);
    }
}
