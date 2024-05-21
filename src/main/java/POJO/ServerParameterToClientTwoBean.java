package POJO;

import java.math.BigInteger;

/**
 * @projectName: Project
 * @package: POJO
 * @className: ServerParameterToClientTwoBean
 * @author: xjm
 * @description: 存放服务器第二次向服务器传递的所有参数,包括（y',AD）
 * @date: 2024/5/21 17:14
 * @version: 1.0
 */
public class ServerParameterToClientTwoBean {
    private BigInteger Y1;
    private BigInteger AD;
    public BigInteger getY1() {
        return Y1;
    }

    public void setY1(BigInteger y1) {
        Y1 = y1;
    }

    public BigInteger getAD() {
        return AD;
    }

    public void setAD(BigInteger AD) {
        this.AD = AD;
    }
}
