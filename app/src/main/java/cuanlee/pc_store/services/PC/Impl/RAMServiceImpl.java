package cuanlee.pc_store.services.PC.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.database.database.GlobalContext;
import cuanlee.pc_store.domain.PC.RAM;
import cuanlee.pc_store.repository.PC.Impl.RAMRepositoryImpl;
import cuanlee.pc_store.repository.PC.RAMRepository;
import cuanlee.pc_store.services.PC.RAMService;


/**
 * Created by CuanL on 08/05/2016.
 */
public class RAMServiceImpl extends Service implements RAMService {
    RAMRepository ramRepository;
    private static RAMServiceImpl service = null;

    public RAMServiceImpl()
    {
        ramRepository = new RAMRepositoryImpl(GlobalContext.getAppContext());
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    public static RAMServiceImpl getInstance() {
        if (service == null)
            service = new RAMServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public RAMServiceImpl getService() {
            return RAMServiceImpl.this;
        }
    }



    @Override
    public RAM addRam(RAM ram) {
        return ramRepository.save(ram);
    }

    @Override
    public RAM updateRam(RAM ram) {
        return ramRepository.update(ram);
    }

    @Override
    public RAM getRam(Long ramId) {
        return ramRepository.findById(ramId);
    }

    @Override
    public Set<RAM> getAll() {
        Set<RAM> ram;
        ram = ramRepository.findAll();
        return ram;
    }

    @Override
    public RAM deleteRam(RAM ram) {
        return ramRepository.delete(ram);
    }

    @Override
    public int deleteAllRam() {
        return ramRepository.deleteAll();
    }

    @Override
    public boolean duplicateCheck(RAM ram) {
        Set<RAM> allRam = ramRepository.findAll();
        boolean duplicate = false;

        for (RAM ramRecord: allRam)
        {
            if (ram.getCode().equalsIgnoreCase(ramRecord.getCode())

                    )
                duplicate = true;
        }
        return duplicate;

    }

    @Override
    public ArrayList<RAM> getAllActive() {
        Set<RAM> allRam = ramRepository.findAll();
        ArrayList<RAM> allActiveRecords = new ArrayList<>();

        for (RAM ramRecord: allRam)
        {
            if (ramRecord.isActive().intValue() == 1)
                allActiveRecords.add(ramRecord);
        }
        return allActiveRecords;
    }
}
