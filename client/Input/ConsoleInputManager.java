package Input;

import java.io.IOException;
import java.util.InputMismatchException;

import CommonClasses.Entities.*;
import CommonClasses.Exceptions.InvalidInputException;

/**
 * ����� ��� ���������� ������ �� �������
 * ��������� ��������� InputManager
 */
public class ConsoleInputManager extends BaseInputManager implements InputManager {

    private static ConsoleInputManager instance;

    /**
     * �����������
     */
    private ConsoleInputManager() {
        super(System.in);
    }

    /**
     * �������� ��������� ������
     * @return ConsoleInputManager
     */
    public static ConsoleInputManager getInstance(){
        if (instance==null){
            instance = new ConsoleInputManager();
        }
        return instance;
    }

    /**
     * ������ ���������� �� �������
     * @return Coordinates - ���������� ����������
     * @throws InvalidInputException ���� ������������ ����
     */
    public Coordinates readCoordinates() throws InvalidInputException{
        try {
            System.out.print("������� ���������� � ������� x y : ");

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
     * @throws InvalidInputException ���� ������������ ����
     */
    public Furnish readFurnish() throws InvalidInputException{
        try {
            System.out.print("������� Furish (DESIGNER, NONE, LITTLE): ");
            return Furnish.valueOf(this.readLine().strip().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("�� ���������� ���������� enum");
        }
    }

    /**
     * ������ View �� �������
     * @return View - ���������� View
     * @throws InvalidInputException ���� ������������ ����
     */
    public View readView()throws InvalidInputException{
        try {
            System.out.print("������� View (STREET, YARD, PARK, BAD, GOOD): ");
            return View.valueOf(this.readLine().strip().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("�� ���������� ���������� enum");
        }
    }


    /**
     * ������ House �� ������� � ������� ������
     * @return House - ���������� House
     * @throws InvalidInputException ���� ������������ ����
     */
    public House readHouse(){
        House object = new House();
        this.doUntillCorrect(()->object.setName(this.readLine("������� ��� ����: ")),
                ()->object.setYear(this.readInt("������� ��� ���������: ")),
                ()->object.setNumberOfFloors(this.readInt("������� ���������� ������ ����: "))
        );
        return object;
    }


    /**
     * ������ Transport �� �������
     * @return Transport - ���������� Transport
     * @throws InvalidInputException ���� ������������ ����
     */
    public Transport readTransport() throws InvalidInputException{
        try {
            System.out.print("������� Transport (NONE, LITTLE, NORMAL, ENOUGH): ");
            return Transport.valueOf(this.readLine().strip().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }


    /**
     * ������ Flat �� �������
     * @return Flat - ���������� Flat
     */
    public Flat readFlat() {
        Flat object = new Flat();
        this.doUntillCorrect(()->object.setName(this.readLine("������� ���: ").strip()),
                ()->object.setCoordinates(this.readCoordinates()),
                ()->object.setArea(this.readInt("������� �������: ")),
                ()->object.setNumberOfRooms(this.readLong("������� ���������� ������: ")),
                ()->object.setFurnish(this.readFurnish()),
                ()->object.setView(this.readView()),
                ()->object.setTransport(this.readTransport()),
                ()->object.setHouse(this.readHouse())
        );
        return object;
    }



    /**
     * ��������� �������� ���� �� ����� ������� ���������� ��������
     * @param runnable ��������
     */
    public void doUntillCorrect(Runnable runnable){
        while (true){
            try {
                runnable.run();
                break;
            }catch (InvalidInputException|IllegalArgumentException e){
                System.err.println("������������ ����: "+e.getMessage());
            }
        }
    }

    /**
     * ��������� �������� ���� �� ����� ������� ���������� ��������
     * @param runnables ��������
     */
    public void doUntillCorrect(Runnable ... runnables){
        for (Runnable runnable:runnables){
            doUntillCorrect(runnable);
        }
    }

}
