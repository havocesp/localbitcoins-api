import com.fuzzy.parameter.ParameterCollection;
import com.fuzzy.parameter.RequestParameter;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by fuzzyavacado on 9/2/16.
 */
public class ParameterCollectionJsonTest {

    @Test
    public void toJsonParameters() {
        RequestParameter<String, Integer> p1 = new RequestParameter<>("test", 123);
        RequestParameter<String, String> p2 = new RequestParameter<>("quiz", "This is a test!");
        ParameterCollection collection = new ParameterCollection(new ArrayList<>());
        collection.addAll(p1, p2);
        System.out.println(collection.toString());
    }
}
