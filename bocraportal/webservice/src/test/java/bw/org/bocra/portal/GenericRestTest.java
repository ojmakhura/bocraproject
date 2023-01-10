package bw.org.bocra.portal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.containers.PostgreSQLContainer;

@Transactional
/**
 * T: The main data datatyps
 * C: Criteria type
 */
public abstract class GenericRestTest<T, R extends JpaRepository, C, X> {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = BocraportalTestContainer.getInstance();

    protected final X restController;
    protected final GenericTestData<T, R, C, X> testData;

    public GenericRestTest(X restController, GenericTestData <T, R, C, X> testData) {
        this.restController = restController;
        this.testData = testData;
    }

    protected abstract Collection<T> dummyData(int num);
    protected abstract T unsavedDummyData();

    protected ResponseEntity<?> handleGetAll() throws Exception {
        Method getAll = restController.getClass().getDeclaredMethod("getAll");
        return (ResponseEntity<?>) getAll.invoke(restController);
    }
    protected ResponseEntity<?> handleGetAllPaged(int pageNumber, int pageSize) throws Exception {

        Method getAllPaged = restController.getClass().getDeclaredMethod("getAllPaged");
        return (ResponseEntity<?>) getAllPaged.invoke(restController);
    }
    protected ResponseEntity<?> handleFindById(Long id) throws Exception {

        Method findById = restController.getClass().getDeclaredMethod("findById");
        return (ResponseEntity<?>) findById.invoke(restController, id);
    }
    protected ResponseEntity<?> handleRemove(Long id) throws Exception {

        Method remove = restController.getClass().getDeclaredMethod("remove");
        return (ResponseEntity<?>) remove.invoke(restController, id);

    }

    protected ResponseEntity<?> handleSearch(C criteria) throws Exception {

        Method search = restController.getClass().getDeclaredMethod("search");
        return (ResponseEntity<?>) search.invoke(restController, criteria);

    }
    protected ResponseEntity<?> handlePagedSearch(int pagenumber, int pageSize, C criteria) throws Exception {

        Method pagedSearch = restController.getClass().getDeclaredMethod("pagedSearch");
        return (ResponseEntity<?>) pagedSearch.invoke(restController, pagenumber, pageSize, criteria);

    }

    protected ResponseEntity<?> handleSave(T o) throws Exception {

        Method save = restController.getClass().getDeclaredMethod("save");
        return (ResponseEntity<?>) save.invoke(restController, o);

    }

    protected abstract void basicCompareAssertions(T o1, T o2);
    // protected abstract Collection<T> searchData();
    // protected abstract C searchCriteria();

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void findById() throws Exception {

        Collection<T> dummies = this.dummyData(1);

        T obj = dummies.iterator().next();

        Method getId = obj.getClass().getDeclaredMethod("getId");
        
        ResponseEntity<?> response = handleFindById((Long)getId.invoke(obj));

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        T found = (T) response.getBody();

        Assertions.assertNotNull(found);
        Assertions.assertEquals(getId.invoke(found), getId.invoke(obj));
        basicCompareAssertions(obj, found);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void findById_notExisting() throws Exception {
        Collection<T> dummies = this.dummyData(1);

        Object obj = dummies.iterator().next();

        Method getId = obj.getClass().getDeclaredMethod("getId");
        Long id = (Long)getId.invoke(obj) + 10;

        ResponseEntity<?> response = handleFindById(id);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains(String.format("with id %d not found.", id)));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAll() throws Exception {
        Collection<T> dummies = dummyData(9);
        ResponseEntity<?> response = handleGetAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<T> items = (Collection<T>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(items));
        Assertions.assertEquals(items.size(), dummies.size());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAll_empty() throws Exception {
        ResponseEntity<?> response = handleGetAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<T> items = (Collection<T>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isEmpty(items));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAllPaged() throws Exception {
        dummyData(25);
        int pageNumber = 2;
        int pageSize = 4;
        ResponseEntity<?> response = handleGetAllPaged(pageNumber, pageSize);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<T> items = (Collection<T>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(items));
        Assertions.assertEquals(items.size(), pageSize);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAllPaged_lastPage() throws Exception {
        dummyData(15);
        int pageNumber = 3;
        int pageSize = 4;

        ResponseEntity<?> response = handleGetAllPaged(pageNumber, pageSize);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<T> values = (Collection<T>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(values));
        Assertions.assertEquals(values.size(), 3);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void pagedSearch() throws Exception {
        Collection<T> v = testData.searchData();

        ResponseEntity<?> response = handlePagedSearch(2, 3, testData.searchCriteria());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<T> values = (Collection<T>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(values));
        Assertions.assertEquals(values.size(), 1);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void remove() throws Exception {
        dummyData(15);
        ResponseEntity<?> response = handleGetAll();
        Collection<T> all = (Collection<T>) response.getBody();

        Object obj = all.iterator().next();

        Method getId = obj.getClass().getDeclaredMethod("getId");
        Long id = (Long)getId.invoke(obj);

        response = this.handleRemove(id);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertTrue((boolean) response.getBody());

        response = handleGetAll();
        Collection<T> all2 = (Collection<T>) response.getBody();

        Assertions.assertEquals(all2.size(), all.size() - 1);

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void remove_non_existing() throws Exception {
        dummyData(10);
        ResponseEntity<?> response = handleGetAll();
        Collection<T> all = (Collection<T>) response.getBody();

        response = handleRemove(300L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        Assertions.assertTrue(response.getBody().toString().contains("Could not delete"));

        response = handleGetAll();
        Collection<T> all2 = (Collection<T>) response.getBody();

        Assertions.assertEquals(all2.size(), all.size());

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save() throws Exception {
        
        ResponseEntity<?> response = handleSave(unsavedDummyData());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        
        Object obj = response.getBody();
        Assertions.assertNotNull(obj);

        Method getId = obj.getClass().getDeclaredMethod("getId");
        Long id = (Long)getId.invoke(obj);
        Assertions.assertNotNull(id);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_null() throws Exception {

        ResponseEntity<?> response = handleSave(null);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("information is missing"));
    }

    protected abstract C searchCriteriaNone();

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void search_no_results() throws Exception {
        
        testData.searchData();

        ResponseEntity<?> response = handleSearch(searchCriteriaNone());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<T> all = (Collection<T>) response.getBody();
        Assertions.assertEquals(all.size(), 0);
    }

    protected abstract C searchCriteriaEmpty();

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void search_empty() throws Exception {
        
        Collection<T> data = testData.searchData();

        ResponseEntity<?> response = handleSearch(searchCriteriaEmpty());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<T> types = (Collection<T>) response.getBody();
        Assertions.assertEquals(types.size(), data.size());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void search_null() throws Exception {
        
        Collection<T> data = testData.searchData();

        ResponseEntity<?> response = handleSearch(null);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<T> types = (Collection<T>) response.getBody();
        Assertions.assertEquals(types.size(), data.size());
    }
}
