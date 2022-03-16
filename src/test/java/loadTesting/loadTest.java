package loadTesting;

import commands.CommandManger;
import data.StudyGroup;
import org.junit.jupiter.api.Test;
import utils.Collection;

class loadTest {
    @Test
    void toManyObjects(){
        for(long i = 0;i<Collection.MAX_SIZE;i++) {
            Collection.add(i, new StudyGroup());
        }
    }
}
