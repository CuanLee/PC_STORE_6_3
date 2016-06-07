package cuanlee.pc_store.factories;


import cuanlee.pc_store.domain.PC.RAM;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface RAMFactory {
    RAM createRAM(Long id, String code, String description, String memorySize, double voltage, String memoryConfiguration, Integer stock, Integer active);
}
