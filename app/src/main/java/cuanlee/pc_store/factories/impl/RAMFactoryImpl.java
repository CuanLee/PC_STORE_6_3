package cuanlee.pc_store.factories.impl;


import cuanlee.pc_store.domain.PC.RAM;
import cuanlee.pc_store.factories.RAMFactory;

/**
 * Created by Cuan on 4/3/2016.
 */
public class RAMFactoryImpl implements RAMFactory {
    private static RAMFactoryImpl factory = null;

    private RAMFactoryImpl(){

    }

    public static RAMFactoryImpl getInstance(){
        if (factory == null)
            factory = new RAMFactoryImpl();

        return factory;
    }

    public RAM createRAM(Long id, String code, String description, String memorySize, double voltage, String memoryConfiguration, Integer stock, Integer active){
        RAM ram = new RAM.Builder()
                .id(id)
                .code(code)
                .description(description)
                .memorySize(memorySize)
                .voltage(voltage)
                .memoryConfiguration(memoryConfiguration)
                .stock(stock)
                .active(active)
                .build();
        return ram;
    }
}
