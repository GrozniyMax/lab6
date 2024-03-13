package CommonClasses.Utils;

import java.io.*;

public class IOUtils {

    static final int PACKET_SIZE=1024;

    public static byte[] toByteArray(Object object){
        try(var array = new ByteArrayOutputStream()) {
            new ObjectOutputStream(array).writeObject(object);
            return array.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T fromByteArray(byte[] array){
        try(var reader = new ObjectInputStream(new ByteArrayInputStream(array));) {
            return (T) reader.readObject();
        } catch (IOException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
