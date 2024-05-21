package POJO;

import java.math.BigInteger;

/**
 * @projectName: Project
 * @package: POJO
 * @className: ClientParameterToServerOneBean
 * @author: xjm
 * @description: 存放客户端第一次向服务器传递的所有参数,包括（PKu，（j1,j2）,(c,s1,s2....)）
 * @date: 2024/5/20 20:07
 * @version: 1.0
 */
public class ClientParameterToServerOneBean {
    private BigInteger pku;                                 //公钥
    private ClientKnowledgeCommitmentOneBean clientKnowledgeCommitmentOneBean; //知识承诺 J1  J2
    private ClientKnowledgeSignatureBean clientKnowledgeSignatureBean;  //SPK1

    public BigInteger getPku() {
        return pku;
    }

    public void setPku(BigInteger pku) {
        this.pku = pku;
    }

    public ClientKnowledgeCommitmentOneBean getClientKnowledgeCommitmentOneBean() {
        return clientKnowledgeCommitmentOneBean;
    }

    public void setClientKnowledgeCommitmentOneBean(ClientKnowledgeCommitmentOneBean clientKnowledgeCommitmentOneBean) {
        this.clientKnowledgeCommitmentOneBean = clientKnowledgeCommitmentOneBean;
    }

    public ClientKnowledgeSignatureBean getClientKnowledgeSignatureOneBean() {
        return clientKnowledgeSignatureBean;
    }

    public void setClientKnowledgeSignatureOneBean(ClientKnowledgeSignatureBean clientKnowledgeSignatureBean) {
        this.clientKnowledgeSignatureBean = clientKnowledgeSignatureBean;
    }
}
