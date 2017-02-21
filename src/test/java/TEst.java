import java.io.File;
import org.junit.Test;
/**
 * Created by RENT on 2016-11-03.
 */
public class TEst {

    @Test
    public void test(){
        File file = new File("D:\\Java\\projekty IDEA\\MergeSort\\src\\main\\resources\\ivnho11.txt");
        System.out.println(file.getParent()+"\\");
    }

}
