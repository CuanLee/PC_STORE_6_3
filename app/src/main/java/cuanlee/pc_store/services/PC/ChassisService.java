package cuanlee.pc_store.services.PC;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.domain.PC.Chassis;


/**
 * Created by CuanL on 10/05/2016.
 */
public interface ChassisService {
    //Adds a new Record
    Chassis addChassis(Chassis chassis);

    //Checks For A Duplicate Record
    boolean duplicateCheck(Chassis chassis);

    //Updates A Record
    Chassis updateChassis(Chassis chassis);

    //Gets A Single Record
    Chassis getChassis(Long chassisId);

    //Gets All -Default
    Set<Chassis> getAll();

    //Deletes A Record
    Chassis deleteChassis(Chassis chassis);

    //Delete All Records
    int deleteAllChassis();

    //Get Total Stock
    int getTotalStock();

    //GetAll Active STock
    ArrayList<Chassis> getAllActive();
}
