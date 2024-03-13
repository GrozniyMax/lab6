package CommonClasses.Entities;

import java.io.Serializable;

/**
 * ����� ��� �������� ������� ���� House
 */
public class House implements Serializable {

    private String name; //���� ����� ���� null
    private Integer year; //���� �� ����� ���� null, �������� ���� ������ ���� ������ 0
    private int numberOfFloors; //�������� ���� ������ ���� ������ 0
    /**
     * ������������� �������� ���� name
     * @param name - �������� ���� name
     * @throws IllegalArgumentException - ���� �������� ���� name �����������
     */
    public void setName(String name) throws IllegalArgumentException {
        if ((name==null)||(name.equals(""))) throw new IllegalArgumentException("������������ �������� House.name. ��� �� ������ ���� ������");
        this.name = name;
    }
    /**
     * ������������� �������� ���� year
     * @param year - �������� ���� year
     * @throws IllegalArgumentException - ���� �������� ���� year �����������
     */
    public void setYear(Integer year) throws IllegalArgumentException {
        if ((year==null)||(year<0)) throw new IllegalArgumentException("������������ �������� House.year");
        this.year = year;
    }
    /**
     * ������������� �������� ���� numberOfFloors
     * @param numberOfFloors - �������� ���� numberOfFloors
     * @throws IllegalArgumentException - ���� �������� ���� numberOfFloors �����������
     */
    public void setNumberOfFloors(int numberOfFloors) throws IllegalArgumentException {
        if (numberOfFloors < 0) throw new IllegalArgumentException("������������ �������� House.numberOfFloors");
        this.numberOfFloors = numberOfFloors;
    }
    /**
     * ���������� ��������� ������������� ������� House
     * @return ��������� ������������� ������� House
     */
    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", numberOfFloors=" + numberOfFloors +
                '}';
    }
}
