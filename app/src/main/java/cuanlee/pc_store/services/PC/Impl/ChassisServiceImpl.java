package cuanlee.pc_store.services.PC.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.database.database.GlobalContext;
import cuanlee.pc_store.database.util.App;
import cuanlee.pc_store.domain.PC.Chassis;
import cuanlee.pc_store.repository.PC.ChassisRepository;
import cuanlee.pc_store.repository.PC.Impl.ChassisRepositoryImpl;
import cuanlee.pc_store.services.PC.ChassisService;


/**
 * Created by CuanL on 10/05/2016.
 */
public class ChassisServiceImpl extends Service implements ChassisService {
    final private ChassisRepository chassisRepository;

    private static ChassisServiceImpl service = null;

    public static ChassisServiceImpl getInstance() {
        if (service == null)
            service = new ChassisServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public ChassisServiceImpl getService() {
            return ChassisServiceImpl.this;
        }
    }

    private ChassisServiceImpl()
    {
        chassisRepository = new ChassisRepositoryImpl(GlobalContext.getAppContext());
    }

    @Override
    public Chassis addChassis(Chassis chassis) {
            return chassisRepository.save(chassis);
    }

    @Override
    public boolean duplicateCheck(Chassis chassis) {
        Set<Chassis> allChassis = chassisRepository.findAll();
        boolean duplicate = false;

        for (Chassis chassisRecord: allChassis)
        {
            if (chassis.getCode().equalsIgnoreCase(chassisRecord.getCode()))
                duplicate = true;
        }
        return duplicate;
    }

    @Override
    public Chassis updateChassis(Chassis chassis) {
            return chassisRepository.update(chassis);
    }

    @Override
    public Chassis getChassis(Long chassisId) {
        return chassisRepository.findById(chassisId);
    }

    @Override
    public Set<Chassis> getAll() {
        Set<Chassis> chassis;
        chassis = chassisRepository.findAll();
        return chassis;
    }

    @Override
    public Chassis deleteChassis(Chassis chassis) {
        return chassisRepository.delete(chassis);
    }

    @Override
    public int deleteAllChassis() {
        return chassisRepository.deleteAll();
    }

    @Override
    public int getTotalStock() {
        Set<Chassis> all = chassisRepository.findAll();
        int totalStock = 0;

        for (Chassis record: all)
        {
            totalStock = totalStock + record.getStock().intValue();
        }
        return totalStock;
    }

    @Override
    public ArrayList<Chassis> getAllActive() {
        Set<Chassis> all = chassisRepository.findAll();
        ArrayList<Chassis> allActiveRecords = new ArrayList<>();

        for (Chassis record: all)
        {
            if (record.isActive().intValue() == 1)
                allActiveRecords.add(record);
        }
        return allActiveRecords;
    }
}
