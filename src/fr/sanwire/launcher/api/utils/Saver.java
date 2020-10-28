package fr.sanwire.launcher.api.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {

    private String file;

    private final JsonObject fileCategories = new JsonObject();

    public Saver(String file) {
        this.file = file;
        if (!this.file.endsWith(".json")) this.file += ".json";
    }

    public void put(String key, String value){
        this.fileCategories.addProperty(key, value);
    }

    public void save(){
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(fileCategories.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getInfosFromJsonFile(String file, String info){
        JsonParser parser = new JsonParser();

        if (!file.endsWith(".json")) file += ".json";

        try (FileReader reader = new FileReader(file)) {
            JsonObject object = (JsonObject) parser.parse(reader);

            return String.valueOf(object.get(info)).replaceAll("\"", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }
}
