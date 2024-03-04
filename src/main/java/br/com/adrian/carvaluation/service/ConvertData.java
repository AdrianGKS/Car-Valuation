package br.com.adrian.carvaluation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConvertData implements IConvertData {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> classe) throws JsonProcessingException {
        return mapper.readValue(json, classe);
    }

    @Override
    public <T> List<T> getList(String json, Class<T> classe) throws JsonProcessingException {
        CollectionType list =  mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);


        return mapper.readValue(json, list);
    }
}
