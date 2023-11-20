package com.example.springrest.getapi;

public interface GetDAO {

  String getCredtoken();

   String getTokenByemail(String email, String password);
}
