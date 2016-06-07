package cuanlee.pc_store.factories;


import cuanlee.pc_store.domain.PC.PSU;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface PSUFactory {
    PSU createPSU(Long id, String code, String description, Integer watts, Integer four_pin_molex, Integer sata_connectors, Integer floppy_connectors, Integer stock, Integer active);
}
