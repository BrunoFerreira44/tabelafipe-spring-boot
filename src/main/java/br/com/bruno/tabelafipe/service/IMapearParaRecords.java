package br.com.bruno.tabelafipe.service;

import java.util.List;

public interface IMapearParaRecords {
    <T> T mapearParaRecords(String json, Class<T> classe);
    <T> List<T> mapearListaParaRecords(String json, Class<T> classe);
}
