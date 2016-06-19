package cuanlee.pc_store.ServiceTest.Employee;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.database.database.GlobalContext;
import cuanlee.pc_store.domain.Employee.ContactDetails;
import cuanlee.pc_store.domain.Employee.Employee;
import cuanlee.pc_store.domain.Employee.GeographicalDetails;
import cuanlee.pc_store.services.Employee.Impl.EmployeeServiceImpl;

/**
 * Created by CuanL on 17/06/2016.
 */
public class EmployeeServiceImplTest extends AndroidTestCase {
    private EmployeeServiceImpl employeeService;
    private boolean isBound;
    Employee employee = new Employee();
    Employee employeeNew = new Employee();
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), EmployeeServiceImpl.class);
        GlobalContext.context = this.getContext();
        employeeService = EmployeeServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

        GeographicalDetails geographicalDetails = new GeographicalDetails.Builder()
                .country("SA")
                .province("WC")
                .city("Cape Town")
                .suburb("Brackenfell")
                .street("Long Street")
                .houseNumber(55)
                .build();

        ContactDetails contactDetails = new ContactDetails.Builder()
                .telephone("0212101022")
                .email("cuan@g.com")
                .build();
        //Create
        employee = new Employee.Builder()
                .username("cuanl26")
                .password("1234567")
                .firstName("Cuan")
                .lastName("Lee")
                .gender("Male")
                .contactDetails(contactDetails)
                .geographicalDetails(geographicalDetails)
                .idNumber("6546654564654")
                .loggedIn(1)
                .build();

        employeeNew = new Employee.Builder()
                .username("cuanl26")
                .password("1234567")
                .firstName("Cuan")
                .lastName("Lee")
                .gender("Male")
                .contactDetails(contactDetails)
                .geographicalDetails(geographicalDetails)
                .idNumber("6546654564654")
                .loggedIn(1)
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            EmployeeServiceImpl.ActivateServiceLocalBinder binder
                    = (EmployeeServiceImpl.ActivateServiceLocalBinder) service;
            employeeService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testAdd()throws Exception{
        System.out.print(employee);

        Employee inserttedEmp = employeeService.addEmployee(employee);
        id = inserttedEmp.getID();
        Assert.assertNotNull(inserttedEmp);

        //Authenticated Employees
        Set<Employee> allAuth = employeeService.getAllAuthenticatedUsers();
        Assert.assertTrue(allAuth.size()>0);

        //Duplicate User Name
        boolean duplicate = employeeService.duplicateUsername(employeeNew);
        Assert.assertEquals(true,duplicate);

        //Duplicate Email
        boolean duplicateEmail = employeeService.duplicateEmail(employeeNew);
        Assert.assertEquals(true,duplicate);
    }
}
