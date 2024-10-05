package br.com.tiago.user_service_api.creator;

import lombok.experimental.UtilityClass;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@UtilityClass
public class CreatorUtils {

    /*Maneira de gerar valores aleatórios*/
    private final static PodamFactory factory = new PodamFactoryImpl();

    public static <T> T generateMock (final Class<T> clazz){
        return factory.manufacturePojo(clazz);
    }



}


