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
import cuanlee.pc_store.domain.PC.Chassis;
import cuanlee.pc_store.services.PC.ChassisService;
import cuanlee.pc_store.services.PC.Impl.ChassisServiceImpl;

/**
 * Created by CuanL on 17/06/2016.
 */
public class ChassisServiceImplTest extends AndroidTestCase {
    private ChassisServiceImpl chassisService;
    private boolean isBound;
    Chassis chassis = new Chassis();
    Chassis chassisNew = new Chassis();
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), ChassisServiceImpl.class);
        GlobalContext.context = this.getContext();
        chassisService = ChassisServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        //Create
        chassis = new Chassis.Builder()
                .code("7501212")
                .description("Monster Build")
                .hddBays(6)
                .caseFans(4)
                .formFactor("ATX")
                .stock(11)
                .active(1)
                .build();

        chassisNew = new Chassis.Builder()
                .code("7501212")
                .description("Monster Build")
                .hddBays(6)
                .caseFans(4)
                .formFactor("ATX")
                .stock(11)
                .active(1)
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ChassisServiceImpl.ActivateServiceLocalBinder binder
                    = (ChassisServiceImpl.ActivateServiceLocalBinder) service;
            chassisService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testAdd()throws Exception{
        System.out.print(chassis);

        Chassis inserttedMobo = chassisService.addChassis(chassis);
        id = inserttedMobo.getId();
        Assert.assertNotNull(inserttedMobo);

        Set<Chassis> allMobo = chassisService.getAll();
        Assert.assertTrue(allMobo.size()>0);

        //ReadAllActive
        ArrayList<Chassis> allActive = chassisService.getAllActive();
        Assert.assertTrue(allActive.size()>0);

        //READ ENTITY
        Chassis entity = chassisService.getChassis(id);
        Assert.assertNotNull(entity);

        //CheckDuplicate
        boolean duplicate = chassisService.duplicateCheck(chassisNew);
        Assert.assertEquals(true,duplicate);

        //Total Stock
        int totalStock = chassisService.getTotalStock();
        Assert.assertEquals(11,totalStock);

        //UPDATE ENTITY
        Chassis updateEntity = new Chassis.Builder()
                .copy(entity)
                .code("Update Chassis")
                .build();
        chassisService.updateChassis(updateEntity);
        Chassis newEntity = chassisService.getChassis(id);
        Assert.assertEquals("Update Chassis",newEntity.getCode());

        // DELETE ENTITY
        chassisService.deleteChassis(updateEntity);
        Chassis deletedEntity = chassisService.getChassis(id);
        Assert.assertNull(deletedEntity);
    }
}
