package cuanlee.pc_store.TestFactories.PC;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cuanlee.pc_store.domain.PC.CPU;
import cuanlee.pc_store.factories.CPUFactory;
import cuanlee.pc_store.factories.impl.CPUFactoryImpl;


/**
 * Created by Cuan on 4/3/2016.
 */
public class TestCPUFactory {
    private CPUFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = CPUFactoryImpl.getInstance();
    }

    @Test
    public void testCPUCreation()
    {
        CPU cpu = factory.createCPU(121232123L,"5351AA","Intel Skylake",1150, "Intel",132323,123,8, 22,1);
        Assert.assertEquals(1, cpu.isActive().intValue());
    }

    @Test
    public void testCPUCreationUpdate()
    {
        CPU cpu = factory.createCPU(121232123L,"5351AA","Intel Skylake",1150, "Intel",132323,123,8, 22,1);
        Assert.assertEquals(1, cpu.isActive().intValue());

        CPU updateCpu = new CPU.Builder()
                .copy(cpu)
                .active(0)
                .build();

        Assert.assertEquals(0, updateCpu.isActive().intValue());
        Assert.assertEquals(8, updateCpu.getCores().intValue());
    }
}
