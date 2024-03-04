package br.com.adrian.carvaluation.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IConvertData {
    <T> T getData(String json, Class<T> classe) throws JsonProcessingException;

    <T> List<T> getList(String json, Class<T> classe) throws JsonProcessingException;
}
