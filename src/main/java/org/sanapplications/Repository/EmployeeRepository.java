package org.sanapplications.Repository;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sanapplications.Config.SessionFactoryConfig;
import org.sanapplications.Model.EmployeeModel;

import java.util.List;


public class EmployeeRepository {
    private final Session session;

    public EmployeeRepository() {
        session = SessionFactoryConfig.getInstance().getSession();
    }

    /*
    sample crud example
        // initializing repo
        EmployeeRepository employeeRepository = new EmployeeRepository();
        // creating employee
        EmployeeModel employee = new EmployeeModel("name","1234567890");
        employeeRepository.saveCustomer(employee);
        // get employee
        employeeRepository.getEmployee(1);
        // get all employees
        employeeRepository.getAllEmployees()
        // update employee
        EmployeeModel updatedEmployee = new EmployeeModel();
        updatedEmployee.setName("new name");
        updatedEmployee.setPassword("1234");
        employeeRepository.updateEmployeeById(2, updatedEmployee);
        // delete employee
        employeeRepository.deleteEmployeeById(1);
    */

    public List<EmployeeModel> getAllEmployees() {
        try {
            TypedQuery<EmployeeModel> query = session.createQuery("SELECT e FROM EmployeeModel e", EmployeeModel.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public EmployeeModel getEmployee(long id) throws RuntimeException {
        try {
            return session.get(EmployeeModel.class, id); // returns employee
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }

    public int saveCustomer(EmployeeModel employee) {
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(employee);
            transaction.commit();
            return 1;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

    public boolean updateEmployeeById(long employeeId, EmployeeModel updatedEmployee) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            EmployeeModel employee = session.get(EmployeeModel.class, employeeId);
            if (employee != null) {
                // Update the employee data with the data from updatedEmployee
                employee.setName(updatedEmployee.getName());
                employee.setPassword(updatedEmployee.getPassword());
                session.merge(employee);
                transaction.commit();
                return true;
            } else {
                // Employee with the given ID does not exist
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public boolean deleteEmployeeById(long employeeId) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            EmployeeModel employee = session.get(EmployeeModel.class, employeeId);
            if (employee != null) {
                session.remove(employee);
                transaction.commit();
                return true;
            } else {
                // Employee with the given ID does not exist
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
