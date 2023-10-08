package com.example.base;

import java.io.*;
import java.nio.file.Files;
import java.util.logging.Logger;

/**
 * @author xingce
 * @date 2023/7/5 15:34
 */
public class SerializableDemo implements Serializable {
    private static final long serialVersionUID = 605312238500484354L;

    private static final String fileName = "demo.txt";

    private static final Logger LOGGER = Logger.getLogger("");

    private static final String staticVariable = "this is a static variable";
    private final int intVariable = 1;

    final transient private String transientVariable = "this is a transient instance field";

    private Thread threadClass;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableDemo demo = new SerializableDemo();
        writeOut(demo);
        System.out.println("SerializableDemo to be saved:" + demo);

        System.out.println();

        System.out.println("DemoClass deserialized: " + readIn());
    }

    private static void writeOut(SerializableDemo demo) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(new File(fileName).toPath()));
        oos.writeObject(demo);
        oos.close();
    }

    @Override
    public String toString() {
        return "\nfinal static fileName=" + fileName + "\nfinal static logger=" + LOGGER
                + "\nnon-final static staticVariable=" + staticVariable + "\ninstance intVariable=" + intVariable
                + "\ntransient instance transientVariable=" + transientVariable + "\nnon-serializable instance field " +
                "threadClass:=" + threadClass;
    }

    private static Object readIn() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(new File(fileName).toPath()));
        return ois.readObject();
    }
}
