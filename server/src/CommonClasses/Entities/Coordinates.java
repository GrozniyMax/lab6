package CommonClasses.Entities;

import java.io.Serializable;

/**
 * ����� ��� �������� ������� ���� Coordinates
 */
public class Coordinates implements Serializable {

    /**
     * ���� x - ���������� x
     */
    private Integer x; //������������ �������� ����: 606, ���� �� ����� ���� null
    /**
     * ���� y - ���������� y
     */
    private Float y; //���� �� ����� ���� null

    public Coordinates() {
    }
    /**
     * ������������� �������� ���� x
     * @param x - �������� ���� x
     * @throws IllegalArgumentException - ���� �������� ���� x �����������
     */
    public void setX(Integer x) throws IllegalArgumentException {
        if ((x==null)||(x>606)) throw new IllegalArgumentException("������������ �������� Coordinates.x");
        this.x = x;
    }
    /**
     * ������������� �������� ���� y
     * @param y - �������� ���� y
     * @throws IllegalArgumentException - ���� �������� ���� y �����������
     */
    public void setY(Float y) throws IllegalArgumentException {
        if (y==null) throw new IllegalArgumentException("������������ �������� Coordinates.y");
        this.y = y;
    }

    /**
     * ���������� ��������� ������������� ������� Coordinates
     * @return ��������� ������������� ������� Coordinates
     */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x.toString() +
                ", y=" + y.toString() +
                '}';
    }
}
