package br.com.cooperativismo.infrastructure.util;

import com.google.gson.Gson;

public class ParserJSON {

    public static String fromObjToJSON(Object obj) {
        return new Gson().toJson(obj);
    }

}
