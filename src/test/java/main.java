import POJO.ClientParameterToServerOneBean;
import POJO.ClientParameterToServerTwoBean;
import POJO.ServerParameterToClientOneBean;
import POJO.ServerParameterToClientTwoBean;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @projectName: Project
 * @package: PACKAGE_NAME
 * @className: main
 * @author: xjm
 * @description: TODO
 * @date: 2024/5/20 21:24
 * @version: 1.0
 */
public class main {
    public static void main(String[] args) {

        ArrayList<BigInteger> cList = new ArrayList<>();
        ArrayList<BigInteger> sList = new ArrayList<>();

        ClientService clientService = new ClientService();
        ServerService serverService = new ServerService();
        //(pku,j1,j2,c,s1-s9)  生成知识承诺及知识签名相关参数
        ClientParameterToServerOneBean clientParameterToServerOneBean = clientService.prepareParameterClientoServerOne();
        //验证知识签名
        System.out.println(serverService.verifyClientParameterToServerOne(clientParameterToServerOneBean));

        //(A,y,t2) cl签名
        ServerParameterToClientOneBean serverParameterToClientOneBean = serverService.signClientSecretParameter(clientParameterToServerOneBean);
        //cl签名验证
        System.out.println(clientService.verifyClientSecretParameterSignature(serverParameterToClientOneBean));

        //（A',T,IC，SN，D） （c,s....）
        ClientParameterToServerTwoBean  clientParameterToServerTwoBean = clientService.prepareParameterClienToServerTwo();
        //验证spk2
        System.out.println(serverService.verifyClientParameterToServerTwo(clientParameterToServerTwoBean));

        //计算R1 R2
        clientService.caculatePartTwoKnowledgeCommitment(clientParameterToServerTwoBean.getClientKnowledgeCommitmentTwoBean(),clientParameterToServerTwoBean.getClientKnowledgeSignatureBean().getC());
//        System.out.println("c:"+clientParameterToServerTwoBean.getClientKnowledgeSignatureBean().getC());
//        System.out.println("R1:"+clientParameterToServerTwoBean.getClientKnowledgeCommitmentTwoBean().getR1());

        //验证SN
        System.out.println(serverService.verifyClientSN(clientParameterToServerTwoBean));

        ServerParameterToClientTwoBean serverParameterToClientTwoBean = serverService.signSecretParameterAndStateCode(clientParameterToServerTwoBean);
        System.out.println(clientService.verifySecretParameterAndStateCodeSignature(serverParameterToClientTwoBean, clientParameterToServerTwoBean.getClientKnowledgeCommitmentTwoBean()));
        clientService.updateClientAnonymousCertificate(serverParameterToClientTwoBean);
    }
}
