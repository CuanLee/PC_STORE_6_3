package cuanlee.pc_store.services.PC;

import java.util.Set;

import cuanlee.pc_store.domain.PC.GPU;


/**
 * Created by CuanL on 10/05/2016.
 */
public interface GPUService {
    GPU addGPU(GPU gpu);

    boolean duplicateCheck(GPU gpu);

    GPU updateGPU(GPU gpu);

    GPU getGPU(Long gpuId);

    Set<GPU> getAll();

    GPU deleteGPU(GPU gpu);

    int deleteAllGPU();
}
