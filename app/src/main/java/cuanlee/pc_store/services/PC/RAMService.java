package cuanlee.pc_store.services.PC;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.domain.PC.RAM;


/**
 * Created by CuanL on 08/05/2016.
 */
public interface RAMService {
    //Adds a new Record
    RAM addRam(RAM ram);

    //Checks For A Duplicate Record
    boolean duplicateCheck(RAM ram);

    //Updates A Record
    RAM updateRam(RAM ram);

    //Gets A Single Record
    RAM getRam(Long ramId);

    //Gets All -Default
    Set<RAM> getAll();

    //GetAll Active STock
    ArrayList<RAM> getAllActive();

    //Deletes A Record
    RAM deleteRam(RAM ram);

    //Delete All Records
    int deleteAllRam();
}
