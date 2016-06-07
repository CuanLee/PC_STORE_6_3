package cuanlee.pc_store.services.PC;

import java.util.Set;

import cuanlee.pc_store.domain.PC.CPU;


/**
 * Created by CuanL on 10/05/2016.
 */
public interface CPUService {
    CPU addCPU(CPU cpu);

    boolean duplicateCheck(CPU cpu);

    CPU updateCPU(CPU cpu);

    CPU getCPU(Long gpuId);

    Set<CPU> getAll();

    CPU deleteCPU(CPU cpu);

    int deleteAllCPU();
}

