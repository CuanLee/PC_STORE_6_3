package cuanlee.pc_store.factories;


import cuanlee.pc_store.domain.PC.HDD;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface HDDFactory {
    HDD createHDD(Long id, String code, String description, double size_MB, double rpm, Integer sata, Integer stock, Integer active);
}
