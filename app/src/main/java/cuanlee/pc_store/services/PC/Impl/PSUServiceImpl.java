package cuanlee.pc_store.services.PC.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.database.database.GlobalContext;
import cuanlee.pc_store.database.util.App;
import cuanlee.pc_store.domain.PC.PSU;
import cuanlee.pc_store.repository.PC.Impl.PSURepositoryImpl;
import cuanlee.pc_store.repository.PC.PSURepository;
import cuanlee.pc_store.services.PC.PSUService;


/**
 * Created by CuanL on 10/05/2016.
 */
public class PSUServiceImpl extends Service implements PSUService {
    final private PSURepository psuRepository;
    private static PSUServiceImpl service = null;

    public PSUServiceImpl()
    {
        psuRepository = new PSURepositoryImpl(GlobalContext.getAppContext());
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    public static PSUServiceImpl getInstance() {
        if (service == null)
            service = new PSUServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public PSUServiceImpl getService() {
            return PSUServiceImpl.this;
        }
    }



    @Override
    public PSU addPsu(PSU psu) {

            return psuRepository.save(psu);

    }

    @Override
    public PSU updatePsu(PSU psu) {

            return psuRepository.update(psu);

    }

    @Override
    public PSU getPsu(Long psuId) {
        return psuRepository.findById(psuId);
    }

    @Override
    public Set<PSU> getAll() {
        Set<PSU> psu;
        psu = psuRepository.findAll();
        return psu;
    }

    @Override
    public PSU deleteRam(PSU psu) {
        return psuRepository.delete(psu);
    }

    @Override
    public int deleteAllPSU() {
        return psuRepository.deleteAll();
    }

    @Override
    public boolean duplicateCheck(PSU psu) {
        Set<PSU> allPsu = psuRepository.findAll();
        boolean duplicate = false;

        for (PSU psuRecord: allPsu)
        {
            if (psu.getCode().equalsIgnoreCase(psuRecord.getCode()))
                duplicate = true;
        }
        return duplicate;
    }

    @Override
    public ArrayList<PSU> getAllActive() {
        Set<PSU> allPsu = psuRepository.findAll();
        ArrayList<PSU> allActiveRecords = new ArrayList<>();

        for (PSU psuRecord: allPsu)
        {
            if (psuRecord.isActive().intValue() == 1)
                allActiveRecords.add(psuRecord);
        }
        return allActiveRecords;
    }

    @Override
    public ArrayList<PSU> getByWatts(int watts) {
        Set<PSU> allPsu = psuRepository.findAll();
        ArrayList<PSU> allRecords = new ArrayList<>();

        for (PSU psuRecord: allPsu)
        {
            if (psuRecord.getWatts().intValue() == watts)
                allRecords.add(psuRecord);
        }
        return allRecords;
    }
}
