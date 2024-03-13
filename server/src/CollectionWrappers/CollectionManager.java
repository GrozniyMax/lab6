package CollectionWrappers;

import CommonClasses.Entities.Flat;

import java.util.ArrayList;
import java.util.List;


/**
 * �����, ����������� ����������
 */
public class CollectionManager {


    MyCollection collection;
    private final String[] history = new String[5];
    private int historyIndex = 0;

    /**
     * �����������
     * @param collection - ���������
     */
    public CollectionManager(MyCollection collection) {
        this.collection = collection;
    }

    /**
     * ���������� ������� ������
     * @return ������� ������
     */
    public List<String> getHistory() {
        List<String> copy = new ArrayList<>();
        for (int i = historyIndex; i < historyIndex+5; i++) {
            if (history[i%5]!=null) {
                copy.add(history[i%5]);
            }
        }
        return copy;
    }
    /**
     * ��������� ������� � �������
     * @param command - ��� �������
     */
    public void addHistory(String command){
        history[historyIndex] = command;
        historyIndex = (historyIndex+1)%5;
    }


    /**
     * ���������� �����-������� ���������
     * @return ���������
     */
    public MyCollection getCollection() {
        return collection;
    }


    /**
     * @return ��������� ��� ������-�������
     */
    public List<Flat> getList(){
        return this.collection.getList();
    }
}
