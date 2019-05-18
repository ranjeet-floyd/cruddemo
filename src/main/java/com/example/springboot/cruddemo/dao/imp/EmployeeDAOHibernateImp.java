package com.example.springboot.cruddemo.dao.imp;

import com.example.springboot.cruddemo.dao.EmployeeDAO;
import com.example.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImp implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOHibernateImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Employee> selectAll = currentSession.createQuery("from Employee", Employee.class);
        return selectAll.getResultList();
    }

    @Override
    public Employee findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(employee);

    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
//        currentSession.delete(id);
        Query deleteQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
        deleteQuery.setParameter("employeeId", id);
        deleteQuery.executeUpdate();
    }
}
