package cuanlee.pc_store.services.PC;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.domain.PC.Motherboard;


/**
 * Created by CuanL on 10/05/2016.
 */
public interface MotherboardService {
    Motherboard addMotherboard(Motherboard motherboard);

    boolean duplicateCheck(Motherboard motherboard);

    Motherboard updateMotherboard(Motherboard motherboard);

    Motherboard getMotherboard(Long motherboardId);

    Set<Motherboard> getAll();

    int getTotalStock();

    ArrayList<Motherboard> getAllActive();

    Motherboard deleteMobo(Motherboard ram);
}
