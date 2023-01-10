package bw.org.bocra.portal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

/**
 * T: data type
 * R: Repository type
 * C: Criteria type
 * X: Rest controller type
 */
public abstract class GenericTestData<T, R extends JpaRepository, C, X> {

    protected final R repository;
    protected final X restController;

    public GenericTestData(R repository, X restController) {
        this.repository = repository;
        this.restController = restController;
    }
    
    public void clean() {
        repository.deleteAll();
    }

    public R getRepository() {
        return repository;
    }

    public X getRestController() {
        return restController;
    }

    public abstract T createUnsavedData();

    public abstract Collection<T> generateUnsavedSequentialData(int size);

    public T create(T obj) throws Exception {
        Method save = restController.getClass().getDeclaredMethod("save");
        ResponseEntity<?> response = (ResponseEntity<?>) save.invoke(restController, obj);
        return (T) response.getBody();
    }

    public T create() throws Exception {
        
        return this.create(createUnsavedData());
    }

    public Collection<T> generateSequentialData(int size) {

        return generateUnsavedSequentialData(size)
            .stream()
            .map(obj -> {
                try {
                    return create(obj);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                return null;
            })
            .collect(Collectors.toList());
    }

    public abstract Collection<T> searchData();

    public abstract C searchCriteria();

    public abstract C searchCriteriaEmpty() ;

    public abstract C searchCriteriaNone();
}
