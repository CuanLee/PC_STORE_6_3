package cuanlee.pc_store.services.Employee.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.HashSet;
import java.util.Set;

import cuanlee.pc_store.database.database.GlobalContext;
import cuanlee.pc_store.database.util.App;
import cuanlee.pc_store.domain.Employee.Employee;
import cuanlee.pc_store.repository.Employee.EmployeeRepository;
import cuanlee.pc_store.repository.Employee.Impl.EmployeeRepositoryImpl;
import cuanlee.pc_store.services.Employee.EmployeeService;


/**
 * Created by CuanL on 08/05/2016.
 */
public class EmployeeServiceImpl extends Service implements EmployeeService {
    final private EmployeeRepository employeeRepository;

    private static EmployeeServiceImpl service = null;

    public static EmployeeServiceImpl getInstance() {
        if (service == null)
            service = new EmployeeServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private Employee repo;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public EmployeeServiceImpl getService() {
            return EmployeeServiceImpl.this;
        }
    }

    public EmployeeServiceImpl()
    {
        employeeRepository = new EmployeeRepositoryImpl(GlobalContext.getAppContext());
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public boolean userAuthenticated(Employee employee) {
        Employee employeeSearched = employeeRepository.findById(employee.getID());

        if (employeeSearched.getLoggedIn() == 1)
            return true;
        else
            return false;

    }

    @Override
    public Set<Employee> getAllAuthenticatedUsers() {
        Set<Employee> employeeHashSet = new HashSet<>();

        Set<Employee> allEmployee = employeeRepository.findAll();

        for(Employee employee: allEmployee){
            if (employee.getLoggedIn().intValue() == 1)
                employeeHashSet.add(employee);
        }
        return employeeHashSet;
    }

    @Override
    public boolean duplicateUsername(Employee employeeEntity) {
        Set<Employee> allEmployee = employeeRepository.findAll();
        boolean duplicate = false;

        for (Employee employee: allEmployee)
        {
            if (employeeEntity.getUsername().equalsIgnoreCase(employee.getUsername()))
                duplicate = true;
        }
        return duplicate;
    }

    @Override
    public boolean duplicateEmail(Employee employeeEntity) {
        Set<Employee> allEmployee = employeeRepository.findAll();
        boolean duplicate = false;

        for (Employee employee: allEmployee)
        {
            if (employeeEntity.getContactDetails().getEmail().equalsIgnoreCase(employee.getContactDetails().getEmail()))
                duplicate = true;
        }
        return duplicate;
    }

    @Override
    public Employee deleteEmployee(Employee employee) {
        return employeeRepository.delete(employee);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
