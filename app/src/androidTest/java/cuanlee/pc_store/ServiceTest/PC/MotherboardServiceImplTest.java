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
import cuanlee.pc_store.domain.PC.Motherboard;
import cuanlee.pc_store.services.PC.Impl.MotherboardServiceImpl;

/**
 * Created by CuanL on 15/06/2016.
 */
public class MotherboardServiceImplTest  extends AndroidTestCase{
    private MotherboardServiceImpl motherboardService;
    private boolean isBound;
    Motherboard mobo = new Motherboard();
    Motherboard moboNew = new Motherboard();
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), MotherboardServiceImpl.class);
        GlobalContext.context = this.getContext();
        motherboardService = MotherboardServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        //Create
        mobo = new Motherboard.Builder()
                .code("Asus B85m")
                .description("Asus Golden Series")
                .chipset("1150")
                .sataPorts(2133)
                .usb2(4)
                .usb3(2)
                .formFactor("ATX")
                .stock(89)
                .active(1)
                .build();

        moboNew = new Motherboard.Builder()
                .code("Asus B85m")
                .description("Asus Golden Series")
                .chipset("1150")
                .sataPorts(2133)
                .usb2(4)
                .usb3(2)
                .formFactor("ATX")
                .stock(89)
                .active(1)
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MotherboardServiceImpl.ActivateServiceLocalBinder binder
                    = (MotherboardServiceImpl.ActivateServiceLocalBinder) service;
            motherboardService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testAdd()throws Exception{
        System.out.print(mobo);

        Motherboard inserttedMobo = motherboardService.addMotherboard(mobo);
        id = inserttedMobo.getId();
        Assert.assertNotNull(inserttedMobo);

        Set<Motherboard> allMobo = motherboardService.getAll();
        Assert.assertTrue(allMobo.size()>0);

        //ReadAllActive
        ArrayList<Motherboard> allActive = motherboardService.getAllActive();
        Assert.assertTrue(allActive.size()>0);

        //READ ENTITY
        Motherboard entity = motherboardService.getMotherboard(id);
        Assert.assertNotNull(entity);

        //CheckDuplicate
        boolean duplicate = motherboardService.duplicateCheck(moboNew);
        Assert.assertEquals(true,duplicate);

        //Total Stock
        int totalStock = motherboardService.getTotalStock();
        Assert.assertEquals(89,totalStock);

        //UPDATE ENTITY
        Motherboard updateEntity = new Motherboard.Builder()
                .copy(entity)
                .code("Update Mobo")
                .build();
        motherboardService.updateMotherboard(updateEntity);
        Motherboard newEntity = motherboardService.getMotherboard(id);
        Assert.assertEquals("Update Mobo",newEntity.getCode());

        // DELETE ENTITY
        motherboardService.deleteMobo(updateEntity);
        Motherboard deletedEntity = motherboardService.getMotherboard(id);
        Assert.assertNull(deletedEntity);
    }
}
