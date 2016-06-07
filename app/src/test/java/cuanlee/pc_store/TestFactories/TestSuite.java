package cuanlee.pc_store.TestFactories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import cuanlee.pc_store.TestFactories.Employee.TestContactDetailsFactory;
import cuanlee.pc_store.TestFactories.Employee.TestEmployeeFactory;
import cuanlee.pc_store.TestFactories.Employee.TestGeographicalDetailsFactory;
import cuanlee.pc_store.TestFactories.PC.TestCPUFactory;
import cuanlee.pc_store.TestFactories.PC.TestChassisFactory;
import cuanlee.pc_store.TestFactories.PC.TestGPUFactory;
import cuanlee.pc_store.TestFactories.PC.TestHDDFactory;
import cuanlee.pc_store.TestFactories.PC.TestMotherboardFactory;
import cuanlee.pc_store.TestFactories.PC.TestPSUFactory;
import cuanlee.pc_store.TestFactories.PC.TestRAMFactory;


/**
 * Created by CuanL on 14/04/2016. Testing Commits
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestChassisFactory.class,
        TestContactDetailsFactory.class,
        TestCPUFactory.class,
        TestEmployeeFactory.class,
        TestGeographicalDetailsFactory.class,
        TestGPUFactory.class,
        TestHDDFactory.class,
        TestMotherboardFactory.class,
        TestPSUFactory.class,
        TestRAMFactory.class
})
public class TestSuite {
}
