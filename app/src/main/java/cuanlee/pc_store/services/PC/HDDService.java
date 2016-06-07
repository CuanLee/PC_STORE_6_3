package cuanlee.pc_store.services.PC;

import java.util.Set;

import cuanlee.pc_store.domain.PC.HDD;


/**
 * Created by CuanL on 10/05/2016.
 */
public interface HDDService {
    HDD addHDD(HDD hdd);

    boolean duplicateCheck(HDD hdd);

    HDD updateHDD(HDD hdd);

    HDD getHDD(Long hddId);

    Set<HDD> getAll();

    HDD deleteHDD(HDD hdd);

    int deleteAllHDD();
}
