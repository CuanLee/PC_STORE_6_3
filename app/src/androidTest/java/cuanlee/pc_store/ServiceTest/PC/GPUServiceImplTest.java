package cuanlee.pc_store.ServiceTest.PC;

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
import cuanlee.pc_store.domain.PC.GPU;
import cuanlee.pc_store.services.PC.Impl.GPUServiceImpl;

/**
 * Created by CuanL on 17/06/2016.
 */
public class GPUServiceImplTest extends AndroidTestCase {
    private GPUServiceImpl gpuService;
    private boolean isBound;
    GPU gpu = new GPU();
    GPU gpuNew = new GPU();
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), GPUServiceImpl.class);
        GlobalContext.context = this.getContext();
        gpuService = GPUServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        //Create
        //Create
         gpu = new GPU.Builder()
                .code("gpuCode")
                .description("gpuDescription")
                .bitMemory(132)
                .memorySize_GB(121)
                .memoryType("GDDR5")
                .memoryClock_MHz(132123)
                .cardBus("PCIE3")
                .stock(16)
                .active(1)
                .build();

        gpuNew = new GPU.Builder()
                .code("gpuCode")
                .description("gpuDescription")
                .bitMemory(132)
                .memorySize_GB(121)
                .memoryType("GDDR5")
                .memoryClock_MHz(132123)
                .cardBus("PCIE3")
                .stock(16)
                .active(1)
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            GPUServiceImpl.ActivateServiceLocalBinder binder
                    = (GPUServiceImpl.ActivateServiceLocalBinder) service;
            gpuService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testAdd()throws Exception{
        System.out.print(gpu);

        GPU inserttedMobo = gpuService.addGPU(gpu);
        id = inserttedMobo.getId();
        Assert.assertNotNull(inserttedMobo);

        Set<GPU> allMobo = gpuService.getAll();
        Assert.assertTrue(allMobo.size()>0);

        //ReadAllActive
        ArrayList<GPU> allActive = gpuService.getAllActive();
        Assert.assertTrue(allActive.size()>0);

        //READ ENTITY
        GPU entity = gpuService.getGPU(id);
        Assert.assertNotNull(entity);

        //CheckDuplicate
        boolean duplicate = gpuService.duplicateCheck(gpuNew);
        Assert.assertEquals(true,duplicate);

        //Total Stock
        int totalStock = gpuService.getTotalStock();
        Assert.assertEquals(16,totalStock);

        //UPDATE ENTITY
        GPU updateEntity = new GPU.Builder()
                .copy(entity)
                .code("Update GPU")
                .build();
        gpuService.updateGPU(updateEntity);
        GPU newEntity = gpuService.getGPU(id);
        Assert.assertEquals("Update GPU",newEntity.getCode());

        // DELETE ENTITY
        gpuService.deleteGPU(updateEntity);
        GPU deletedEntity = gpuService.getGPU(id);
        Assert.assertNull(deletedEntity);
    }
}
