import POJO.*;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @projectName: Project
 * @package: PACKAGE_NAME
 * @className: serverService
 * @author: xjm
 * @description:
 * @date: 2024/5/19 13:12
 * @version: 1.0
 */
public class ServerService {
    // p and q
    private static final BigInteger p = new BigInteger("6795510185750818210375461788482983988856832854980210368754685656186571714353434168850192396968899899947007851456348732281839169134903594729430671486257347");//服务器私有
    private static final BigInteger q = new BigInteger("11969727164351273626888258109835136108332300337345090579231533034665387563974043081963482658868053949960231294215692334687751977599787735850052533646296899");//服务器私有

    /**
     * @param clientParameterToServerOneBean:
     * @return boolean
     * @author xjm
     * @description 验证知识签名SPK1
     * @date 2024/5/20 23:25
     */
    public boolean verifyClientParameterToServerOne(ClientParameterToServerOneBean clientParameterToServerOneBean){
        BigInteger pku = clientParameterToServerOneBean.getPku();
        ClientKnowledgeCommitmentOneBean clientKnowledgeCommitmentOneBean = clientParameterToServerOneBean.getClientKnowledgeCommitmentOneBean(); //知识承诺 J1  J2
        ClientKnowledgeSignatureBean clientKnowledgeSignatureBean = clientParameterToServerOneBean.getClientKnowledgeSignatureOneBean();  //SPK1

        ArrayList<BigInteger> baseNumList1 = BaseFunction.parameters2ArrayList(PublicParams.b);
        ArrayList<BigInteger> exponentList1 = clientKnowledgeSignatureBean.getS().get(0);
        ArrayList<BigInteger> baseNumList2 = BaseFunction.parameters2ArrayList(PublicParams.b);
        ArrayList<BigInteger> exponentList2 = clientKnowledgeSignatureBean.getS().get(1);
        ArrayList<BigInteger> baseNumList3 = BaseFunction.parameters2ArrayList(
                PublicParams.ax,
                PublicParams.as,
                PublicParams.at,
                PublicParams.ai,
                PublicParams.ae,
                PublicParams.ad,
                PublicParams.h);
        ArrayList<BigInteger> exponentList3 = clientKnowledgeSignatureBean.getS().get(2);

        ArrayList<BigInteger> TList = new ArrayList<>();

        TList.add(pku.modPow(clientKnowledgeSignatureBean.getC(),PublicParams.sigma)
                .multiply(BaseFunction.caculateT_P1(baseNumList1,exponentList1,PublicParams.sigma)).mod(PublicParams.sigma));
        TList.add(clientKnowledgeCommitmentOneBean.getJ1().modPow(clientKnowledgeSignatureBean.getC(),PublicParams.sigma)
                .multiply(BaseFunction.caculateT_P1(baseNumList2,exponentList2,PublicParams.sigma)).mod(PublicParams.sigma));
        TList.add(clientKnowledgeCommitmentOneBean.getJ2()
                .multiply(PublicParams.a.modInverse(PublicParams.n))
                .modPow(clientKnowledgeSignatureBean.getC(),PublicParams.n)
                .multiply(BaseFunction.caculateT_P1(baseNumList3,exponentList3,PublicParams.n)).mod(PublicParams.n));

        BigInteger T = BigInteger.ZERO;
        for (int i = 0; i < TList.size(); i++) {
            T = T.add(TList.get(i));
        }

        BigInteger c = null;
        try {
            c = BaseFunction.getHash(T);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return c.equals(clientKnowledgeSignatureBean.getC());
    }
    /**
     * @param clientParameterToServerOneBean:
     * @return ServerParameterToClientOneBean
     * @author xjm
     * @description TODO
     * @date 2024/5/20 23:41
     */
    public ServerParameterToClientOneBean signClientSecretParameter(ClientParameterToServerOneBean clientParameterToServerOneBean){
        ServerParameterToClientOneBean serverParameterToClientOneBean = new ServerParameterToClientOneBean();
        Random rnd = new Random();
        BigInteger y = BigInteger.probablePrime(100,rnd);
        BigInteger t2 = BigInteger.probablePrime(100,rnd);
        BigInteger A = clientParameterToServerOneBean.getClientKnowledgeCommitmentOneBean().getJ2()
                .multiply(PublicParams.at.modPow(t2,PublicParams.n))
                .modPow(y.modInverse((p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE))),PublicParams.n);

        serverParameterToClientOneBean.setA(A);
        serverParameterToClientOneBean.setT2(t2);
        serverParameterToClientOneBean.setY(y);
        return serverParameterToClientOneBean;
    }

    public boolean verifyClientParameterToServerTwo(ClientParameterToServerTwoBean clientParameterToServerTwoBean){

        ClientKnowledgeCommitmentTwoBean clientKnowledgeCommitmentTwoBean = clientParameterToServerTwoBean.getClientKnowledgeCommitmentTwoBean();
        ClientKnowledgeSignatureBean clientKnowledgeSignatureBean = clientParameterToServerTwoBean.getClientKnowledgeSignatureBean();

        ArrayList<BigInteger> pubParamList1 = BaseFunction.parameters2ArrayList(
                clientKnowledgeCommitmentTwoBean.getA(),
                PublicParams.ax,
                PublicParams.as,
                PublicParams.at,
                PublicParams.ai,
                PublicParams.ae,
                PublicParams.ad,
                PublicParams.h);
        ArrayList<BigInteger> pubParamList2 = BaseFunction.parameters2ArrayList(
                PublicParams.b1,
                PublicParams.b2);
        ArrayList<BigInteger> pubParamList3 = BaseFunction.parameters2ArrayList(
                clientKnowledgeCommitmentTwoBean.getSN(),
                PublicParams.b2);
        ArrayList<BigInteger> pubParamList4 = BaseFunction.parameters2ArrayList(
                clientKnowledgeCommitmentTwoBean.getSN(),
                PublicParams.b1);
        ArrayList<BigInteger> pubParamList5 = BaseFunction.parameters2ArrayList(
                PublicParams.a.modPow(clientKnowledgeCommitmentTwoBean.getIc(),PublicParams.n),
                PublicParams.a,
                PublicParams.a.modPow(PublicParams.k,PublicParams.n));
        ArrayList<BigInteger> pubParamList6 = BaseFunction.parameters2ArrayList(
                PublicParams.b,
                PublicParams.b,
                PublicParams.b);
        ArrayList<BigInteger> pubParamList7 = BaseFunction.parameters2ArrayList(
                PublicParams.ax,
                PublicParams.as,
                PublicParams.at,
                PublicParams.ae,
                PublicParams.ad,
                PublicParams.h);

        BigInteger T1 = PublicParams.a.modPow(clientKnowledgeSignatureBean.getC(), PublicParams.n)
                .multiply(BaseFunction.caculateT_P2(pubParamList1, clientKnowledgeSignatureBean.getS().get(0), PublicParams.n))
                .mod(PublicParams.n);

        BigInteger T2 = clientKnowledgeCommitmentTwoBean.getT()
                .modPow(clientKnowledgeSignatureBean.getC(), PublicParams.sigma)
                .multiply(BaseFunction.caculateT_P1(pubParamList2, clientKnowledgeSignatureBean.getS().get(1), PublicParams.sigma))
                .mod(PublicParams.sigma);

        BigInteger T3 = PublicParams.b1.multiply((clientKnowledgeCommitmentTwoBean.getSN().modPow(clientKnowledgeCommitmentTwoBean.getIc(), PublicParams.sigma)).modInverse(PublicParams.sigma)).modPow(clientKnowledgeSignatureBean.getC(), PublicParams.sigma)
                .multiply(BaseFunction.caculateT_P2(pubParamList3, clientKnowledgeSignatureBean.getS().get(2), PublicParams.sigma))
                .mod(PublicParams.sigma);

        BigInteger T4 = PublicParams.b2.multiply((clientKnowledgeCommitmentTwoBean.getSN().modPow(clientKnowledgeCommitmentTwoBean.getIc(), PublicParams.sigma)).modInverse(PublicParams.sigma)).modPow(clientKnowledgeSignatureBean.getC(), PublicParams.sigma)
                .multiply(BaseFunction.caculateT_P2(pubParamList4, clientKnowledgeSignatureBean.getS().get(3), PublicParams.sigma))
                .mod(PublicParams.sigma);

        BigInteger T5 = (PublicParams.a.modPow(clientKnowledgeSignatureBean.getC(), PublicParams.n))
                .multiply(BaseFunction.caculateT_P2(pubParamList5, clientKnowledgeSignatureBean.getS().get(4), PublicParams.n))
                .mod(PublicParams.n);

        BigInteger T6 = (BigInteger.ONE.modPow(clientKnowledgeSignatureBean.getC(), PublicParams.sigma))
                .multiply(BaseFunction.caculateT_P2(pubParamList6, clientKnowledgeSignatureBean.getS().get(5), PublicParams.sigma));
        BigInteger T7 = (clientKnowledgeCommitmentTwoBean.getD().multiply((PublicParams.a.mod(PublicParams.n).multiply(PublicParams.ai.modPow(clientKnowledgeCommitmentTwoBean.getIc(), PublicParams.n))).modInverse(PublicParams.n)).modPow(clientKnowledgeSignatureBean.getC(), PublicParams.n))
                .multiply(BaseFunction.caculateT_P1(pubParamList7, clientKnowledgeSignatureBean.getS().get(6), PublicParams.n))
                .mod(PublicParams.n);
        BigInteger T = T7.add(T1).add(T2).add(T3).add(T4).add(T5).add(T6);
//        System.out.println("T1':"+T1);
//        System.out.println("T2':"+T2);
//        System.out.println("T3':"+T3);
//        System.out.println("T4':"+T4);
//        System.out.println("T5':"+T5);
//        System.out.println("T6':"+T6);
//        System.out.println("T7':"+T7);
        BigInteger c;
        try {
            c = BaseFunction.getHash(T);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return c.equals(clientKnowledgeSignatureBean.getC());
    }

    public boolean verifyClientSN(ClientParameterToServerTwoBean clientParameterToServerTwoBean){
        BigInteger c = clientParameterToServerTwoBean.getClientKnowledgeSignatureBean().getC();
        ClientKnowledgeCommitmentTwoBean clientKnowledgeCommitmentTwoBean = clientParameterToServerTwoBean.getClientKnowledgeCommitmentTwoBean();
        BigInteger temp = clientKnowledgeCommitmentTwoBean.getT().modPow(c,PublicParams.sigma)
                .multiply(PublicParams.b1.modPow(clientKnowledgeCommitmentTwoBean.getR1(),PublicParams.sigma))
                .multiply(PublicParams.b2.modPow(clientKnowledgeCommitmentTwoBean.getR2(),PublicParams.sigma))
                .mod(PublicParams.sigma);
        return temp.equals(clientKnowledgeCommitmentTwoBean.getSN());
    }

    public ServerParameterToClientTwoBean signSecretParameterAndStateCode(ClientParameterToServerTwoBean clientParameterToServerTwoBean){
        ServerParameterToClientTwoBean serverParameterToClientTwoBean = new ServerParameterToClientTwoBean();
        BigInteger y1 = BigInteger.probablePrime(100,new Random());
        BigInteger AD = clientParameterToServerTwoBean.getClientKnowledgeCommitmentTwoBean().getD().modPow(y1.modInverse((this.p.subtract(BigInteger.ONE)).multiply(this.q.subtract(BigInteger.ONE))),PublicParams.n).mod(PublicParams.n);
        serverParameterToClientTwoBean.setY1(y1);
        serverParameterToClientTwoBean.setAD(AD);
        return serverParameterToClientTwoBean;
    }
}
