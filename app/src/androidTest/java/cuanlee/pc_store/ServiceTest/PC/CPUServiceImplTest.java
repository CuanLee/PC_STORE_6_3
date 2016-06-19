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
import cuanlee.pc_store.domain.PC.CPU;
import cuanlee.pc_store.services.PC.Impl.CPUServiceImpl;

/**
 * Created by CuanL on 19/06/2016.
 */
public class CPUServiceImplTest extends AndroidTestCase {
    private CPUServiceImpl cpuService;
    private boolean isBound;
    CPU cpu = new CPU();
    CPU cpuNew = new CPU();
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), CPUServiceImpl.class);
        GlobalContext.context = this.getContext();
        cpuService = CPUServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        //Create
        cpu = new CPU.Builder()
                .code("5351AA")
                .description("Intel Skylake")
                .socket(115)
                .processorBrand("Intel")
                .speed_Ghz(132323)
                .cache_MB(123)
                .cores(8)
                .stock(22)
                .active(1)
                .build();

        cpuNew = new CPU.Builder()
                .code("5351AA")
                .description("Intel Skylake")
                .socket(115)
                .processorBrand("Intel")
                .speed_Ghz(132323)
                .cache_MB(123)
                .cores(8)
                .stock(22)
                .active(1)
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CPUServiceImpl.ActivateServiceLocalBinder binder
                    = (CPUServiceImpl.ActivateServiceLocalBinder) service;
            cpuService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testAdd()throws Exception{
        System.out.print(cpu);

        CPU inserttedMobo = cpuService.addCPU(cpu);
        id = inserttedMobo.getId();
        Assert.assertNotNull(inserttedMobo);

        Set<CPU> allMobo = cpuService.getAll();
        Assert.assertTrue(allMobo.size()>0);

        //ReadAllActive
        ArrayList<CPU> allActive = cpuService.getAllActive();
        Assert.assertTrue(allActive.size()>0);

        //READ ENTITY
        CPU entity = cpuService.getCPU(id);
        Assert.assertNotNull(entity);

        //CheckDuplicate
        boolean duplicate = cpuService.duplicateCheck(cpuNew);
        Assert.assertEquals(true,duplicate);

        //Total Stock
        int totalStock = cpuService.getTotalStock();
        Assert.assertEquals(22,totalStock);

        //UPDATE ENTITY
        CPU updateEntity = new CPU.Builder()
                .copy(entity)
                .code("Update CPU")
                .build();
        cpuService.updateCPU(updateEntity);
        CPU newEntity = cpuService.getCPU(id);
        Assert.assertEquals("Update CPU",newEntity.getCode());

        // DELETE ENTITY
        cpuService.deleteCPU(updateEntity);
        CPU deletedEntity = cpuService.getCPU(id);
        Assert.assertNull(deletedEntity);
    }
}
