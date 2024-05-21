package POJO;

import java.math.BigInteger;

/**
 * @projectName: Project
 * @package: POJO
 * @className: ClientAnonymousCertificateBean
 * @author: xjm
 * @description: POJO for ClientAnonymousCertificate
 * @date: 2024/5/19 12:57
 * @version: 1.0
 */
public class ClientAnonymousCertificateBean extends ClientSecretParameterBean{
    private BigInteger A;
    private BigInteger y;
    public ClientAnonymousCertificateBean(){
    }

    public BigInteger getA() {
        return A;
    }

    public void setA(BigInteger a) {
        A = a;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }
}
