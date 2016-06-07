package cuanlee.pc_store.factories;

import cuanlee.pc_store.domain.Employee.ContactDetails;
import cuanlee.pc_store.domain.Employee.Employee;
import cuanlee.pc_store.domain.Employee.GeographicalDetails;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface EmployeeFactory {
    Employee createEmployee(Long id, String username, String password, String firstName, String lastName, String gender, ContactDetails contactDetails, GeographicalDetails geographicalDetails, String idNumber, Integer loggedIn);
}
