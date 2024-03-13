package Managers;

import CollectionWrappers.MyCollection;
import CommonClasses.Entities.*;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedList;

public final class JsonManager {
    static String filePath;


    /**
     * ����� ��� ���������� ��������� � JSON-����
     */
    private static class MyCollectionAdapter{
        LinkedList<FlatParams> list;
        public Date creationTime;
        /**
         * ����������� ��� �������� ������� �� ������� ������ Managers.CollectionManager
         * @param manager - ������ ������ Managers.CollectionManager
         */
        public MyCollectionAdapter(MyCollection manager) {
            this.list = new LinkedList<>();
            this.creationTime = Date.from(manager.creationDate.toInstant());
            for (Flat f :
                    manager.getList()) {
                list.add(new FlatParams(f));
            }
        }
    }


    /**
     * ����� ��� ���������� ��������� � JSON-����
     */
    private static class FlatParams{
        public String name; //���� �� ����� ���� null, ������ �� ����� ���� ������
        public Coordinates coordinates; //���� �� ����� ���� null
        public Date creationDate;
        public Integer area; //������������ �������� ����: 745, �������� ���� ������ ���� ������ 0
        public Long numberOfRooms; //�������� ���� ������ ���� ������ 0
        public Furnish furnish; //���� ����� ���� null
        public View view; //���� �� ����� ���� null
        public Transport transport; //���� �� ����� ���� null
        public House house; //���� �� ����� ���� null


        /**
         * ����������� ��� �������� ������� �� ������� ������ Flat
         * @param copyFrom - ������ ������ Flat
         */
        public FlatParams(Flat copyFrom) {
            this.name = copyFrom.getName();
            this.creationDate = Date.from(copyFrom.getCreationDate().toInstant());
            this.coordinates = copyFrom.getCoordinates();
            this.transport=copyFrom.getTransport();
            this.area = copyFrom.getArea();
            this.numberOfRooms = copyFrom.getNumberOfRooms();
            this.furnish = copyFrom.getFurnish();
            this.view = copyFrom.getView();
            this.house = copyFrom.getHouse();
        }
    }


    /**
     * ������������� ���� � �����
     * @param filePath1 - ���� � �����
     */
    public static void setFilePath(String filePath1){
        filePath = filePath1;
    }


    public static void dump(MyCollection collection) throws FileNotFoundException {
        MyCollectionAdapter managerAdapter = new MyCollectionAdapter(collection);
        OutputStreamWriter writer = null;
        Gson g = new Gson();
        String file = g.toJson(managerAdapter);
        try {
            writer = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");
            writer.write(file);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("����������� ����");
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("�������� ���� �� ������");
        } catch (IOException e) {
            throw new RuntimeException("�� ���������� ��������� ��������� � JSON-����");
        }
        finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * ��������� ��������� �� JSON-�����
     * @return - �������� ���������
     * @throws IllegalArgumentException - ���� ���� �� ������
     */
    public static MyCollection load() throws IllegalArgumentException, IOException {

        StringBuilder lines = new StringBuilder();
        ZonedDateTime resultTime;
        File file = new File(filePath);
        if (!file.exists()) Files.createFile(Path.of(filePath));

        BufferedReader reader = Files.newBufferedReader(Path.of(filePath));
            for (; ; ) {
                String line = reader.readLine();
                if (line == null)
                    break;
                lines.append(line.replace("\n",""));
            }
        MyCollectionAdapter adapter= new Gson().fromJson(lines.toString(),MyCollectionAdapter.class);
        LinkedList<Flat> list = new LinkedList<>();
        long currentId=0;
        for (int i=0;i<adapter.list.size();i++) {
            try {
                FlatParams transferObject = adapter.list.get(i);
                Flat f = new Flat();
                f.setName(transferObject.name);
                f.setCreationDate(ZonedDateTime.ofInstant(transferObject.creationDate.toInstant(),ZoneId.systemDefault()));
                f.setCoordinates(transferObject.coordinates);
                f.setArea(transferObject.area);
                f.setNumberOfRooms(transferObject.numberOfRooms);
                f.setView(transferObject.view);
                f.setFurnish(transferObject.furnish);
                f.setHouse(transferObject.house);
                f.setTransport(transferObject.transport);
                f.setId(currentId);
                currentId++;
                list.add(f);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("� �������� ��������� ����� �������� ������ � ������� "+ i + ":" + e.getMessage());
            }
        }
        return new MyCollection(list,ZonedDateTime.ofInstant(adapter.creationTime.toInstant(),ZoneId.systemDefault()));


    }


}
