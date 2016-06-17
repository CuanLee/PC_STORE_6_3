package cuanlee.pc_store.services.PC.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import cuanlee.pc_store.database.util.App;
import cuanlee.pc_store.domain.PC.GPU;
import cuanlee.pc_store.repository.PC.GPURepository;
import cuanlee.pc_store.repository.PC.Impl.GPURepositoryImpl;
import cuanlee.pc_store.services.PC.GPUService;


/**
 * Created by CuanL on 10/05/2016.
 */
public class GPUServiceImpl extends Service implements GPUService {
    final private GPURepository gpuRepository;

    private static GPUServiceImpl service = null;

    public static GPUServiceImpl getInstance() {
        if (service == null)
            service = new GPUServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public GPUServiceImpl getService() {
            return GPUServiceImpl.this;
        }
    }

    private GPUServiceImpl()
    {
        gpuRepository = new GPURepositoryImpl(App.getAppContext());
    }

    @Override
    public GPU addGPU(GPU gpu) {
        if(duplicateCheck(gpu) == false)
            return gpuRepository.save(gpu);
        else
            return null;
    }

    @Override
    public boolean duplicateCheck(GPU gpu) {
        Set<GPU> allGpu = gpuRepository.findAll();
        boolean duplicate = false;

        for (GPU gpuRecord: allGpu)
        {
            if (gpu.getCode().equalsIgnoreCase(gpuRecord.getCode()))
                duplicate = true;
        }
        return duplicate;
    }

    @Override
    public GPU updateGPU(GPU gpu) {
        if(duplicateCheck(gpu) == false)
            return gpuRepository.update(gpu);
        else
            return null;
    }

    @Override
    public GPU getGPU(Long gpuId) {
        return gpuRepository.findById(gpuId);
    }

    @Override
    public Set<GPU> getAll() {
        Set<GPU> gpu;
        gpu = gpuRepository.findAll();
        return gpu;
    }

    @Override
    public GPU deleteGPU(GPU gpu) {
        return gpuRepository.delete(gpu);
    }

    @Override
    public int deleteAllGPU() {
        return gpuRepository.deleteAll();
    }
}
