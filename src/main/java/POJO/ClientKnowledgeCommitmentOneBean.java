package POJO;

import java.math.BigInteger;

/**
 * @projectName: Project
 * @package: POJO
 * @className: ClientKnowledgeCommitmentOneBean
 * @author: xjm
 * @description: 存放  客户端  第一次  知识承诺
 * @date: 2024/5/20 20:12
 * @version: 1.0
 */
public class ClientKnowledgeCommitmentOneBean {
    private BigInteger j1;
    private BigInteger j2;
    public ClientKnowledgeCommitmentOneBean(){
    }

    public BigInteger getJ1() {
        return j1;
    }

    public void setJ1(BigInteger j1) {
        this.j1 = j1;
    }

    public BigInteger getJ2() {
        return j2;
    }

    public void setJ2(BigInteger j2) {
        this.j2 = j2;
    }
}
