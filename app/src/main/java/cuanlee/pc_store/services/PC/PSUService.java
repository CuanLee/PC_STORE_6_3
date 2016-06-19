package cuanlee.pc_store.services.PC;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.domain.PC.PSU;
import cuanlee.pc_store.domain.PC.RAM;


/**
 * Created by CuanL on 10/05/2016.
 */
public interface PSUService {
    //Adds a new Record
    PSU addPsu(PSU psu);

    //Checks For A Duplicate Record
    boolean duplicateCheck(PSU psu);

    //Updates A Record
    PSU updatePsu(PSU psu);

    //Gets A Single Record
    PSU getPsu(Long psuId);

    //Gets All -Default
    Set<PSU> getAll();

    //Deletes A Record
    PSU deleteRam(PSU psu);

    //GetAll Active STock
    ArrayList<PSU> getAllActive();

    //Gets All Records Based On The Watts
    ArrayList<PSU> getByWatts(int watts);

    //Delete All Records
    int deleteAllPSU();
}
