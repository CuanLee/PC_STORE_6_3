package cuanlee.pc_store.services.PC;

import java.util.Set;

import cuanlee.pc_store.domain.PC.Chassis;


/**
 * Created by CuanL on 10/05/2016.
 */
public interface ChassisService {
    Chassis addChassis(Chassis chassis);

    boolean duplicateCheck(Chassis chassis);

    Chassis updateChassis(Chassis chassis);

    Chassis getChassis(Long chassisId);

    Set<Chassis> getAll();

    Chassis deleteChassis(Chassis chassis);

    int deleteAllChassis();
}
