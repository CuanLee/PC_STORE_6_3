package cuanlee.pc_store.RepositoryTests.PC;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cuanlee.pc_store.domain.PC.HDD;
import cuanlee.pc_store.repository.PC.HDDRepository;
import cuanlee.pc_store.repository.PC.Impl.HDDRepositoryImpl;


/**
 * Created by CuanL on 22/04/2016.
 */
public class HDDRepositoryImplTest extends AndroidTestCase {
    private static final String TAG="HDD TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        HDDRepository repo = new HDDRepositoryImpl(this.getContext());

        //Create
        HDD hdd = new HDD.Builder()
                .code("testCode")
                .description("testDesc")
                .size_MB(1232)
                .rpm(2331)
                .sata(1)
                .stock(32)
                .active(1)
                .build();

        HDD inserttedHDD = repo.save(hdd);
        id = inserttedHDD.getId();
        Assert.assertNotNull(TAG + " CREATE",inserttedHDD);

        //READ ALL
        Set<HDD> allHdd = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allHdd.size()>0);

        //READ ENTITY
        HDD entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        HDD updateEntity = new HDD.Builder()
                .copy(entity)
                .code("Update HDD")
                .build();
        repo.update(updateEntity);
        HDD newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Update HDD",newEntity.getCode());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        //HDD deletedEntity = repo.findById(id);
        //Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
