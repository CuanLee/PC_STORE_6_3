package cuanlee.pc_store.ServiceTest.PC;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.test.AndroidTestCase;
import android.content.ServiceConnection;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.database.database.GlobalContext;
import cuanlee.pc_store.domain.PC.PSU;
import cuanlee.pc_store.services.PC.Impl.PSUServiceImpl;

/**
 * Created by CuanL on 14/06/2016.
 */
public class PSUServiceImplTest extends AndroidTestCase{
    private PSUServiceImpl psuService;
    PSU psu = new PSU();
    PSU psuNew = new PSU();
    private Long id;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), PSUServiceImpl.class);
        GlobalContext.context = this.getContext();
        psuService = PSUServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        //Create
         psu = new PSU.Builder()
                .code("11234")
                .description("Raidmax Gold Series")
                .watts(750)
                .four_pin_molex(0)
                .sata_connectors(4)
                .floppy_connectors(6)
                .stock(55)
                .active(1)
                .build();

        psuNew = new PSU.Builder()
                .code("11234")
                .description("Raidmax Gold Series")
                .watts(750)
                .four_pin_molex(0)
                .sata_connectors(4)
                .floppy_connectors(6)
                .stock(55)
                .active(1)
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PSUServiceImpl.ActivateServiceLocalBinder binder
                    = (PSUServiceImpl.ActivateServiceLocalBinder) service;
            psuService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testAdd()throws Exception{
        System.out.print(psu);

        PSU inserttedPsu = psuService.addPsu(psu);
        id = inserttedPsu.getId();
        Assert.assertNotNull(inserttedPsu);

        Set<PSU> allPsu = psuService.getAll();
        Assert.assertTrue(allPsu.size()>0);

        //ReadAllActive
        ArrayList<PSU> allActive = psuService.getAllActive();
        Assert.assertTrue(allActive.size()>0);

        //READ ENTITY
        PSU entity = psuService.getPsu(id);
        Assert.assertNotNull(entity);

        //Get By Watts
        ArrayList<PSU> allRecords = psuService.getByWatts(750);
        Assert.assertTrue(allRecords.size()>0);

        //CheckDuplicate
        boolean duplicate = psuService.duplicateCheck(psuNew);
        Assert.assertEquals(true,duplicate);

        //UPDATE ENTITY
        PSU updateEntity = new PSU.Builder()
                .copy(entity)
                .code("Update PSU")
                .build();
        psuService.updatePsu(updateEntity);
        PSU newEntity = psuService.getPsu(id);
        Assert.assertEquals("Update PSU",newEntity.getCode());

        // DELETE ENTITY
        psuService.deleteRam(updateEntity);
        PSU deletedEntity = psuService.getPsu(id);
        Assert.assertNull(deletedEntity);
    }
}
