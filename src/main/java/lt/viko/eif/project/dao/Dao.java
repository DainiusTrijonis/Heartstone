package lt.viko.eif.project.dao;

        import java.util.List;

public interface Dao<T> {

    List<T> getAll() throws Exception;

    T get(int id);

    Boolean post(T object);

    Boolean put(T object);

    Boolean delete(int id);



}
