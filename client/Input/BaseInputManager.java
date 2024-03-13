package Input;

import CommonClasses.Exceptions.*;


import java.io.*;

/**
 * ������� ����� ��� ���������� ������/�������
 */
public abstract class BaseInputManager {

    protected BufferedReader reader;

    /**
     * �����������
     * @param readFrom - ����� �����
     */
    protected BaseInputManager(InputStream readFrom) {
        this.reader = new BufferedReader(new InputStreamReader(readFrom));
    }


    /**
     * ������ ������ �� �������
     * @return ������
     * @throws EndOfStreamException ���� ����� ������
     */
    public String readLine() throws EndOfStreamException{
        try {
            String line = reader.readLine();
            if (line==null) throw new EndOfStreamException();
            return line;
        } catch (IOException e) {
            throw new RuntimeException("������ ������ �������� ������, ��� ����������� ������");
        }
    }
    /**
     * ������ ������ �� ������� �������� �������
     * @param prefix �������
     * @return ������
     * @throws EndOfStreamException ���� ����� ������
     */
    public String readLine(String prefix) throws EndOfStreamException {
        System.out.print(prefix);
        return this.readLine();
    }

    /**
     * ������ ����� ����� �� �������
     * @return Integer - ���������� �����
     * @throws InvalidInputException ���� ������������ ����
     */
    public Integer readInt() throws InvalidInputException {
        try {
            return Integer.parseInt(this.readLine().strip());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("������������ ���� ������ ����� ������� Integer");
        }
    }
    /**
     * ������ ����� ����� �� ������� �������� �������
     * @param prefix - �������
     * @return Integer - ���������� �����
     * @throws InvalidInputException ���� ������������ ����
     */
    public Integer readInt(String prefix) throws InvalidInputException {
        System.out.print(prefix);
        return this.readInt();
    }

    /**
     * ������ ������� ����� �� �������
     * @return Long - ���������� �����
     * @throws InvalidInputException ���� ������������ ����
     */
    public Long readLong() throws InvalidInputException {
        try {
            return Long.parseLong(this.readLine().strip());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("������������ ������� ����� ������� Long");
        }
    }
    /**
     * ������ ������� ����� �� ������� �������� �������
     * @param prefix - �������
     * @return Long - ���������� �����
     * @throws InvalidInputException ���� ������������ ����
     */
    public Long readLong(String prefix) throws InvalidInputException {
        System.out.print(prefix);
        return this.readLong();
    }
    /**
     * ������ ������� ����� �� �������
     * @return Float - ���������� �����
     * @throws InvalidInputException ���� ������������ ����
     */
    public Float readFloat() throws InvalidInputException {
        try {
            return Float.parseFloat(this.readLine().strip());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("������������ ������� ����� ������� Float");
        }
    }
    /**
     * ������ ������� ����� �� ������� �������� �������
     * @param prefix - �������
     * @return Float - ���������� �����
     * @throws InvalidInputException ���� ������������ ����
     */
    public Float readFloat(String prefix) throws InvalidInputException {
        System.out.print(prefix);
        return readFloat();
    }
}
