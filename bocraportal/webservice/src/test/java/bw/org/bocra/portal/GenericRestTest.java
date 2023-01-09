package bw.org.bocra.portal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.containers.PostgreSQLContainer;

@Transactional
public abstract class GenericRestTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = BocraportalTestContainer.getInstance();
    
    protected abstract Collection<?> dummyData(int num);
    protected abstract Object unsavedDummyData();

    protected abstract ResponseEntity<?> handleGetAll();
    protected abstract ResponseEntity<?> handleGetAllPaged(int pageNumber, int pageSize);
    protected abstract ResponseEntity<?> handleFindById(Long id);
    protected abstract ResponseEntity<?> handleRemove(Long id);
    protected abstract ResponseEntity<?> handleSearch(Object criteria);
    protected abstract ResponseEntity<?> handlePagedSearch(int pagenumber, int pageSize, Object criteria);
    protected abstract ResponseEntity<?> handleSave(Object o);

    protected abstract void basicCompareAssertions(Object o1, Object o2);
    protected abstract Collection<?> searchData();
    protected abstract Object searchCriteria();

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void findById() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Collection<?> dummies = this.dummyData(1);

        Object obj = dummies.iterator().next();

        Method getId = obj.getClass().getDeclaredMethod("getId");
        
        ResponseEntity<?> response = handleFindById((Long)getId.invoke(obj));

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Object found = (Object) response.getBody();

        Assertions.assertNotNull(found);
        Assertions.assertEquals(getId.invoke(found), getId.invoke(obj));
        basicCompareAssertions(obj, found);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void findById_notExisting() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Collection<?> dummies = this.dummyData(1);

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
    public void getAll() {
        Collection<?> dummies = dummyData(9);
        ResponseEntity<?> response = handleGetAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<?> auths = (Collection<?>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(auths));
        Assertions.assertEquals(auths.size(), dummies.size());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAll_empty() {
        ResponseEntity<?> response = handleGetAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<?> auths = (Collection<?>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isEmpty(auths));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAllPaged() {
        dummyData(25);
        int pageNumber = 2;
        int pageSize = 4;
        ResponseEntity<?> response = handleGetAllPaged(pageNumber, pageSize);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<?> auths = (Collection<?>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(auths));
        Assertions.assertEquals(auths.size(), pageSize);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAllPaged_lastPage() {
        dummyData(15);
        int pageNumber = 3;
        int pageSize = 4;

        ResponseEntity<?> response = handleGetAllPaged(pageNumber, pageSize);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<?> values = (Collection<?>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(values));
        Assertions.assertEquals(values.size(), 3);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void pagedSearch() {
        Collection<?> v = this.searchData();

        ResponseEntity<?> response = handlePagedSearch(2, 3, searchCriteria());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<?> values = (Collection<?>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(values));
        Assertions.assertEquals(values.size(), 1);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void remove() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        dummyData(15);
        ResponseEntity<?> response = handleGetAll();
        Collection<?> all = (Collection<?>) response.getBody();

        Object obj = all.iterator().next();

        Method getId = obj.getClass().getDeclaredMethod("getId");
        Long id = (Long)getId.invoke(obj);

        response = this.handleRemove(id);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertTrue((boolean) response.getBody());

        response = handleGetAll();
        Collection<?> all2 = (Collection<?>) response.getBody();

        Assertions.assertEquals(all2.size(), all.size() - 1);

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void remove_non_existing() {
        dummyData(10);
        ResponseEntity<?> response = handleGetAll();
        Collection<?> all = (Collection<?>) response.getBody();

        response = handleRemove(300L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        Assertions.assertTrue(response.getBody().toString().contains("Could not delete"));

        response = handleGetAll();
        Collection<?> all2 = (Collection<?>) response.getBody();

        Assertions.assertEquals(all2.size(), all.size());

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
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
    public void save_null() {

        ResponseEntity<?> response = handleSave(null);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("information is missing"));
    }

    protected abstract Object searchCriteriaNone();

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void search_no_results() {
        
        this.searchData();

        ResponseEntity<?> response = handleSearch(searchCriteriaNone());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<?> all = (Collection<?>) response.getBody();
        Assertions.assertEquals(all.size(), 0);
    }

    protected abstract Object searchCriteriaEmpty();

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void search_empty() {
        
        Collection<?> data = this.searchData();

        ResponseEntity<?> response = handleSearch(searchCriteriaEmpty());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<?> types = (Collection<?>) response.getBody();
        Assertions.assertEquals(types.size(), data.size());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void search_null() {
        
        Collection<?> data = this.searchData();

        ResponseEntity<?> response = handleSearch(null);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<?> types = (Collection<?>) response.getBody();
        Assertions.assertEquals(types.size(), data.size());
    }
}
