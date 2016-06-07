package cuanlee.pc_store.services.PC;

import java.util.Set;

import cuanlee.pc_store.domain.PC.PSU;


/**
 * Created by CuanL on 10/05/2016.
 */
public interface PSUService {
    PSU addPsu(PSU psu);

    boolean duplicateCheck(PSU psu);

    PSU updatePsu(PSU psu);

    PSU getPsu(Long psuId);

    Set<PSU> getAll();

    PSU deleteRam(PSU psu);

    int deleteAllRam();
}
