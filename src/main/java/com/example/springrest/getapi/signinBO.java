package com.example.springrest.getapi;

import io.restassured.RestAssured;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

@Data
public class signinBO {
    @Getter
    @Setter
    public static class Root {
        public int code;
        public String desc;
        public ArrayList<Object> errors;
        public boolean success;
        public String type;
        public String name;
        public Data data;
    }
    @Getter@Setter
    public static class Data {
        public String accessToken;
        public String refreshToken;
    }
}
