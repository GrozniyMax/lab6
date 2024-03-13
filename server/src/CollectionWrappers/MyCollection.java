package CollectionWrappers;

import CommonClasses.Entities.Flat;

import java.time.ZonedDateTime;
import java.util.LinkedList;


/**
 * �����-������� ���������
 */
public class MyCollection {
    private LinkedList<Flat> list;
    public final ZonedDateTime creationDate;


    /**
     * �����������
     * @param list - ���������
     * @param creationDate - ���� ��������
     */
    public MyCollection(LinkedList<Flat> list, ZonedDateTime creationDate) {
        this.list = list;
        this.creationDate = creationDate;
    }

    /**
     * ���������� ���������
     * @return ���������
     */
    public LinkedList<Flat> getList() {
        return list;
    }

    public void setList(LinkedList<Flat> list) {
        this.list = list;
    }
}
