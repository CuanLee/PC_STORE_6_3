package cuanlee.pc_store.TestFactories.PC;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cuanlee.pc_store.domain.PC.Chassis;
import cuanlee.pc_store.factories.ChassisFactory;
import cuanlee.pc_store.factories.MotherboardFactory;
import cuanlee.pc_store.factories.impl.ChassisFactoryImpl;
import cuanlee.pc_store.factories.impl.MotherboardFactoryImpl;


/**
 * Created by Cuan on 4/3/2016.
 */
public class TestChassisFactory {
    private ChassisFactory factory;
    private MotherboardFactory mobofactory;

    @Before
    public void setUp() throws Exception{
        factory = ChassisFactoryImpl.getInstance();
        mobofactory = MotherboardFactoryImpl.getInstance();
    }

    @Test
    public void testComputerCreation()
    {
        Chassis chassis = factory.createComputer(56464L,"7501212","Monster Build",1,6,4,11,"ATX");
        Assert.assertEquals(1, chassis.isActive().intValue());
        Assert.assertEquals(4, chassis.getCaseFans().intValue());
    }

    @Test
    public void testComputerCreationUpdate()
    {
        Chassis chassis = factory.createComputer(56464L,"7501212","Monster Build",1,6,4,11,"ATX");
        Assert.assertEquals(1, chassis.isActive().intValue());
        Assert.assertEquals(4, chassis.getCaseFans().intValue());

        Chassis updateChassis = new Chassis.Builder()
                .copy(chassis)
                .active(0)
                .hddBays(1)
                .build();

        Assert.assertEquals(0, updateChassis.isActive().intValue());
        Assert.assertEquals(1, updateChassis.getHddBays().intValue());
    }
}
