package com.example.demo.Common;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component // Le dice a Spring que esta clase es un bean administrado
public class Mediator {

    // Map que asocia un tipo de Request con su Handler correspondiente
    Map<? extends Class<?>, RequestHandler<?, ?>> requestHandlerMap;

    // Spring inyecta automáticamente todos los RequestHandler disponibles
    public Mediator(List<RequestHandler<?, ?>> requestHandler) {

        // Convertimos la lista de handlers en un Map:
        // Key   -> tipo de request que maneja el handler
        // Value -> el handler mismo
        requestHandlerMap = requestHandler.stream()
                .collect(Collectors.toMap(
                        RequestHandler::getRequestType, // clave
                        Function.identity()               // valor
                ));
    }

    public <R, T extends Request<R>> R dispatch(T request) {

        RequestHandler<T, R> handler = (RequestHandler<T, R>) requestHandlerMap.get(request.getClass());

        if (handler == null) {
            throw new RuntimeException("No handler for " + request.getClass());
        }
        
        return handler.handler(request);
    }


}
