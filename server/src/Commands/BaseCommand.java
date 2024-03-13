package Commands;

import Commands.Parametres.Description;

/**
 * ������� ����� �������
 * @author ������ ���������
 */
public abstract class BaseCommand implements Command {



    /**
     * �������� �������
     */
    protected final String description;
    /**
     * �������� �������
     */
    protected final String name;


    /**
     * @param name
     * @param description
     */
    protected BaseCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * �������� �������� �������
     * @return - �������� �������
     */
    public String getDescription() {
        return description;
    }

    /**
     * �������� �������� �������
     * @return - �������� �������
     */
    public String getName() {
        return name;
    }

}
