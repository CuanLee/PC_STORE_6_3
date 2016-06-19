package cuanlee.pc_store.services.PC;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.domain.PC.GPU;


/**
 * Created by CuanL on 10/05/2016.
 */
public interface GPUService {
    //Adds a new Record
    GPU addGPU(GPU gpu);

    //Checks For A Duplicate Record
    boolean duplicateCheck(GPU gpu);

    //Updates A Record
    GPU updateGPU(GPU gpu);

    //Gets A Single Record
    GPU getGPU(Long gpuId);

    //Gets All -Default
    Set<GPU> getAll();

    //Get Total STock
    int getTotalStock();

    //GetAll Active STock
    ArrayList<GPU> getAllActive();

    //Deletes A Record
    GPU deleteGPU(GPU gpu);

    //Delete All Records
    int deleteAllGPU();
}
