package POJO;

import java.math.BigInteger;

/**
 * @projectName: Project
 * @package: POJO
 * @className: ServerParameterToClientOneBean
 * @author: xjm
 * @description: 存放服务器第一次向服务器传递的所有参数,包括（y,t2,A）
 * @date: 2024/5/20 23:32
 * @version: 1.0
 */
public class ServerParameterToClientOneBean {
    private BigInteger y;
    private BigInteger t2;
    private BigInteger A;

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }

    public BigInteger getT2() {
        return t2;
    }

    public void setT2(BigInteger t2) {
        this.t2 = t2;
    }

    public BigInteger getA() {
        return A;
    }

    public void setA(BigInteger a) {
        A = a;
    }
}
