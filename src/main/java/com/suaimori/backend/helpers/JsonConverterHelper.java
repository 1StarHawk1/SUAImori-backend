package com.suaimori.backend.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonConverterHelper {
    public static String convertTupleListToJson(List<Tuple> tuples) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = new HashMap<>();

        for (Tuple tuple : tuples) {
            for (TupleElement<?> element : tuple.getElements()) {
                jsonMap.put(element.getAlias(), tuple.get(element.getAlias()));
            }
        }

        String json = "";
        try {
            json = mapper.writeValueAsString(jsonMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }
}
