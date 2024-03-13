package Main;

import CollectionWrappers.CollectionManager;
import CollectionWrappers.MyCollection;
import CommonClasses.Entities.Flat;
import Managers.CommandManager;
import Managers.ConnectionManger;
import Managers.JsonManager;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    public static Logger logger = Logger.getLogger("ServerLogger");
    /**
     * Точка входа в программу
     * @param args - аргументы командной строки
     */
    public static void main(String[] args) {
        System.setErr(System.out);
        logger.info("Server started");
        String fileName = null;
        try {
            fileName = Objects.requireNonNull(System.getenv("collectionFileName"));

        } catch (Throwable e){
            logger.warning("Enviromental variable not found. ~/dumping.json will be used");
            fileName = "dumping.json";
        }

        //Приводим путь к абсолютному виду
        fileName = FileSystems.getDefault().getPath(fileName).normalize().toAbsolutePath().toString();
        JsonManager.setFilePath(fileName);
        MyCollection collection=new MyCollection(new LinkedList<>(), ZonedDateTime.now());
        try {
             collection = JsonManager.load();
        } catch (IllegalArgumentException e) {
            logger.warning("Found mistake in json-file");
        }
        catch (Throwable e){
            logger.warning("Unable to read json-file");
        }
        finally {
            ConnectionManger manger = new ConnectionManger(new CommandManager(new CollectionManager(collection)));
            manger.run();
            MyCollection finalCollection = collection;
            Runtime.getRuntime().addShutdownHook(new Thread(
                    ()-> {
                        try {
                            logger.info("Server finishes working");
                            JsonManager.dump(finalCollection);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
            ));
        }
    }



}