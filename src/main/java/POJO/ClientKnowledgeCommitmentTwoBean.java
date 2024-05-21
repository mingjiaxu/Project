package POJO;

import java.math.BigInteger;

/**
 * @projectName: Project
 * @package: POJO
 * @className: ClientKnowledgeCommitmentTwoBean
 * @author: xjm
 * @description: 存放  客户端  第二次  知识承诺  (A',T,ic,SN,D,R1,R2)
 * @date: 2024/5/20 20:12
 * @version: 1.0
 */
public class ClientKnowledgeCommitmentTwoBean {
    private BigInteger A;
    private BigInteger T;
    private BigInteger ic;
    private BigInteger SN;
    private BigInteger D;
    private BigInteger R1;
    private BigInteger R2;

    public BigInteger getA() {
        return A;
    }

    public void setA(BigInteger a) {
        A = a;
    }

    public BigInteger getT() {
        return T;
    }

    public void setT(BigInteger t) {
        T = t;
    }

    public BigInteger getIc() {
        return ic;
    }

    public void setIc(BigInteger ic) {
        this.ic = ic;
    }

    public BigInteger getSN() {
        return SN;
    }

    public void setSN(BigInteger SN) {
        this.SN = SN;
    }

    public BigInteger getD() {
        return D;
    }

    public void setD(BigInteger d) {
        D = d;
    }

    public BigInteger getR1() {
        return R1;
    }

    public void setR1(BigInteger r1) {
        R1 = r1;
    }

    public BigInteger getR2() {
        return R2;
    }

    public void setR2(BigInteger r2) {
        R2 = r2;
    }
}
