package cuanlee.pc_store.services.PC;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.domain.PC.RAM;


/**
 * Created by CuanL on 08/05/2016.
 */
public interface RAMService {
    RAM addRam(RAM ram);

    boolean duplicateCheck(RAM ram);

    RAM updateRam(RAM ram);

    RAM getRam(Long ramId);

    Set<RAM> getAll();

    ArrayList<RAM> getAllActive();

    RAM deleteRam(RAM ram);

    int deleteAllRam();
}
