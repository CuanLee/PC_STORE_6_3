package cuanlee.pc_store.services.PC;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.domain.PC.CPU;


/**
 * Created by CuanL on 10/05/2016.
 */
public interface CPUService {
    //Adds a new Record
    CPU addCPU(CPU cpu);

    //Checks For A Duplicate Record
    boolean duplicateCheck(CPU cpu);

    //Updates A Record
    CPU updateCPU(CPU cpu);

    //Gets A Single Record
    CPU getCPU(Long gpuId);

    //Gets All -Default
    Set<CPU> getAll();

    //Get Total STock
    int getTotalStock();

    //GetAll Active STock
    ArrayList<CPU> getAllActive();

    //Deletes A Record
    CPU deleteCPU(CPU cpu);

    //Delete All Records
    int deleteAllCPU();
}

