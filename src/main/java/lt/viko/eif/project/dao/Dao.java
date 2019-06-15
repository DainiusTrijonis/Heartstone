package lt.viko.eif.project.dao;

import java.util.List;

public interface Dao<T> {

    List<T> getAll() throws Exception;

    void post(T t);

}
