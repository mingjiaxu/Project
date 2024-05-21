package POJO;

import java.math.BigInteger;

/**
 * @projectName: Project
 * @package: POJO
 * @className: ClientSecretParameterBean
 * @author: xjm
 * @description: POJO for user‘s secret parameters
 * @date: 2024/5/19 12:55
 * @version: 1.0
 */
public class ClientSecretParameterBean{
    private BigInteger u;    //私钥
    private BigInteger x;
    private BigInteger s;
    private BigInteger t1;
    private BigInteger i;
    private BigInteger e;
    private BigInteger d;
    private BigInteger w;
    private BigInteger pku; //公钥


    public ClientSecretParameterBean() {
    }

    public BigInteger getU() {
        return u;
    }

    public void setU(BigInteger u) {
        this.u = u;
    }

    public BigInteger getX() {
        return x;
    }

    public void setX(BigInteger x) {
        this.x = x;
    }

    public BigInteger getS() {
        return s;
    }

    public void setS(BigInteger s) {
        this.s = s;
    }

    public BigInteger getT1() {
        return t1;
    }

    public void setT1(BigInteger t1) {
        this.t1 = t1;
    }

    public BigInteger getI() {
        return i;
    }

    public void setI(BigInteger i) {
        this.i = i;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getW() {
        return w;
    }

    public void setW(BigInteger w) {
        this.w = w;
    }

    public BigInteger getPku() {
        return pku;
    }

    public void setPku(BigInteger pku) {
        this.pku = pku;
    }




}
