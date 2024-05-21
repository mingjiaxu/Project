package POJO;

/**
 * @projectName: Project
 * @package: POJO
 * @className: ClientParameterToServerTwoBean
 * @author: xjm
 * @description: 存放客户端第二次向服务器传递的所有参数,包括（（A',T,ic,SN,D）,(c,s1,s2,s3...)）
 * @date: 2024/5/21 11:05
 * @version: 1.0
 */
public class ClientParameterToServerTwoBean {
    private ClientKnowledgeCommitmentTwoBean clientKnowledgeCommitmentTwoBean;
    private ClientKnowledgeSignatureBean clientKnowledgeSignatureBean;

    public ClientKnowledgeCommitmentTwoBean getClientKnowledgeCommitmentTwoBean() {
        return clientKnowledgeCommitmentTwoBean;
    }

    public void setClientKnowledgeCommitmentTwoBean(ClientKnowledgeCommitmentTwoBean clientKnowledgeCommitmentTwoBean) {
        this.clientKnowledgeCommitmentTwoBean = clientKnowledgeCommitmentTwoBean;
    }

    public ClientKnowledgeSignatureBean getClientKnowledgeSignatureBean() {
        return clientKnowledgeSignatureBean;
    }

    public void setClientKnowledgeSignatureBean(ClientKnowledgeSignatureBean clientKnowledgeSignatureBean) {
        this.clientKnowledgeSignatureBean = clientKnowledgeSignatureBean;
    }
}
