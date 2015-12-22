package com.leorowe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Leorowe on 11/2/2015.
 */
public class BaseTest {

    public  <T> T jsonMock(String name,final Class<T> type) throws FileNotFoundException {

        JsonReader reader = new JsonReader(new FileReader(getMockJsonPath()));
        Gson gson = new GsonBuilder().create();
        Map<String,Object> target=gson.fromJson(reader,new TypeToken<Map<String,Object>>(){}.getType());
        return gson.fromJson(gson.toJson(target.get(name)),type);
    }

    /**
     *
     * @param name
     * @param type new TypeToken<List<fill the type in here>>(){}.getType()
     * @param <T>
     * @return
     * @throws FileNotFoundException
     */
    public  <T> T jsonMock(String name,Type type) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(getMockJsonPath()));
        Gson gson = new GsonBuilder().create();
        Map<String,Object> target=gson.fromJson(reader,new TypeToken<Map<String,Object>>(){}.getType());
        return gson.fromJson(gson.toJson(target.get(name)),type);
    }

    public String getMockJsonPath(){
        return "src/test/asset/"+this.getClass().getSimpleName()+"/"+Thread.currentThread().getStackTrace()[3].getMethodName()+".json";
    }

    public static void printJson(Object obj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(obj));
    }
}
