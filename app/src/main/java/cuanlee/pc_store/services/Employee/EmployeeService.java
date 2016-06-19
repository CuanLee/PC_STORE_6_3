package cuanlee.pc_store.services.Employee;

import java.util.Set;

import cuanlee.pc_store.domain.Employee.Employee;


/**
 * Created by CuanL on 08/05/2016.
 */
public interface EmployeeService {
    //Gets a Single Record
    Employee getEmployee(Long employeeId);

    //Checks That a User is Logged In
    boolean userAuthenticated(Employee employee);

    //Returns The List of users Logged In
    Set <Employee> getAllAuthenticatedUsers();

    //Checks For Duplicate Username Validation
    boolean duplicateUsername(Employee employee);

    //Checks For Duplicate Email Validation
    boolean duplicateEmail(Employee employee);

    //Adds A REcord To DB
    Employee addEmployee(Employee employee);

    //Removes A Record From The DB
    Employee deleteEmployee(Employee employee);
}
