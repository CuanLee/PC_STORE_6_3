package cuanlee.pc_store.services.PC;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.domain.PC.Motherboard;


/**
 * Created by CuanL on 10/05/2016.
 */
public interface MotherboardService {
    //Adds a new Record
    Motherboard addMotherboard(Motherboard motherboard);

    //Checks For A Duplicate Record
    boolean duplicateCheck(Motherboard motherboard);

    //Updates A Record
    Motherboard updateMotherboard(Motherboard motherboard);

    //Gets A Single Record
    Motherboard getMotherboard(Long motherboardId);

    //Gets All -Default
    Set<Motherboard> getAll();

    //Gets a Total Stock
    int getTotalStock();

    //Delete All Active Records
    ArrayList<Motherboard> getAllActive();

    //Deletes A Record
    Motherboard deleteMobo(Motherboard ram);
}
