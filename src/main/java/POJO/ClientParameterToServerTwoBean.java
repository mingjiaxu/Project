package POJO;

/**
 * @projectName: Project
 * @package: POJO
 * @className: ClientParameterToServerTwoBean
 * @author: xjm
 * @description: TODO
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
