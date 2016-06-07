package cuanlee.pc_store.factories;


import cuanlee.pc_store.domain.PC.Chassis;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface ChassisFactory {
    Chassis createComputer(Long id, String code, String description, Integer active, Integer hddBays, Integer caseFans, Integer stock, String formFactor);
}
