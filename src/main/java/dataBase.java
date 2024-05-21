import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @projectName: Project
 * @package: POJO
 * @className: dataBase
 * @author: xjm
 * @description: TODO
 * @date: 2024/5/21 17:31
 * @version: 1.0
 */
public class dataBase {
    private ArrayList<BigInteger> cList;
    private ArrayList<BigInteger> sList;

    public ArrayList<BigInteger> getcList() {
        return cList;
    }

    public void setcList(ArrayList<BigInteger> cList) {
        this.cList = cList;
    }

    public ArrayList<BigInteger> getsList() {
        return sList;
    }

    public void setsList(ArrayList<BigInteger> sList) {
        this.sList = sList;
    }
}
