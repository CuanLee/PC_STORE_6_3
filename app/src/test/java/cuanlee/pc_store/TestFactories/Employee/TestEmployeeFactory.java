package cuanlee.pc_store.TestFactories.Employee;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cuanlee.pc_store.domain.Employee.Employee;
import cuanlee.pc_store.factories.EmployeeFactory;
import cuanlee.pc_store.factories.impl.EmployeeFactoryImpl;


/**
 * Created by Cuan on 4/3/2016.
 */
public class TestEmployeeFactory {
    private EmployeeFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = EmployeeFactoryImpl.getInstance();
    }

    @Test
    public void testEmployeeCreation()
    {
        Employee employee = factory.createEmployee(123131L,"cuanl26","1234567","Cuan","Lee", "Male",null,null,"6546654564654", 1);
        Assert.assertEquals("Cuan", employee.getFirstName());
    }

    @Test
    public void testEmployeeCreationUpdate()
    {
        Employee employee = factory.createEmployee(123131L,"cuanl26","1234567","Cuan","Lee", "Male",null,null,"6546654564654", 1);
        Assert.assertEquals("Cuan", employee.getFirstName());

        Employee updateEmployee = new Employee.Builder()
                .copy(employee)
                .lastName("John")
                .build();

        Assert.assertEquals("John", updateEmployee.getLastName());
        Assert.assertEquals("6546654564654", updateEmployee.getIdNumber());
    }
}
