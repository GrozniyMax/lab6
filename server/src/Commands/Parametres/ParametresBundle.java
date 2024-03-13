package Commands.Parametres;

import CollectionWrappers.CollectionManager;
import Commands.Command;
import CommonClasses.Commands.UserData;

import java.util.Collection;

/**
 * Класс, хранящий в себе все параметры, необходимые для выполнения команды
 */
public record ParametresBundle(CollectionManager collectionManager, UserData data,Collection<Command> commands) {
    //TODO решить в каком формате передаются аргументы пользователя

    public ParametresBundle(Collection<Command> commands) {
        this(null, null, commands);
    }

    public ParametresBundle(CollectionManager collectionManager) {
        this(collectionManager, null, null);
    }

    public ParametresBundle(CollectionManager collectionManager, UserData date) {
        this(collectionManager, date, null);
    }

    public ParametresBundle() {
        this(null,null,null);
    }
}
