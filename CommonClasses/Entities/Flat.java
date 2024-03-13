package CommonClasses.Entities;



import CommonClasses.Utils.IDManager;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * ����� ��� �������� ������� ���� Flat
 */
public class Flat implements Comparable<Flat>, Serializable {

    private Long id; //���� �� ����� ���� null, �������� ���� ������ ���� ������ 0, �������� ����� ���� ������ ���� ����������, �������� ����� ���� ������ �������������� �������������
    private String name; //���� �� ����� ���� null, ������ �� ����� ���� ������
    private Coordinates coordinates; //���� �� ����� ���� null
    private ZonedDateTime creationDate; //���� �� ����� ���� null, �������� ����� ���� ������ �������������� �������������
    private Integer area; //������������ �������� ����: 745, �������� ���� ������ ���� ������ 0
    private Long numberOfRooms; //�������� ���� ������ ���� ������ 0
    private Furnish furnish; //���� ����� ���� null
    private View view; //���� �� ����� ���� null
    private Transport transport; //���� �� ����� ���� null
    private House house; //���� �� ����� ���� null
    {
        //���� ������������� �������
        this.id = IDManager.getID();
        this.creationDate =  ZonedDateTime.now();
    }

    /**
     * ������ �����������
     */
    public Flat() {
    }

    /**
     * ������������� �������� ���� id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * ������������� �������� ���� name
     * @param name
     * @throws IllegalArgumentException - ���� �������� ���� name �����������
     */
    public void setName(String name) throws IllegalArgumentException {
        if ((name==null)||name.equals("")) throw new IllegalArgumentException("��� �� ������ ���� ������");
        this.name = name;
    }

    /**
     * ������������� �������� ���� creationDate
     * @param creationDate
     * @throws IllegalArgumentException - ���� �������� ���� creationDate �����������
     */
    public void setCreationDate(ZonedDateTime creationDate)throws IllegalArgumentException {
        if (creationDate==null) throw new IllegalArgumentException("���� �� ������ ���� ������");
        this.creationDate = creationDate;
    }

    /**
     * ������������� �������� ���� coordinates
     * @param coordinates
     * @throws IllegalArgumentException - ���� �������� ���� coordinates �����������
     */
    public void setCoordinates(Coordinates coordinates) throws IllegalArgumentException {
        if (coordinates==null) throw new IllegalArgumentException("���������� �� ������ ���� �������");
        this.coordinates = coordinates;
    }
    /**
     * ������������� �������� ���� area
     * @param area
     * @throws IllegalArgumentException - ���� �������� ���� area �����������
     */
    public void setArea(Integer area) throws IllegalArgumentException {
        if ((area==null)||(area<0)||(area>745)) throw new IllegalArgumentException("������������ �������� Flat.area");
        this.area = area;
    }
    /**
     * ������������� �������� ���� numberOfRooms
     * @param numberOfRooms
     * @throws IllegalArgumentException - ���� �������� ���� numberOfRooms �����������
     */
    public void setNumberOfRooms(Long numberOfRooms) throws IllegalArgumentException {
        if ((numberOfRooms==null)||(numberOfRooms<0)) throw new IllegalArgumentException("������������ �������� Flat.numberOfRooms");
        this.numberOfRooms = numberOfRooms;
    }
    /**
     * ������������� �������� ���� furnish
     * @param furnish
     * @throws IllegalArgumentException - ���� �������� ���� furnish �����������
     */
    public void setFurnish(Furnish furnish) throws IllegalArgumentException {
        if (furnish==null) throw new IllegalArgumentException("���� �� ������ ���� ������");
        this.furnish = furnish;
    }
    /**
     * ������������� �������� ���� view
     * @param view
     * @throws IllegalArgumentException - ���� �������� ���� view �����������
     */
    public void setView(View view) throws IllegalArgumentException {
        if (view==null) throw new IllegalArgumentException("���� �� ������ ���� ������");
        this.view = view;
    }
    /**
     * ������������� �������� ���� transport
     * @param transport
     * @throws IllegalArgumentException - ���� �������� ���� transport �����������
     */
    public void setTransport(Transport transport) throws IllegalArgumentException {
        if (transport==null) throw new IllegalArgumentException("���� �� ������ ���� ������");
        this.transport = transport;
    }
    /**
     * ������������� �������� ���� house
     * @param house
     * @throws IllegalArgumentException - ���� �������� ���� house �����������
     */
    public void setHouse(House house) throws IllegalArgumentException {
        if (house==null) throw new IllegalArgumentException("���� �� ������ ���� ������");
        this.house = house;
    }

    /**
     * ���������� ���� ��������� ��������
     * @return �������� ���������
     */
    @Override
    public int compareTo(Flat o) {
        if (o==null) return 1;
        return area.compareTo(o.area);
    }

    /**
     * ��������� id �������
     * @return - id �������
     */
    public Long getId() {
        return id;
    }

    /**
     * ���������� creationDate �������
     * @return creationDate �������
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * ���������� furnish �������
     * @return furnish �������
     */
    public Furnish getFurnish() {
        return furnish;
    }

    /**
     * ���������� view �������
     * @return view �������
     */
    public View getView() {
        return view;
    }
    /**
     * ���������� name �������
     * @return name �������
     */
    public String getName() {
        return name;
    }
    /**
     * ���������� coordinates �������
     * @return coordinates �������
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }
    /**
     * ���������� area �������
     * @return area �������
     */
    public Integer getArea() {
        return area;
    }
    /**
     * ���������� numberOfRooms �������
     * @return numberOfRooms �������
     */
    public Long getNumberOfRooms() {
        return numberOfRooms;
    }
    /**
     * ���������� transport �������
     * @return transport �������
     */
    public Transport getTransport() {
        return transport;
    }
    /**
     * ���������� house �������
     * @return house �������
     */
    public House getHouse() {
        return house;
    }

    /**
     * ���������� ��������� ������������� ������� Flat
     * @return ��������� ������������� ������� Flat
     */
    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate.toString() +
                ", area=" + area +
                ", numberOfRooms=" + numberOfRooms +
                ", furnish=" + furnish.toString() +
                ", view=" + view.toString() +
                ", transport=" + transport.toString() +
                ", house=" + house.toString() +
                '}';
    }
}