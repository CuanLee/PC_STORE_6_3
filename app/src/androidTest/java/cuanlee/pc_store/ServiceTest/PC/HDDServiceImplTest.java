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
import cuanlee.pc_store.domain.PC.HDD;
import cuanlee.pc_store.services.PC.Impl.HDDServiceImpl;

/**
 * Created by CuanL on 17/06/2016.
 */
public class HDDServiceImplTest extends AndroidTestCase {
    private HDDServiceImpl hddService;
    private boolean isBound;
    HDD hdd = new HDD();
    HDD hddNew = new HDD();
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), HDDServiceImpl.class);
        GlobalContext.context = this.getContext();
        hddService = HDDServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

        //Create
        hdd = new HDD.Builder()
                .code("testCode")
                .description("testDesc")
                .size_MB(1232)
                .rpm(2331)
                .sata(1)
                .stock(32)
                .active(1)
                .build();

        hddNew = new HDD.Builder()
                .code("testCode")
                .description("testDesc")
                .size_MB(1232)
                .rpm(2331)
                .sata(1)
                .stock(32)
                .active(1)
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            HDDServiceImpl.ActivateServiceLocalBinder binder
                    = (HDDServiceImpl.ActivateServiceLocalBinder) service;
            hddService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testAdd()throws Exception{
        System.out.print(hdd);

        HDD inserttedMobo = hddService.addHDD(hdd);
        id = inserttedMobo.getId();
        Assert.assertNotNull(inserttedMobo);

        Set<HDD> allMobo = hddService.getAll();
        Assert.assertTrue(allMobo.size()>0);

        //ReadAllActive
        ArrayList<HDD> allActive = hddService.getAllActive();
        Assert.assertTrue(allActive.size()>0);

        //READ ENTITY
        HDD entity = hddService.getHDD(id);
        Assert.assertNotNull(entity);

        //CheckDuplicate
        boolean duplicate = hddService.duplicateCheck(hddNew);
        Assert.assertEquals(true,duplicate);

        //Total Stock
        int totalStock = hddService.getTotalStock();
        Assert.assertEquals(32,totalStock);

        //UPDATE ENTITY
        HDD updateEntity = new HDD.Builder()
                .copy(entity)
                .code("Update HDD")
                .build();
        hddService.updateHDD(updateEntity);
        HDD newEntity = hddService.getHDD(id);
        Assert.assertEquals("Update HDD",newEntity.getCode());

        // DELETE ENTITY
        hddService.deleteHDD(updateEntity);
        HDD deletedEntity = hddService.getHDD(id);
        Assert.assertNull(deletedEntity);
    }
}
