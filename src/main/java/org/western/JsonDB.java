package org.western;

import java.io.FileReader;
import java.util.Objects;

import com.google.gson.*;

public class JsonDB {
    private JsonObject data;
    public JsonDB(String field, String value) {
        int i;
        JsonObject j;
        Gson g = new Gson();
        if(field.equals("user") || field.equals("poi")) {
            try (FileReader f = new FileReader(Objects.requireNonNull(getClass().getResource("/org/western/db/" + field + ".json")).getFile())) { // new file reader
                j = JsonParser.parseReader(f).getAsJsonObject(); // parse json file
                if(field.equals("user"))
                {
                    i = j.get("data").getAsJsonObject().get(value).getAsInt();
                    if(i < j.get("count").getAsInt()) { // check if user exists
                        try (FileReader r = new FileReader(Objects.requireNonNull(getClass().getResource("/org/western/db/" + i + ".json")).getFile())) { // new file reader
                            User u = g.fromJson(r, User.class); // convert to json element
                            data = g.toJsonTree(u).getAsJsonObject(); // convert to json object
                        }
                    } else {
                        data = JsonParser.parseString("{\"status\": 500, \"message\": \"Internal Error\"}").getAsJsonObject(); // internal error
                    }
                } else {
                    POI p = g.fromJson(f, POI.class); // convert to json element
                    data = g.toJsonTree(p).getAsJsonObject(); // convert to json object
                }
            } catch (Exception e) {
                data = JsonParser.parseString("{\"status\": 404, \"message\": \"Not Found\"}").getAsJsonObject(); // not found
                e.printStackTrace();
            }
        } else {
            data = JsonParser.parseString("{\"status\": 400, \"message\": \"Invalid field\"}").getAsJsonObject(); // invalid field
        }
    }
    public JsonObject getData() {
        return data;
    }
    public static void main(String[] args) {
        JsonDB j = new JsonDB("user", "admin");
        System.out.println(j.getData().toString());
    }
}
