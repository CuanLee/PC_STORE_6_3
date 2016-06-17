package cuanlee.pc_store.services.PC.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.database.database.GlobalContext;
import cuanlee.pc_store.database.util.App;
import cuanlee.pc_store.domain.PC.Motherboard;
import cuanlee.pc_store.repository.PC.Impl.MotherboardRepositoryImpl;
import cuanlee.pc_store.repository.PC.MotherboardRepository;
import cuanlee.pc_store.services.PC.MotherboardService;


/**
 * Created by CuanL on 10/05/2016.
 */
public class MotherboardServiceImpl extends Service implements MotherboardService {
    final private MotherboardRepository motherboardRepository;
    private static MotherboardServiceImpl service = null;

    public MotherboardServiceImpl()
    {
        motherboardRepository = new MotherboardRepositoryImpl(GlobalContext.getAppContext());
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    public static MotherboardServiceImpl getInstance() {
        if (service == null)
            service = new MotherboardServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public MotherboardServiceImpl getService() {
            return MotherboardServiceImpl.this;
        }
    }



    @Override
    public Motherboard addMotherboard(Motherboard motherboard) {
            return motherboardRepository.save(motherboard);
    }

    @Override
    public boolean duplicateCheck(Motherboard motherboard) {
        Set<Motherboard> allMotherboard = motherboardRepository.findAll();
        boolean duplicate = false;

        for (Motherboard motherboardRecord: allMotherboard)
        {
            if (motherboard.getCode().equalsIgnoreCase(motherboardRecord.getCode()))
                duplicate = true;
        }
        return duplicate;

    }

    @Override
    public Motherboard updateMotherboard(Motherboard motherboard) {
            return motherboardRepository.update(motherboard);
    }

    @Override
    public Motherboard getMotherboard(Long motherboardId) {
        return motherboardRepository.findById(motherboardId);
    }

    @Override
    public Set<Motherboard> getAll() {
        Set<Motherboard> motherboards;
        motherboards = motherboardRepository.findAll();
        return motherboards;
    }

    @Override
    public int getTotalStock() {
        Set<Motherboard> allMobo = motherboardRepository.findAll();
        int totalStock = 0;

        for (Motherboard moboRecord: allMobo)
        {
            totalStock = totalStock + moboRecord.getStock().intValue();
        }
        return totalStock;
    }

    @Override
    public ArrayList<Motherboard> getAllActive() {
        Set<Motherboard> allMobo = motherboardRepository.findAll();
        ArrayList<Motherboard> allActiveRecords = new ArrayList<>();

        for (Motherboard moboRecord: allMobo)
        {
            if (moboRecord.isActive().intValue() == 1)
                allActiveRecords.add(moboRecord);
        }
        return allActiveRecords;
    }

    @Override
    public Motherboard deleteMobo(Motherboard mobo) {
        return motherboardRepository.delete(mobo);
    }
}
