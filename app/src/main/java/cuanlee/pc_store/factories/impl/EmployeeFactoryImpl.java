package cuanlee.pc_store.factories.impl;


import cuanlee.pc_store.domain.Employee.ContactDetails;
import cuanlee.pc_store.domain.Employee.Employee;
import cuanlee.pc_store.domain.Employee.GeographicalDetails;
import cuanlee.pc_store.factories.EmployeeFactory;

/**
 * Created by Cuan on 4/3/2016.
 */
public class EmployeeFactoryImpl implements EmployeeFactory {
    private static EmployeeFactoryImpl factory = null;

    private EmployeeFactoryImpl(){

    }

    public static EmployeeFactoryImpl getInstance(){
        if (factory == null)
            factory = new EmployeeFactoryImpl();

        return factory;
    }

    public Employee createEmployee(Long id, String username, String password, String firstName, String lastName, String gender, ContactDetails contactDetails, GeographicalDetails geographicalDetails, String idNumber, Integer loggedIn){
        Employee employee = new Employee.Builder()
                .id(id)
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .contactDetails(contactDetails)
                .geographicalDetails(geographicalDetails)
                .idNumber(idNumber)
                .loggedIn(loggedIn)
                .build();
        return employee;
    }

}
