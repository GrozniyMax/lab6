package Input;

import CommonClasses.Entities.*;
import CommonClasses.Exceptions.EndOfStreamException;
import CommonClasses.Exceptions.InvalidInputException;

import java.io.InputStream;

/**
 * ����� ��� ���������� ������ �� �������
 * ��������� ��������� InputManager
 */
public class ScriptInputManager extends BaseInputManager implements InputManager{

    /**
     * �����������
     * @param readFrom - ����� �����
     */
    public ScriptInputManager(InputStream readFrom) {
        super(readFrom);
    }


    /**
     * ������ ���������� �� �������
     * @return Coordinates - ���������� ����������
     * @throws EndOfStreamException ���� ����� ������
     */
    public Coordinates readCoordinates() throws InvalidInputException{
        try {
            String[] values = this.readLine().strip().split(" +");
            if (!(values.length ==2)) throw new IllegalArgumentException(" ���������� ������� ����� 2 ���������");
            Coordinates coordinates = new Coordinates();
            coordinates.setX(Integer.parseInt(values[0]));
            coordinates.setY(Float.parseFloat(values[1]));
            return coordinates;
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    /**
     * ������ Furnish �� �������
     * @return Furnish - ���������� Furnish
     * @throws EndOfStreamException ���� ����� ������
     */
    public Furnish readFurnish() throws InvalidInputException{
        try {
            return Furnish.valueOf(this.readLine().strip().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }


    /**
     * ������ View �� �������
     * @return View - ���������� View
     * @throws InvalidInputException ���� ������������ ����
     */
    public View readView() throws InvalidInputException{
        try {
            return View.valueOf(this.readLine().strip().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    /**
     * ������ House �� �������
     * @return House - ���������� House
     * @throws InvalidInputException ���� ������������ ����
     */
    public House readHouse()throws InvalidInputException{
        try {
            House object = new House();
            object.setName(this.readLine());
            object.setYear(this.readInt());
            object.setNumberOfFloors(this.readInt());
            return object;
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    /**
     * ������ Transport �� �������
     * @return ����������� Transport
     * @throws InvalidInputException ���� ������������ ����
     */
    public Transport readTransport() throws InvalidInputException{
        try {
            return Transport.valueOf(this.readLine().strip().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    /**
     * ������ Flat �� �������
     * @return ����������� Flat
     * @throws InvalidInputException ���� ������������ ����
     */
    public Flat readFlat() throws InvalidInputException{
        try {
            Flat object = new Flat();
            object.setName(this.readLine());
            object.setCoordinates(this.readCoordinates());
            object.setArea(this.readInt());
            object.setNumberOfRooms(this.readLong());
            object.setFurnish(this.readFurnish());
            object.setView(this.readView());
            object.setTransport(this.readTransport());
            object.setHouse(this.readHouse());
            return object;
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        } catch (EndOfStreamException e){
            throw new InvalidInputException("���� ���������");
        }
    }

}
