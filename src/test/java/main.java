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
 * @description: 主函数部分
 * @date: 2024/5/20 21:24
 * @version: 1.0
 */
public class main {
    public static void main(String[] args) {

//        ArrayList<BigInteger> cList = new ArrayList<>();
//        ArrayList<BigInteger> sList = new ArrayList<>();

        ClientService clientService = new ClientService();
        ServerService serverService = new ServerService();

        //客户端           生成知识承诺及知识签名相关参数(pku,j1,j2,c,s1-s9)  存放在 clientParameterToServerOneBean中
        ClientParameterToServerOneBean clientParameterToServerOneBean = clientService.prepareParameterClientoServerOne();

        //服务器端         验证知识签名SPK1      输入参数为  clientParameterToServerOneBean
        System.out.println(serverService.verifyClientParameterToServerOne(clientParameterToServerOneBean));

        //服务器端          进行CL签名生成(A,y,t2)                存放在 serverParameterToClientOneBean中
        ServerParameterToClientOneBean serverParameterToClientOneBean = serverService.signClientSecretParameter(clientParameterToServerOneBean);

        //客户端           验证CL签名         输入参数  serverParameterToClientOneBean
        System.out.println(clientService.verifyClientSecretParameterSignature(serverParameterToClientOneBean));


        //客户端       生成知识承诺 知识签名 以及相关参数 包括(A',T,ic,SN,D) (c,s1,s2....)    存放在clientParameterToServerTwoBean中
        ClientParameterToServerTwoBean  clientParameterToServerTwoBean = clientService.prepareParameterClienToServerTwo();

        //服务器端      验证知识签名SPK2  输入参数clientParameterToServerTwoBean
        System.out.println(serverService.verifyClientParameterToServerTwo(clientParameterToServerTwoBean));

        //客户端        计算算R1 R2  与(A',T,ic,SN,D)          一同存放在clientParameterToServerTwoBean中
        clientService.caculatePartTwoKnowledgeCommitment(clientParameterToServerTwoBean.getClientKnowledgeCommitmentTwoBean(),clientParameterToServerTwoBean.getClientKnowledgeSignatureBean().getC());

        //服务器端    验证SN              输入clientParameterToServerTwoBean
        System.out.println(serverService.verifyClientSN(clientParameterToServerTwoBean));
        //服务器端   签名 秘密证书和状态码
        ServerParameterToClientTwoBean serverParameterToClientTwoBean = serverService.signSecretParameterAndStateCode(clientParameterToServerTwoBean);
        //客户端       验证
        System.out.println(clientService.verifySecretParameterAndStateCodeSignature(serverParameterToClientTwoBean, clientParameterToServerTwoBean.getClientKnowledgeCommitmentTwoBean()));
        //客户端     更新匿名证书
        clientService.updateClientAnonymousCertificate(serverParameterToClientTwoBean);
    }
}
