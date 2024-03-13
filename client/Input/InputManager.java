package Input;

import CommonClasses.Entities.*;


/**
 * ��������� ��� ���������� ������
 */
public interface InputManager {

    /**
     * ������ ������ �� �������
     * @return ������
     */
    String readLine();

    default String readLine(String prefix){
        System.out.print(prefix);
        return readLine();
    }

    /**
     * ������ ���������� �� �������
     * @return ����������
     */
    Coordinates readCoordinates();


    /**
     * ������ Furnish �� �������
     * @return ����������� Furnish
     */
    Furnish readFurnish();

    /**
     * ������ View �� �������
     * @return ����������� View
     */
     View readView();

    /**
     * ������ Transport �� �������
     * @return ����������� Transport
     */

     Transport readTransport();

    /**
     * ������ House �� �������
     * @return ����������� House
     */
    House readHouse();

    /**
     * ������ Flat �� �������
     * @return ����������� Flat
     */
     Flat readFlat();

}
