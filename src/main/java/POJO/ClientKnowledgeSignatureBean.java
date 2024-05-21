package POJO;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @projectName: Project
 * @package: POJO
 * @className: ClientKnowledgeSignatureOneBean
 * @author: xjm
 * @description: 存放  客户端  知识签名
 * @date: 2024/5/20 20:09
 * @version: 1.0
 */
public class ClientKnowledgeSignatureBean {
    private BigInteger c;
    private ArrayList<ArrayList<BigInteger>> s;
    public ClientKnowledgeSignatureBean(){
    }

    public BigInteger getC() {
        return c;
    }

    public void setC(BigInteger c) {
        this.c = c;
    }

    public ArrayList<ArrayList<BigInteger>> getS() {
        return s;
    }

    public void setS(ArrayList<ArrayList<BigInteger>> s) {
        this.s = s;
    }
}
