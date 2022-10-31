package com.example.avmed.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

@Slf4j
public class ObjectUtil {

   private  ObjectUtil() {

   }

   public static <T> T jsonToObject(String json, Class<T> valueType) {

     try {
         return new ObjectMapper().readValue(json, valueType);
     } catch (JsonProcessingException ex) {
        ex.printStackTrace();
        log.error("jsonToObject: {}", ex.getMessage());
     }
        return null;
   }

    public static String objectToJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            log.error("objectToJson: ", ex.getMessage());
        }
        return null;
    }

    public static byte[] createEventCompressed(String message) {
        // BufferedWriter zipWriter = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(message.length())) {
            try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
                gzipOutputStream.write(message.getBytes(StandardCharsets.UTF_8));
                gzipOutputStream.close();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Failed to zip content", e);
        }
    }

}
