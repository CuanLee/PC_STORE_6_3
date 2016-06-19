package cuanlee.pc_store.services.PC.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.database.database.GlobalContext;
import cuanlee.pc_store.database.util.App;
import cuanlee.pc_store.domain.PC.HDD;
import cuanlee.pc_store.repository.PC.HDDRepository;
import cuanlee.pc_store.repository.PC.Impl.HDDRepositoryImpl;
import cuanlee.pc_store.services.PC.HDDService;


/**
 * Created by CuanL on 10/05/2016.
 */
public class HDDServiceImpl extends Service implements HDDService {
    final private HDDRepository hddRepository;
    private static HDDServiceImpl service = null;

    public HDDServiceImpl()
    {
        hddRepository = new HDDRepositoryImpl(GlobalContext.getAppContext());
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    public static HDDServiceImpl getInstance() {
        if (service == null)
            service = new HDDServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public HDDServiceImpl getService() {
            return HDDServiceImpl.this;
        }
    }



    @Override
    public HDD addHDD(HDD hdd) {
            return hddRepository.save(hdd);
    }

    @Override
    public boolean duplicateCheck(HDD hdd) {
        Set<HDD> allHdd = hddRepository.findAll();
        boolean duplicate = false;

        for (HDD hddRecord: allHdd)
        {
            if (hdd.getCode().equalsIgnoreCase(hddRecord.getCode()))
                duplicate = true;
        }
        return duplicate;
    }

    @Override
    public HDD updateHDD(HDD hdd) {
            return hddRepository.update(hdd);
    }

    @Override
    public HDD getHDD(Long hddId) {
        return hddRepository.findById(hddId);
    }

    @Override
    public Set<HDD> getAll() {
        Set<HDD> hdd;
        hdd = hddRepository.findAll();
        return hdd;
    }

    @Override
    public HDD deleteHDD(HDD hdd) {
        return hddRepository.delete(hdd);
    }

    @Override
    public int deleteAllHDD() {
        return hddRepository.deleteAll();
    }

    @Override
    public ArrayList<HDD> getAllActive() {
        Set<HDD> allHdd = hddRepository.findAll();
        ArrayList<HDD> allActiveRecords = new ArrayList<>();

        for (HDD hddRecord: allHdd)
        {
            if (hddRecord.isActive().intValue() == 1)
                allActiveRecords.add(hddRecord);
        }
        return allActiveRecords;
    }

    @Override
    public int getTotalStock() {
        Set<HDD> all = hddRepository.findAll();
        int totalStock = 0;

        for (HDD record: all)
        {
            totalStock = totalStock + record.getStock().intValue();
        }
        return totalStock;
    }
}
