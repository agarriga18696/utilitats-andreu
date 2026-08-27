package io.github.agarriga18696.andreuutils.core;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for working with text and binary files.
 *
 * @author Andreu
 * @version 1.6
 */
public final class FileUtils {

    private FileUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // TEXT FILES - READING
    //-------------------------------

    /**
     * Reads the entire contents of a text file.
     *
     * @param path Path to the file.
     * @return The contents of the file, or {@code null} if an error occurs.
     */
    public static String readAllText(String path) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }

            return content.toString();
        } catch (IOException e) {
            MessageUtils.error("Could not read the file: " + path);
            return null;
        }
    }

    /**
     * Reads all lines from a text file.
     *
     * @param path Path to the file.
     * @return An array containing the lines, or an empty array if an error occurs.
     */
    public static String[] readLines(String path) {
        int lineCount = countLines(path);

        if (lineCount <= 0) {
            return new String[0];
        }

        String[] lines = new String[lineCount];
        int index = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines[index++] = line;
            }
        } catch (IOException e) {
            MessageUtils.error("Could not read the file: " + path);
        }

        return lines;
    }

    /**
     * Reads a CSV file and returns its contents as a two-dimensional array.
     *
     * @param path      Path to the file.
     * @param separator Separator used between fields, usually {@code ";"} or {@code ","}.
     * @return A two-dimensional array containing the rows and fields.
     */
    public static String[][] readCsv(String path, String separator) {
        String[] lines = readLines(path);
        String[][] fields = new String[lines.length][];

        for (int index = 0; index < lines.length; index++) {
            fields[index] = lines[index].split(separator);
        }

        return fields;
    }

    /**
     * Counts the number of lines in a text file.
     *
     * @param path Path to the file.
     * @return The number of lines, or {@code -1} if an error occurs.
     */
    public static int countLines(String path) {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.readLine() != null) {
                count++;
            }

            return count;
        } catch (IOException e) {
            MessageUtils.error("Could not read the file: " + path);
            return -1;
        }
    }

    //-------------------------------
    // TEXT FILES - WRITING
    //-------------------------------

    /**
     * Writes text to a file.
     *
     * @param path   Path to the file.
     * @param text   Text to write.
     * @param append {@code true} to append to the file,
     *               or {@code false} to overwrite it.
     * @return {@code true} if the text was written successfully.
     */
    public static boolean write(String path, String text, boolean append) {
        try (FileWriter writer = new FileWriter(path, append)) {
            writer.write(text + System.lineSeparator());
            return true;
        } catch (IOException e) {
            MessageUtils.error("Could not write to the file: " + path);
            return false;
        }
    }

    /**
     * Writes multiple lines to a text file.
     *
     * @param path   Path to the file.
     * @param lines  Lines to write.
     * @param append {@code true} to append to the file,
     *               or {@code false} to overwrite it.
     * @return {@code true} if the lines were written successfully.
     */
    public static boolean writeLines(String path, String[] lines, boolean append) {
        try (FileWriter writer = new FileWriter(path, append)) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }

            return true;
        } catch (IOException e) {
            MessageUtils.error("Could not write to the file: " + path);
            return false;
        }
    }

    /**
     * Writes an array of objects to a text file in CSV format.
     * The objects must implement {@link CsvSerializable}.
     *
     * @param path      Path to the file.
     * @param objects   Objects to write.
     * @param separator Separator used between fields.
     * @param append    {@code true} to append to the file,
     *                  or {@code false} to overwrite it.
     * @param <T>       Type of the objects.
     * @return {@code true} if the objects were written successfully.
     */
    public static <T extends CsvSerializable> boolean writeObjectsToCsv(
            String path,
            T[] objects,
            String separator,
            boolean append
    ) {
        String[] lines = new String[objects.length];

        for (int index = 0; index < lines.length; index++) {
            lines[index] = objects[index].toCsv(separator);
        }

        return writeLines(path, lines, append);
    }

    //-------------------------------
    // BINARY FILES
    //-------------------------------

    /**
     * Writes an array of serializable objects to a binary file.
     *
     * @param path    Path to the file.
     * @param objects Objects to write.
     * @param <T>     Type of the objects.
     * @return {@code true} if the objects were written successfully.
     */
    public static <T> boolean writeObjects(String path, T[] objects) {
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(path))) {

            for (T object : objects) {
                output.writeObject(object);
            }

            return true;
        } catch (IOException e) {
            MessageUtils.error("Could not save the file: " + path);
            return false;
        }
    }

    /**
     * Writes a collection of serializable objects to a binary file.
     *
     * @param path    Path to the file.
     * @param objects Objects to write.
     * @param <T>     Type of the objects.
     * @return {@code true} if the objects were written successfully.
     */
    public static <T> boolean writeObjects(
            String path,
            Collection<T> objects
    ) {
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(path))) {

            for (T object : objects) {
                output.writeObject(object);
            }

            return true;
        } catch (IOException e) {
            MessageUtils.error("Could not save the file: " + path);
            return false;
        }
    }

    /**
     * Writes a map of serializable objects to a binary file.
     *
     * @param path Path to the file.
     * @param map  Map to write.
     * @param <K>  Type of the keys.
     * @param <V>  Type of the values.
     * @return {@code true} if the map was written successfully.
     */
    public static <K, V> boolean writeMap(String path, Map<K, V> map) {
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(path))) {

            output.writeObject(map);
            return true;
        } catch (IOException e) {
            MessageUtils.error("Could not save the file: " + path);
            return false;
        }
    }

    /**
     * Reads all objects from a binary file and returns them as an array.
     *
     * @param path Path to the file.
     * @param type Class of the objects, for example {@code Person.class}.
     * @param <T>  Type of the objects.
     * @return An array containing the objects,
     * or an empty array if an error occurs.
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] readObjects(String path, Class<T> type) {
        int objectCount = countObjects(path);

        if (objectCount <= 0) {
            return (T[]) Array.newInstance(type, 0);
        }

        T[] objects = (T[]) Array.newInstance(type, objectCount);

        File file = new File(path);

        if (!file.exists()) {
            return objects;
        }

        int index = 0;

        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream(path))) {

            while (true) {
                try {
                    objects[index++] = (T) input.readObject();
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            MessageUtils.error("Could not read the file: " + path);
            return (T[]) Array.newInstance(type, 0);
        }

        return objects;
    }

    /**
     * Reads all objects from a binary file and returns them as a list.
     *
     * @param path Path to the file.
     * @param <T>  Type of the objects.
     * @return A list containing the objects,
     * or an empty list if an error occurs.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> readObjects(String path) {
        List<T> objects = new ArrayList<>();

        File file = new File(path);

        if (!file.exists()) {
            return objects;
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream(path))) {

            while (true) {
                try {
                    objects.add((T) input.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            MessageUtils.error("Could not read the file: " + path);
        }

        return objects;
    }

    /**
     * Reads a map from a binary file.
     *
     * @param path Path to the file.
     * @param <K>  Type of the keys.
     * @param <V>  Type of the values.
     * @return The loaded map, or an empty map if an error occurs.
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> readMap(String path) {
        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream(path))) {

            return (Map<K, V>) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            MessageUtils.error("Could not read the file: " + path);
            return new HashMap<>();
        }
    }

    /**
     * Counts the number of objects stored in a binary file.
     *
     * @param path Path to the file.
     * @return The number of objects, or {@code -1} if an error occurs.
     */
    public static int countObjects(String path) {
        int count = 0;

        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream(path))) {

            while (true) {
                try {
                    input.readObject();
                    count++;
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    MessageUtils.error("Class not found in the file: " + path);
                    return -1;
                }
            }
        } catch (IOException e) {
            MessageUtils.error("Could not read the file: " + path);
            return -1;
        }

        return count;
    }

    //-------------------------------
    // FILE MANAGEMENT
    //-------------------------------

    /**
     * Checks whether a file or directory exists.
     *
     * @param path Path to check.
     * @return {@code true} if the path exists.
     */
    public static boolean exists(String path) {
        return new File(path).exists();
    }

    /**
     * Deletes a file.
     *
     * @param path Path to the file.
     * @return {@code true} if the file was deleted successfully.
     */
    public static boolean delete(String path) {
        File file = new File(path);

        if (file.exists()) {
            return file.delete();
        }

        MessageUtils.warning("The file does not exist: " + path);
        return false;
    }

    /**
     * Creates an empty file if it does not already exist.
     *
     * @param path Path to the file.
     * @return {@code true} if the file was created or already existed.
     */
    public static boolean createFileIfAbsent(String path) {
        File file = new File(path);

        if (file.exists()) {
            return true;
        }

        try {
            return file.createNewFile();
        } catch (IOException e) {
            MessageUtils.error("Could not create the file: " + path);
            return false;
        }
    }

    /**
     * Creates a directory and any missing parent directories if necessary.
     *
     * @param path Path to the directory.
     * @return {@code true} if the directory was created or already existed.
     */
    public static boolean createDirectoriesIfAbsent(String path) {
        File directory = new File(path);

        if (directory.exists()) {
            return true;
        }

        return directory.mkdirs();
    }

}