package cuanlee.pc_store.factories;


import cuanlee.pc_store.domain.PC.Motherboard;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface MotherboardFactory {
    Motherboard createMotherboard(Long id, String code, String description, String chipset, Integer sataPorts, Integer usb2, Integer usb3, String formFactor, Integer stock, Integer active);
}
