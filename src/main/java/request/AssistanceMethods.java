package request;

import io.cucumber.datatable.DataTable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssistanceMethods {

    private static final Logger logger = LoggerFactory.getLogger(AssistanceMethods.class);


    public Iterator<Map<String, String>> convertDataTableToIterator(DataTable dt) {
        List<Map<String, String>> listOfMap = dt.entries();
        return listOfMap.iterator();
    }

    public JSONObject test(DataTable dataTable) {
//        JSONObject obj = new JSONObject();
//        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> subscription : data) {
//            obj = new JSONObject(subscription);
//
//
//
//        }
        JSONObject jsonObject = new JSONObject();
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            for (Map.Entry<String, String> entry : row.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                // Check if the value is a boolean
                if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                    jsonObject.put(key, Boolean.parseBoolean(value));
                }
                // Check if the value is an integer
                else {
                    try {
                        jsonObject.put(key, Integer.parseInt(value));
                    } catch (NumberFormatException e1) {
                        // Try parsing the value as a double
                        try {
                            jsonObject.put(key, Double.parseDouble(value));
                        } catch (NumberFormatException e2) {
                            // Value is not an integer, double, or boolean, add it as a string
                            jsonObject.put(key, value);
                        }
                    }
                }
            }
        }
        return jsonObject;
    }

        public JSONObject convertDataTableToJson(DataTable dataTable) {
        JSONObject jsonObject = new JSONObject();
        JSONObject nested;
        JSONArray jsonArray;
        Iterator<Map<String, String>> iterator = convertDataTableToIterator(dataTable);
        while (iterator.hasNext()) {
            Map<String, String> row = iterator.next();
            for (Map.Entry<String, String> pair : row.entrySet()) {
                if (pair.getValue() != null) {
                    if (isNested(pair.getValue()) && !isArray(pair.getValue())) {
                        nested = convertStringToJsonObj(pair.getValue());
                        jsonObject.put(pair.getKey(), nested);
                    } else if (isArray(pair.getValue())) {
                        jsonArray = convertStringToJsonArray(pair.getValue());
                        jsonObject.put(pair.getKey(), jsonArray);
                    } else {
                        // Check if the value is an integer or double
                        try {
                            int intValue = Integer.parseInt(pair.getValue());
                            jsonObject.put(pair.getKey(), intValue);
                        } catch (NumberFormatException e1) {
                            try {
                                double doubleValue = Double.parseDouble(pair.getValue());
                                jsonObject.put(pair.getKey(), doubleValue);
                            } catch (NumberFormatException e2) {
                                // Check if the value is a boolean
                                if ("true".equalsIgnoreCase(pair.getValue()) || "false".equalsIgnoreCase(pair.getValue())) {
                                    boolean booleanValue = Boolean.parseBoolean(pair.getValue());
                                    jsonObject.put(pair.getKey(), booleanValue);
                                } else {
                                    jsonObject.put(pair.getKey(), pair.getValue());
                                }
                            }
                        }
                    }
                }
            }
        }
        return jsonObject;

    }

    public JSONObject convertStringToJsonObj(String jsonString) {
        JSONObject jsonObject = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            jsonObject = (JSONObject) parser.parse(jsonString);
        } catch (ParseException e) {
            // Log the exception instead of printing the stack trace
            logger.error("Error parsing JSON string: " + e.getMessage(), e);        }
        return jsonObject;
    }
    public JSONArray convertStringToJsonArray(String jsonString) {

        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            jsonArray = (JSONArray) parser.parse(jsonString);
        } catch (ParseException e) {
            logger.error("Error parsing ARRAY string: " + e.getMessage(), e);
        }
        return jsonArray;
    }

    private boolean isNested(String key) {
        return (key.contains("{"));
    }

    private boolean isArray(String key) {
        return (key.charAt(0) == '[');
    }

}
