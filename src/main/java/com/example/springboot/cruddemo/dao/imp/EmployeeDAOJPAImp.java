package com.example.springboot.cruddemo.dao.imp;

import com.example.springboot.cruddemo.dao.EmployeeDAO;
import com.example.springboot.cruddemo.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author r0k00xp
 */
@Repository
@Primary
@Slf4j
public class EmployeeDAOJPAImp implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJPAImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        log.info("Get all employee");
        // Mind | "select from employee" will not work
        Query query = entityManager.createQuery(" from Employee");

        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
        log.info("find employee by id :" + id);
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        log.info("Save " + employee);
        Employee dbEmployee = entityManager.merge(employee);
        employee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteById(int id) {
        log.info("Delete employee : " + id);
        Query deleteQuery = entityManager
                .createQuery("delete from Employee where id=:id");
        deleteQuery.setParameter("id", id);
        deleteQuery.executeUpdate();
    }
}
