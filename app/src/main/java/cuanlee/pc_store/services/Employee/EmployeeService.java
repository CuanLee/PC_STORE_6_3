package cuanlee.pc_store.services.Employee;

import java.util.Set;

import cuanlee.pc_store.domain.Employee.Employee;


/**
 * Created by CuanL on 08/05/2016.
 */
public interface EmployeeService {
    String updatePassword(Employee employee);

    boolean userAuthenticated(Employee employee);

    Set <Employee> getAllAuthenticatedUsers();

    boolean duplicateUsername(Employee employee);
}
