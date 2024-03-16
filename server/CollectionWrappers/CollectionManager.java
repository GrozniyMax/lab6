package CollectionWrappers;

import CommonClasses.Entities.Flat;

import java.util.ArrayList;
import java.util.List;


/**
 * Класс для управления коллекцией
 * отвечает за историю взаимодействия с коллекцией
 */
public class CollectionManager {

    MyCollection collection;
    private final String[] history = new String[5];
    private int historyIndex = 0;

    /**
     * Конструктор
     * @param collection - коллекция
     */
    public CollectionManager(MyCollection collection) {
        this.collection = collection;
    }

    /**
     * Метод для получения истории
     * @return
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
     * Метод для добавления команды в историю
     * @param command - команда
     */
    public void addHistory(String command){
        history[historyIndex] = command;
        historyIndex = (historyIndex+1)%5;
    }


    /**
     * Метод для получения коллекции
     * @return
     */
    public MyCollection getCollection() {
        return collection;
    }


    /**
     * Метод для получения списка элементов коллекции
     * @return
     */
    public List<Flat> getList(){
        return this.collection.getList();
    }
}
