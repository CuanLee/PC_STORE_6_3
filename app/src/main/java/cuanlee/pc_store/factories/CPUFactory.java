package cuanlee.pc_store.factories;


import cuanlee.pc_store.domain.PC.CPU;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface CPUFactory {
    CPU createCPU(Long id, String code, String description, Integer socket, String processorBrand, double speed_Ghz, double cache_MB, Integer cores, Integer stock, Integer active);
}
