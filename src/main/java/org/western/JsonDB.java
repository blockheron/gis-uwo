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
            try (FileReader f = new FileReader(Objects.requireNonNull(getClass().getResource("db/" + field + ".json")).getFile())) { // new file reader
                j = JsonParser.parseReader(f).getAsJsonObject(); // parse json file
                if(field.equals("user"))
                {
                    if(j.get("data") != null && j.get("data").getAsJsonObject().get(value) != null) {
                        i = j.get("data").getAsJsonObject().get(value).getAsInt();
                        if(i <= j.get("count").getAsInt()) { // check if user exists
                            try (FileReader r = new FileReader(Objects.requireNonNull(getClass().getResource("db/u-" + i + ".json")).getFile())) { // new file reader
                                User u = g.fromJson(r, User.class); // convert to json element
                                data = g.toJsonTree(u).getAsJsonObject(); // convert to json object
                            }
                            catch (Exception e) {
                                data = JsonParser.parseString("{\"status\": 500, \"message\": \"Internal Error\"}").getAsJsonObject(); // internal error
                            }
                        }
                    } else {
                        data = JsonParser.parseString("{\"status\": 404, \"message\": \"Not Found\"}").getAsJsonObject(); // not found
                    }
                } else {
                    if(j.get("data") != null && j.get("data").getAsJsonObject().get(value) != null) {
                        i = j.get("data").getAsJsonObject().get(value).getAsInt();
                        if(i <= j.get("count").getAsInt()) { // check if poi exists
                            System.out.println(getClass().getResource("db/p-" + i + ".json"));
                            try (FileReader r = new FileReader(Objects.requireNonNull(getClass().getResource("db/p-" + i + ".json")).getFile())) { // new file reader
                                // parse from reader
                                j = JsonParser.parseReader(r).getAsJsonObject();
                                if(j.get("data") != null) {
                                    JsonArray dataArr = j.get("data").getAsJsonArray(), result = new JsonArray();
                                    for(JsonElement e : dataArr) {
                                        POI p = g.fromJson(e, POI.class);
                                        result.add(g.toJsonTree(p).getAsJsonObject());
                                    }
                                    data = new JsonObject();
                                    data.add("data", result);
                                } else {
                                    data = JsonParser.parseString("{\"status\": 403, \"message\": \"Forbidden\"}").getAsJsonObject(); // forbidden
                                }
                            }
                            catch (Exception e) {
                                data = JsonParser.parseString("{\"status\": 500, \"message\": \"Internal Error\"}").getAsJsonObject(); // internal error
                            }
                        }
                    } else {
                        data = JsonParser.parseString("{\"status\": 404, \"message\": \"Not Found\"}").getAsJsonObject(); // not found
                    }
                }
            } catch (Exception e) {
                data = JsonParser.parseString("{\"status\": 204, \"message\": \"No Content\"}").getAsJsonObject(); // not found
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
        JsonDB j = new JsonDB("poi", "mc");
        System.out.println(j.getData().toString());
    }
}
