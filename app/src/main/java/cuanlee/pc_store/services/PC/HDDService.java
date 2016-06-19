package cuanlee.pc_store.services.PC;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.domain.PC.HDD;


/**
 * Created by CuanL on 10/05/2016.
 */
public interface HDDService {
    //Adds a new Record
    HDD addHDD(HDD hdd);

    //Checks For A Duplicate Record
    boolean duplicateCheck(HDD hdd);

    //Updates A Record
    HDD updateHDD(HDD hdd);

    //Gets A Single Record
    HDD getHDD(Long hddId);

    //Gets All -Default
    Set<HDD> getAll();

    //GetAll Active STock
    ArrayList<HDD> getAllActive();

    //Get Total Stock
    int getTotalStock();

    //Deletes A Record
    HDD deleteHDD(HDD hdd);

    //Delete All Records
    int deleteAllHDD();
}
