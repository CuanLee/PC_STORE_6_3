package cuanlee.pc_store.TestFactories.PC;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cuanlee.pc_store.domain.PC.GPU;
import cuanlee.pc_store.factories.GPUFactory;
import cuanlee.pc_store.factories.impl.GPUFactoryImpl;


/**
 * Created by Cuan on 4/3/2016.
 */
public class TestGPUFactory {
    private GPUFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = GPUFactoryImpl.getInstance();
    }

    @Test
    public void testGPUCreation()
    {
        GPU gpu = factory.createGPU(1231321L, "gpuCode", "gpuDescription", 132, 121, "GDDR5", 132123, "PCIE3",16, 1);
        Assert.assertEquals("GDDR5", gpu.getMemoryType());
    }

    @Test
    public void testGPUCreationUpdate()
    {
        GPU gpu = factory.createGPU(1231321L, "gpuCode", "gpuDescription", 132, 121, "GDDR5", 132123, "PCIE3",16, 1);
        Assert.assertEquals("GDDR5", gpu.getMemoryType());

        GPU upodateGPU = new GPU.Builder()
                .copy(gpu)
                .description("Nvidia")
                .build();

        Assert.assertEquals("Nvidia", upodateGPU.getDescription());
        Assert.assertEquals("gpuCode", upodateGPU.getCode());
    }
}
