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
import cuanlee.pc_store.domain.PC.RAM;
import cuanlee.pc_store.services.PC.Impl.RAMServiceImpl;


/**
 * Created by CuanL on 08/05/2016.
 */
public class RAMServiceImplTest extends AndroidTestCase {
    private RAMServiceImpl ramService;
    private boolean isBound;
    RAM ram = new RAM();
    RAM ramNew = new RAM();
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), RAMServiceImpl.class);
        GlobalContext.context = this.getContext();
        ramService = RAMServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        //Create
         ram = new RAM.Builder()
                .code("vengance")
                .description("corsair vengance ram")
                .voltage(400)
                .memorySize("4GB")
                .memoryConfiguration("Dula Module")
                .stock(22)
                .active(0)
                .build();

        ramNew = new RAM.Builder()
                .code("vengance")
                .description("corsair vengance ram")
                .voltage(400)
                .memorySize("4GB")
                .memoryConfiguration("Dula Module")
                .stock(22)
                .active(0)
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RAMServiceImpl.ActivateServiceLocalBinder binder
                    = (RAMServiceImpl.ActivateServiceLocalBinder) service;
            ramService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testAdd()throws Exception{
        System.out.print(ram);

        RAM inserttedRam = ramService.addRam(ram);
        id = inserttedRam.getId();
        Assert.assertNotNull(inserttedRam);

        Set<RAM> allRam = ramService.getAll();
        Assert.assertTrue(allRam.size()>0);

        //ReadAllActive
        ArrayList<RAM> allActive = ramService.getAllActive();
        Assert.assertTrue(allActive.size()>0);

        //READ ENTITY
        RAM entity = ramService.getRam(id);
        Assert.assertNotNull(entity);

        //CheckDuplicate
        boolean duplicate = ramService.duplicateCheck(ramNew);
        Assert.assertEquals(true,duplicate);

        //UPDATE ENTITY
        RAM updateEntity = new RAM.Builder()
                .copy(entity)
                .code("Update RAM")
                .build();
        ramService.updateRam(updateEntity);
        RAM newEntity = ramService.getRam(id);
        Assert.assertEquals("Update RAM",newEntity.getCode());

        // DELETE ENTITY
        ramService.deleteRam(updateEntity);
        RAM deletedEntity = ramService.getRam(id);
        Assert.assertNull(deletedEntity);
    }
}
