import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @projectName: Project
 * @package: PACKAGE_NAME
 * @className: BaseFunction
 * @author: xjm
 * @description: provide base function to system
 * @date: 2024/5/20 20:26
 * @version: 1.0
 */
public class BaseFunction {
    /**
     * @param numbits: numBits – maximum bitLength of the new BigInteger
     * @return BigInteger
     * @author xjm
     * @description TODO
     * @date 2024/5/20 20:27
     */
    public static BigInteger getRndBigIntger(int numbits) {
        Random rnd = new Random();
        return new BigInteger(numbits, rnd);
    }

    /**
     * @param bitLength:
     * @return BigInteger
     * @author xjm
     * @description TODO
     * @date 2024/5/20 20:29
     */
    public static BigInteger getStrongPrime (int bitLength){
        Random random = new Random();
        while(true) {
            BigInteger bigInteger = BigInteger.probablePrime(bitLength - 1, random).multiply(new BigInteger("2")).add(new BigInteger("1"));
            if (bigInteger.isProbablePrime(256)) {
                return bigInteger;
            }
        }
    }
    public static BigInteger caculateD(ArrayList<BigInteger> baseNumList, ArrayList<BigInteger> exponentList, BigInteger m){
        //异常处理部分------之后补

        //计算部分
        BigInteger J = baseNumList.getFirst();
        for (int i = 0; i < exponentList.size(); i++) {
            J = J.parallelMultiply(baseNumList.get(i+1).modPow(exponentList.get(i),m));
        }
        return  J.mod(m);
    }
    /**
     * @param message:
     * @return BigInteger
     * @author xjm
     * @description TODO
     * @date 2024/5/20 20:28
     */
    public static BigInteger getHash(BigInteger message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageByteArray = message.toByteArray();
        md.update(messageByteArray);
        byte[] digest = md.digest();
        return new BigInteger(digest);
    }

    /**
     * @param baseNumList:
     * @param exponentList:
     * @param m:
     * @return BigInteger
     * @author xjm
     * @description TODO
     * @date 2024/5/20 20:43
     */
    public static BigInteger caculateT_P1(ArrayList<BigInteger> baseNumList,ArrayList<BigInteger> exponentList,BigInteger m){
//        if (baseNumList == null)
//            throw new Exception("baseNums为空");
//        else if(exponentList == null)
//            throw new Exception("baseNums为空");
//        else if(baseNumList.size()!= exponentList.size())
//            throw new Exception("底数列表与指数列表长度不相等");
        BigInteger T = BigInteger.ONE;
        for (int i = 0; i < baseNumList.size(); i++) {
            T = T.multiply(baseNumList.get(i).modPow(exponentList.get(i),m));
        }
        return T.mod(m);
    }
    /**
     * @param baseNumList: 底数列表
     * @param exponentList: 指数列表
     * @param m: 模数
     * @return BigInteger
     * @author xjm
     * @description 挑战计算模式二 型如  A^ry/(ax^x*as^s*....)的挑战
     * @date 5/12/2024 9:22 PM
     */
    public static BigInteger caculateT_P2(ArrayList<BigInteger> baseNumList,ArrayList<BigInteger> exponentList,BigInteger m){
//        if (baseNumList == null)
//            throw new Exception("baseNumList为空");
//        else if(exponentList == null)
//            throw new Exception("exponentList为空");
//        else if(baseNumList.size()!= exponentList.size())
//            throw new Exception("baseNumList与exponentList长度不相等");
        BigInteger T = baseNumList.get(0).modPow(exponentList.get(0),m);
        ArrayList<BigInteger> baseNumSubList = new ArrayList<BigInteger>();
        ArrayList<BigInteger> exponentSubList = new ArrayList<BigInteger>();
        baseNumSubList.addAll(baseNumList.subList(1,baseNumList.size()));
        exponentSubList.addAll(exponentList.subList(1,exponentList.size()));
        BigInteger invPart = caculateT_P1(baseNumSubList,exponentSubList,m).modInverse(m);
        return T.multiply(invPart).mod(m);
    }
    public static ArrayList<ArrayList<BigInteger>>generateRList(ArrayList<ArrayList<BigInteger>> secParamList){
        ArrayList<ArrayList<BigInteger>> rList = new ArrayList<>();
        for (int i = 0; i < secParamList.size(); i++) {
            ArrayList<BigInteger> temp = new ArrayList<>();
            for (int j = 0; j < secParamList.get(i).size(); j++) {
                temp.add(getRndBigIntger(secParamList.get(i).get(j).bitLength()));
            }
            rList.add(temp);
        }
        return rList;
    }
    public static ArrayList<ArrayList<BigInteger>> caculateSList(ArrayList<ArrayList<BigInteger>> rList,ArrayList<ArrayList<BigInteger>> secParamList,BigInteger c)
    {
        ArrayList<ArrayList<BigInteger>> sList  = new ArrayList<ArrayList<BigInteger>>();

        for (int i = 0; i < rList.size(); i++) {
            ArrayList<BigInteger> temp = new ArrayList<>();
            for (int j = 0; j < rList.get(i).size(); j++) {
                temp.add(rList.get(i).get(j).subtract(c.multiply(secParamList.get(i).get(j))));
            }
            sList.add(temp);
        }
        return sList;
    }
    /**
     * @param a:
     * @return ArrayList<BigInteger>
     * @author xjm
     * @description TODO
     * @date 2024/5/20 20:31
     */
    public static ArrayList<BigInteger> parameters2ArrayList(BigInteger a){
        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        list.add(a);
        return list;
    }
    /**
     * @param a:
     * @param b:
     * @return ArrayList<BigInteger>
     * @author xjm
     * @description TODO
     * @date 5/12/2024 10:21 PM
     */
    public static ArrayList<BigInteger> parameters2ArrayList(BigInteger a,BigInteger b){
        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        list.add(a);
        list.add(b);
        return list;
    }
    /**
     * @param a:
     * @param b:
     * @param c:
     * @return ArrayList<BigInteger>
     * @author xjm
     * @description TODO
     * @date 5/12/2024 10:21 PM
     */
    public static ArrayList<BigInteger> parameters2ArrayList(BigInteger a,BigInteger b,BigInteger c){
        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        list.add(a);
        list.add(b);
        list.add(c);
        return list;
    }
    /**
     * @description 同上
     * @date 5/12/2024 10:22 PM
     */
    public static ArrayList<BigInteger> parameters2ArrayList(BigInteger a,BigInteger b,BigInteger c,BigInteger d){
        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        return list;
    }
    /**
     * @description 同上
     * @date 5/12/2024 10:22 PM
     */
    public static ArrayList<BigInteger> parameters2ArrayList(BigInteger a,BigInteger b,BigInteger c,BigInteger d,BigInteger e){
        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        return list;
    }
    /**
     * @description 同上
     * @date 5/12/2024 10:22 PM
     */
    public static ArrayList<BigInteger> parameters2ArrayList(BigInteger a,BigInteger b,BigInteger c,BigInteger d,BigInteger e,BigInteger f){
        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        return list;
    }
    /**
     * @description 同上
     * @date 5/12/2024 10:22 PM
     */
    public static ArrayList<BigInteger> parameters2ArrayList(BigInteger a,BigInteger b,BigInteger c,BigInteger d,BigInteger e,BigInteger f,BigInteger g){
        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        list.add(g);
        return list;
    }
    /**
     * @description 同上
     * @date 5/12/2024 10:22 PM
     */
    public static ArrayList<BigInteger> parameters2ArrayList(BigInteger a,BigInteger b,BigInteger c,BigInteger d,BigInteger e,BigInteger f,BigInteger g,BigInteger h){
        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        list.add(g);
        list.add(h);
        return list;
    }
}
