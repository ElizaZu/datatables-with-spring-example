package dte.dao;

import java.util.List;

/**
 * Created by ElZu on 09.04.2018.
 */

public interface DictionaryDao<T> {
    List<T> getAll();
    int getCount();
    void save(T obj);
}
