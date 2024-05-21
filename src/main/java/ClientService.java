import POJO.*;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @projectName: Project
 * @package: POJO
 * @className: clientService
 * @author: xjm
 * @description: client Service
 * @date: 2024/5/19 13:11
 * @version: 1.0
 */
public class ClientService {
    //用户秘密参数
    private ClientSecretParameterBean clientSecretParameterBean;
    //用户匿名证书
    private ClientAnonymousCertificateBean clientAnonymousCertificateBean;

    private BigInteger w1; //w*
    private BigInteger r;

    public ClientService(){
        //生成秘密参数 和 密钥对
        this.clientSecretParameterBean = initClientSecretParameter();
        this.clientAnonymousCertificateBean= new ClientAnonymousCertificateBean();
//        clientSecretParameterBean.setU(new BigInteger("727690177904081502511456248963"));   // secret key
//        clientSecretParameterBean.setPku(PublicParams.b.modPow(clientSecretParameterBean.getU(),PublicParams.sigma)); //public key
//        clientSecretParameterBean.setX(new BigInteger("481374665176814985286205925692"));
//        clientSecretParameterBean.setS(new BigInteger("770261130607177064210319694008"));
//        clientSecretParameterBean.setT1(new BigInteger("852710049569141685683026742344"));
//        clientSecretParameterBean.setI(new BigInteger("90"));
//        clientSecretParameterBean.setE(new BigInteger("25"));
//        clientSecretParameterBean.setD(new BigInteger("63"));
//        clientSecretParameterBean.setW(new BigInteger("1095551781250233210063574289668"));
//        System.out.println("u："+clientSecretParameterBean.getU());
//        System.out.println("x："+clientSecretParameterBean.getX());
//        System.out.println("s："+clientSecretParameterBean.getS());
//        System.out.println("t："+clientSecretParameterBean.getT1());
//        System.out.println("i："+clientSecretParameterBean.getI());
//        System.out.println("e："+clientSecretParameterBean.getE());
//        System.out.println("d："+clientSecretParameterBean.getD());
//        System.out.println("w："+clientSecretParameterBean.getW());
//        System.out.println("pku："+clientSecretParameterBean.getPku());
    }
    /**
     * @param : null
     * @return ClientSecretParameterBean
     * @author xjm
     * @description generate client secret parameters and key pair
     * @date 2024/5/20 19:48
     */
    private ClientSecretParameterBean initClientSecretParameter(){
        ClientSecretParameterBean clientSecretParameterBean = new ClientSecretParameterBean();
        Random rnd = new Random();
        clientSecretParameterBean.setU(new BigInteger(100,rnd));   // secret key
        clientSecretParameterBean.setPku(PublicParams.b.modPow(clientSecretParameterBean.getU(),PublicParams.sigma)); //public key
        clientSecretParameterBean.setX(new BigInteger(100,rnd));
        clientSecretParameterBean.setS(new BigInteger(100,rnd));
        clientSecretParameterBean.setT1(new BigInteger(100,rnd));
        clientSecretParameterBean.setI(new BigInteger(10,rnd));
        clientSecretParameterBean.setE(new BigInteger(10,rnd));
        clientSecretParameterBean.setD(new BigInteger(10,rnd));
        clientSecretParameterBean.setW(new BigInteger(100,rnd));
        return clientSecretParameterBean;
    }
    /**
     * @param :
     * @return ClientKnowledgeCommitmentOneBean
     * @author xjm
     * @description 计算知识承诺j1 j2
     * @date 2024/5/20 23:26
     */
    private ClientKnowledgeCommitmentOneBean caculateKnowledgeCommitment(){
        ClientKnowledgeCommitmentOneBean clientKnowledgeCommitmentOneBean = new ClientKnowledgeCommitmentOneBean();
        BigInteger j1 = PublicParams.b.modPow(clientSecretParameterBean.getT1(),PublicParams.sigma);
        BigInteger j2 = PublicParams.a.mod(PublicParams.n);
        j2 = j2.multiply(PublicParams.ax.modPow(clientSecretParameterBean.getX(),PublicParams.n));
        j2 = j2.multiply(PublicParams.as.modPow(clientSecretParameterBean.getS(),PublicParams.n));
        j2 = j2.multiply(PublicParams.at.modPow(clientSecretParameterBean.getT1(),PublicParams.n));
        j2 = j2.multiply(PublicParams.ai.modPow(clientSecretParameterBean.getI(),PublicParams.n));
        j2 = j2.multiply(PublicParams.ae.modPow(clientSecretParameterBean.getE(),PublicParams.n));
        j2 = j2.multiply(PublicParams.ad.modPow(clientSecretParameterBean.getD(),PublicParams.n));
        j2 = j2.multiply(PublicParams.h.modPow(clientSecretParameterBean.getW(),PublicParams.n));
        j2 = j2.mod(PublicParams.n);;
//        System.out.println("j1:"+j1);
//        System.out.println("j2:"+j2);
        clientKnowledgeCommitmentOneBean.setJ1(j1);
        clientKnowledgeCommitmentOneBean.setJ2(j2);
        return  clientKnowledgeCommitmentOneBean;
    }
    /**
     * @param :
     * @return ClientKnowledgeSignatureOneBean
     * @author xjm
     * @description 计算spk1
     * @date 2024/5/20 23:26
     */
    private ClientKnowledgeSignatureBean caculateKnowledgeSignature() {
        ClientKnowledgeSignatureBean clientKnowledgeSignatureBean = new ClientKnowledgeSignatureBean();

        ArrayList<BigInteger> baseNumList1 = BaseFunction.parameters2ArrayList(PublicParams.b);
        ArrayList<BigInteger> exponentList1 = BaseFunction.parameters2ArrayList(clientSecretParameterBean.getU());
        ArrayList<BigInteger> baseNumList2 = BaseFunction.parameters2ArrayList(PublicParams.b);
        ArrayList<BigInteger> exponentList2 = BaseFunction.parameters2ArrayList(clientSecretParameterBean.getT1());
        ArrayList<BigInteger> baseNumList3 = BaseFunction.parameters2ArrayList(
                PublicParams.ax,
                PublicParams.as,
                PublicParams.at,
                PublicParams.ai,
                PublicParams.ae,
                PublicParams.ad,
                PublicParams.h);
        ArrayList<BigInteger> exponentList3 = BaseFunction.parameters2ArrayList(
                clientSecretParameterBean.getX(),
                clientSecretParameterBean.getS(),
                clientSecretParameterBean.getT1(),
                clientSecretParameterBean.getI(),
                clientSecretParameterBean.getE(),
                clientSecretParameterBean.getD(),
                clientSecretParameterBean.getW());


        ArrayList<ArrayList<BigInteger>> baseNumList = new ArrayList<>();
        ArrayList<ArrayList<BigInteger>> exponentList = new ArrayList<>();
        baseNumList.add(baseNumList1);
        baseNumList.add(baseNumList2);
        baseNumList.add(baseNumList3);
        exponentList.add(exponentList1);
        exponentList.add(exponentList2);
        exponentList.add(exponentList3);

        ArrayList<ArrayList<BigInteger>> rList = BaseFunction.generateRList(exponentList);

        ArrayList<BigInteger> TList = new ArrayList<>();
        TList.add(BaseFunction.caculateT_P1(baseNumList.get(0),rList.get(0),PublicParams.sigma));
        TList.add(BaseFunction.caculateT_P1(baseNumList.get(1),rList.get(1),PublicParams.sigma));
        TList.add(BaseFunction.caculateT_P1(baseNumList.get(2),rList.get(2),PublicParams.n));

        BigInteger T = BigInteger.ZERO;
        for (int i = 0; i < TList.size(); i++) {
            T= T.add(TList.get(i));
        }

        BigInteger c = null;
        try {
            c = BaseFunction.getHash(T);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        ArrayList<ArrayList<BigInteger>> sList = BaseFunction.caculateSList(rList,exponentList,c);
        clientKnowledgeSignatureBean.setC(c);
        clientKnowledgeSignatureBean.setS(sList);
        return clientKnowledgeSignatureBean;
    }
    public ClientParameterToServerOneBean prepareParameterClientoServerOne(){
        ClientParameterToServerOneBean clientParameterToServerOneBean = new ClientParameterToServerOneBean();
        clientParameterToServerOneBean.setPku(clientSecretParameterBean.getPku());
        clientParameterToServerOneBean.setClientKnowledgeCommitmentOneBean(caculateKnowledgeCommitment());
        clientParameterToServerOneBean.setClientKnowledgeSignatureOneBean(caculateKnowledgeSignature());
        return clientParameterToServerOneBean;
    }

    public boolean verifyClientSecretParameterSignature(ServerParameterToClientOneBean serverParameterToClientOneBean){
        BigInteger left = serverParameterToClientOneBean.getA().modPow(serverParameterToClientOneBean.getY(), PublicParams.n);
//        System.out.println("left:"+left);
        BigInteger right = PublicParams.a.mod(PublicParams.n);
        right =right.multiply(PublicParams.ax.modPow(clientSecretParameterBean.getX(),PublicParams.n));
        right =right.multiply(PublicParams.as.modPow(clientSecretParameterBean.getS(),PublicParams.n));
        clientSecretParameterBean.setT1(clientSecretParameterBean.getT1().add(serverParameterToClientOneBean.getT2()));//更新参数t1 = t1+t2;
        right =right.multiply(PublicParams.at.modPow(clientSecretParameterBean.getT1(),PublicParams.n));
        right =right.multiply(PublicParams.ai.modPow(clientSecretParameterBean.getI(),PublicParams.n));
        right =right.multiply(PublicParams.ae.modPow(clientSecretParameterBean.getE(),PublicParams.n));
        right =right.multiply(PublicParams.ad.modPow(clientSecretParameterBean.getD(),PublicParams.n));
        right =right.multiply(PublicParams.h.modPow(clientSecretParameterBean.getW(),PublicParams.n));
        right =right.mod(PublicParams.n);
        generateClientAnonymousCertificate(serverParameterToClientOneBean);//生成匿名证书
        return left.equals(right);
    }

    private void generateClientAnonymousCertificate(ServerParameterToClientOneBean serverParameterToClientOneBean){
        this.clientAnonymousCertificateBean.setX(clientSecretParameterBean.getX());
        this.clientAnonymousCertificateBean.setS(clientSecretParameterBean.getS());
        this.clientAnonymousCertificateBean.setT1(clientSecretParameterBean.getT1());
        this.clientAnonymousCertificateBean.setI(clientSecretParameterBean.getI());
        this.clientAnonymousCertificateBean.setE(clientSecretParameterBean.getE());
        this.clientAnonymousCertificateBean.setD(clientSecretParameterBean.getD());
        this.clientAnonymousCertificateBean.setW(clientSecretParameterBean.getW());
        this.clientAnonymousCertificateBean.setA(serverParameterToClientOneBean.getA());
        this.clientAnonymousCertificateBean.setY(serverParameterToClientOneBean.getY());
    }

    private ClientKnowledgeCommitmentTwoBean caculatePartOneKnowledgeCommitment(){
        ClientKnowledgeCommitmentTwoBean  clientKnowledgeCommitmentTwoBean = new ClientKnowledgeCommitmentTwoBean();
        this.w1 = BaseFunction.getRndBigIntger(100);
        this.r  = BaseFunction.getRndBigIntger(100);
        BigInteger A1 = this.clientAnonymousCertificateBean.getA().multiply(PublicParams.h.modPow(this.w1,PublicParams.n)); //A*(h^(w*)mod n)
        BigInteger T = PublicParams.b1
                .modPow(this.clientAnonymousCertificateBean.getT1(),PublicParams.sigma)
                .multiply(PublicParams.b2.modPow(this.r,PublicParams.sigma))
                .mod(PublicParams.sigma);
        BigInteger ic = this.clientAnonymousCertificateBean.getI()
                .add(this.clientAnonymousCertificateBean.getE().modInverse(PublicParams.k))
                .mod(PublicParams.k);
        BigInteger SN = PublicParams.b1.modPow(ic.add(this.clientAnonymousCertificateBean.getS()).modInverse(PublicParams.q1),PublicParams.sigma)
                .multiply(PublicParams.b2.modPow(ic.add((this.clientAnonymousCertificateBean.getT1())).modInverse(PublicParams.q1),PublicParams.sigma)).mod(PublicParams.sigma);

        ArrayList<BigInteger> baseNumList = BaseFunction.parameters2ArrayList(
                PublicParams.a,
                PublicParams.ax,
                PublicParams.as,
                PublicParams.at,
                PublicParams.ai,
                PublicParams.ae,
                PublicParams.ad,
                PublicParams.h);
        ArrayList<BigInteger> exponentList = BaseFunction.parameters2ArrayList(
                this.clientAnonymousCertificateBean.getX(),
                this.clientAnonymousCertificateBean.getS(),
                this.clientAnonymousCertificateBean.getT1(),
                ic,
                this.clientAnonymousCertificateBean.getE().add(this.clientAnonymousCertificateBean.getD()),
                this.clientAnonymousCertificateBean.getD(),
                this.clientAnonymousCertificateBean.getW().add(this.clientAnonymousCertificateBean.getY().multiply(this.w1)));
        BigInteger D = BaseFunction.caculateD(baseNumList,exponentList,PublicParams.n);

        clientKnowledgeCommitmentTwoBean.setA(A1);
        clientKnowledgeCommitmentTwoBean.setT(T);
        clientKnowledgeCommitmentTwoBean.setIc(ic);
        clientKnowledgeCommitmentTwoBean.setSN(SN);
        clientKnowledgeCommitmentTwoBean.setD(D);

        return clientKnowledgeCommitmentTwoBean;
    }

    public void caculatePartTwoKnowledgeCommitment(ClientKnowledgeCommitmentTwoBean clientKnowledgeCommitmentTwoBean,BigInteger c){
        clientKnowledgeCommitmentTwoBean.setR1(((clientKnowledgeCommitmentTwoBean.getIc().add(clientAnonymousCertificateBean.getS())).modInverse(PublicParams.q1)).subtract(c.multiply(clientAnonymousCertificateBean.getT1())));
        clientKnowledgeCommitmentTwoBean.setR2(((clientKnowledgeCommitmentTwoBean.getIc().add(clientAnonymousCertificateBean.getT1())).modInverse(PublicParams.q1)).subtract(c.multiply(this.r)));
    }
    private ClientKnowledgeSignatureBean caculateKnowledgeSignature(ClientKnowledgeCommitmentTwoBean clientKnowledgeCommitmentTwoBean){
        ClientKnowledgeSignatureBean clientKnowledgeSignatureBean = new ClientKnowledgeSignatureBean();
        ArrayList<ArrayList<BigInteger>> sList;
        BigInteger T;
        BigInteger c;
        BigInteger alpha = clientKnowledgeCommitmentTwoBean.getIc()
                .add(this.clientAnonymousCertificateBean.getS())
                .multiply((clientKnowledgeCommitmentTwoBean.getIc().add(this.clientAnonymousCertificateBean.getT1())).modInverse(PublicParams.q1))
                .mod(PublicParams.q1);
        BigInteger beta = clientKnowledgeCommitmentTwoBean.getIc().add(this.clientAnonymousCertificateBean.getT1())
                .multiply((clientKnowledgeCommitmentTwoBean.getIc().add(this.clientAnonymousCertificateBean.getS())).modInverse(PublicParams.q1))
                .mod(PublicParams.q1);
        BigInteger m = clientKnowledgeCommitmentTwoBean.getIc()
                .multiply(this.clientAnonymousCertificateBean.getE())
                .subtract(this.clientAnonymousCertificateBean.getI().multiply(this.clientAnonymousCertificateBean.getE()).subtract(BigInteger.ONE))
                .divide(PublicParams.k);
        BigInteger ec = this.clientAnonymousCertificateBean.getE().add(this.clientAnonymousCertificateBean.getD());

        ArrayList<ArrayList<BigInteger>> rList = new ArrayList<>(); //二维的BigInteger 数组
        ArrayList<ArrayList<BigInteger>> secParamList = new ArrayList<>(); //二维的秘密参数数组

        ArrayList<BigInteger> secParamList1 = BaseFunction.parameters2ArrayList(
                this.clientAnonymousCertificateBean.getY(),
                this.clientAnonymousCertificateBean.getX(),
                this.clientAnonymousCertificateBean.getS(),
                this.clientAnonymousCertificateBean.getT1(),
                this.clientAnonymousCertificateBean.getI(),
                this.clientAnonymousCertificateBean.getE(),
                this.clientAnonymousCertificateBean.getD(),
                this.clientAnonymousCertificateBean.getW().add(this.clientAnonymousCertificateBean.getY().multiply(this.w1)));

        ArrayList<BigInteger> pubParamList1 = BaseFunction.parameters2ArrayList(
                clientKnowledgeCommitmentTwoBean.getA(),
                PublicParams.ax,
                PublicParams.as,
                PublicParams.at,
                PublicParams.ai,
                PublicParams.ae,
                PublicParams.ad,
                PublicParams.h);

        ArrayList<BigInteger> secParamList2 = BaseFunction.parameters2ArrayList(
                this.clientAnonymousCertificateBean.getT1(),
                r);
        ArrayList<BigInteger> pubParamList2 =BaseFunction.parameters2ArrayList(
                PublicParams.b1,
                PublicParams.b2);

        ArrayList<BigInteger> secParamList3 = BaseFunction.parameters2ArrayList(
                this.clientAnonymousCertificateBean.getS(),
                alpha);
        ArrayList<BigInteger> pubParamList3 = BaseFunction.parameters2ArrayList(
                clientKnowledgeCommitmentTwoBean.getSN(),
                PublicParams.b2);
        ArrayList<BigInteger> secParamList4 = BaseFunction.parameters2ArrayList(
                this.clientAnonymousCertificateBean.getT1(),
                beta);
        ArrayList<BigInteger> pubParamList4 = BaseFunction.parameters2ArrayList(
                clientKnowledgeCommitmentTwoBean.getSN(),
                PublicParams.b1);
        ArrayList<BigInteger> secParamList5 = BaseFunction.parameters2ArrayList(
                this.clientAnonymousCertificateBean.getE(),
                this.clientAnonymousCertificateBean.getE().multiply(clientAnonymousCertificateBean.getI()),
                m);
        ArrayList<BigInteger> pubParamList5 = BaseFunction.parameters2ArrayList(
                PublicParams.a.modPow(clientKnowledgeCommitmentTwoBean.getIc(),PublicParams.n),
                PublicParams.a,
                PublicParams.a.modPow(PublicParams.k,PublicParams.n));
        ArrayList<BigInteger> secParamList6 = BaseFunction.parameters2ArrayList(
                ec,
                this.clientAnonymousCertificateBean.getE(),
                this.clientAnonymousCertificateBean.getD());
        ArrayList<BigInteger> pubParamList6 = BaseFunction.parameters2ArrayList(
                PublicParams.b,
                PublicParams.b,
                PublicParams.b);
        ArrayList<BigInteger> secParamList7 = BaseFunction.parameters2ArrayList(
                this.clientAnonymousCertificateBean.getX(),
                this.clientAnonymousCertificateBean.getS(),
                this.clientAnonymousCertificateBean.getT1(),
                this.clientAnonymousCertificateBean.getE().add(this.clientAnonymousCertificateBean.getD()),
                this.clientAnonymousCertificateBean.getD(),
                this.clientAnonymousCertificateBean.getW().add(this.clientAnonymousCertificateBean.getY().multiply(this.w1)));
        ArrayList<BigInteger> pubParamList7 = BaseFunction.parameters2ArrayList(
                PublicParams.ax,
                PublicParams.as,
                PublicParams.at,
                PublicParams.ae,
                PublicParams.ad,
                PublicParams.h);
        secParamList.add(secParamList1);
        secParamList.add(secParamList2);
        secParamList.add(secParamList3);
        secParamList.add(secParamList4);
        secParamList.add(secParamList5);
        secParamList.add(secParamList6);
        secParamList.add(secParamList7);

        rList = BaseFunction.generateRList(secParamList);
        T = BaseFunction.caculateT_P2(pubParamList1, rList.get(0), PublicParams.n);
        T = T.add(BaseFunction.caculateT_P1(pubParamList2, rList.get(1), PublicParams.sigma));
        T = T.add(BaseFunction.caculateT_P2(pubParamList3, rList.get(2), PublicParams.sigma));
        T = T.add(BaseFunction.caculateT_P2(pubParamList4, rList.get(3), PublicParams.sigma));
        T = T.add(BaseFunction.caculateT_P2(pubParamList5, rList.get(4), PublicParams.n));
        T = T.add(BaseFunction.caculateT_P2(pubParamList6, rList.get(5), PublicParams.sigma));
        T = T.add(BaseFunction.caculateT_P1(pubParamList7, rList.get(6), PublicParams.n));
//        System.out.println("T1:"+BaseFunction.caculateT_P2(pubParamList1, rList.get(0), PublicParams.n));
//        System.out.println("T2:"+BaseFunction.caculateT_P1(pubParamList2, rList.get(1), PublicParams.sigma));
//        System.out.println("T3:"+BaseFunction.caculateT_P2(pubParamList3, rList.get(2), PublicParams.sigma));
//        System.out.println("T4:"+BaseFunction.caculateT_P2(pubParamList4, rList.get(3), PublicParams.sigma));
//        System.out.println("T5:"+BaseFunction.caculateT_P2(pubParamList5, rList.get(4), PublicParams.n));
//        System.out.println("T6:"+BaseFunction.caculateT_P2(pubParamList6, rList.get(5), PublicParams.sigma));
//        System.out.println("T7:"+BaseFunction.caculateT_P1(pubParamList7, rList.get(6), PublicParams.n));
        try {
            c = BaseFunction.getHash(T);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        sList = BaseFunction.caculateSList(rList, secParamList, c);
        clientKnowledgeSignatureBean.setC(c);
        clientKnowledgeSignatureBean.setS(sList);
        return clientKnowledgeSignatureBean;
    }
    public ClientParameterToServerTwoBean prepareParameterClienToServerTwo(){
        ClientParameterToServerTwoBean clientParameterToServerTwoBean = new ClientParameterToServerTwoBean();
        clientParameterToServerTwoBean.setClientKnowledgeCommitmentTwoBean(this.caculatePartOneKnowledgeCommitment());
        clientParameterToServerTwoBean.setClientKnowledgeSignatureBean(this.caculateKnowledgeSignature(clientParameterToServerTwoBean.getClientKnowledgeCommitmentTwoBean()));
        return  clientParameterToServerTwoBean;
    }
    public boolean verifySecretParameterAndStateCodeSignature(ServerParameterToClientTwoBean serverParameterToClientTwoBean,ClientKnowledgeCommitmentTwoBean clientKnowledgeCommitmentTwoBean){
        return serverParameterToClientTwoBean.getAD().modPow(serverParameterToClientTwoBean.getY1(),PublicParams.n).equals(clientKnowledgeCommitmentTwoBean.getD());
    }
    public void updateClientAnonymousCertificate(ServerParameterToClientTwoBean serverParameterToClientTwoBean){
        this.clientAnonymousCertificateBean.setY(serverParameterToClientTwoBean.getY1());
        this.clientAnonymousCertificateBean.setA(serverParameterToClientTwoBean.getAD());
        this.clientAnonymousCertificateBean.setI(this.clientAnonymousCertificateBean.getI()
                .add(this.clientAnonymousCertificateBean.getE().modInverse(PublicParams.k))
                .mod(PublicParams.k));
        this.clientAnonymousCertificateBean.setE(this.clientAnonymousCertificateBean.getE().add(this.clientAnonymousCertificateBean.getD()));
        this.clientAnonymousCertificateBean.setW(this.w1);
    }

}
