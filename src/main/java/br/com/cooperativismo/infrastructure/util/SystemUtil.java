package br.com.cooperativismo.infrastructure.util;

import java.time.LocalDateTime;
import java.util.Objects;

public class SystemUtil {

    public static LocalDateTime getDataAtual(){
        return LocalDateTime.now();
    }

    public static LocalDateTime getDataAtualMaisMinutos(int minuto){
        return LocalDateTime.now().plusMinutes(minuto);
    }

    public static boolean nullBlank(String value){
        if(Objects.isNull(value)){
            return true;
        }
        return value.isBlank();
    }

}
