package com.atteg.MeasurementPersistJPA;

import com.atteg.MeasurementPersistJPA.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.atteg.MeasurementPersistJPA.model.builder.DataParser;
import com.atteg.MeasurementPersistJPA.model.builder.MeasurementBuilder;
import com.atteg.MeasurementPersistJPA.repository.LeftArmRepository;
import com.atteg.MeasurementPersistJPA.repository.LeftLegRepository;
import com.atteg.MeasurementPersistJPA.repository.MeasurementRepository;
import com.atteg.MeasurementPersistJPA.repository.ResultsRepository;
import com.atteg.MeasurementPersistJPA.repository.RightArmRepository;
import com.atteg.MeasurementPersistJPA.repository.RightLegRepository;
import com.atteg.MeasurementPersistJPA.repository.TorsoRepository;
import com.atteg.MeasurementPersistJPA.repository.UserRepository;

@SpringBootApplication
public class MeasurementPersistJpaApplication implements CommandLineRunner {

    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    LeftLegRepository leftLegRepository;
    @Autowired
    LeftArmRepository leftArmRepository;
    @Autowired
    RightLegRepository rightLegRepository;
    @Autowired
    RightArmRepository rightArmRepository;
    @Autowired
    TorsoRepository torsoRepository;
    @Autowired
    ResultsRepository resultsRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ConfigurableApplicationContext context;

    private final MeasurementBuilder measurementBuilder = new MeasurementBuilder(new DataParser());

    public static void main(String[] args) {
        SpringApplication.run(MeasurementPersistJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*
        leftLegRepository.deleteAllInBatch();
        leftArmRepository.deleteAllInBatch();
        rightArmRepository.deleteAllInBatch();
        rightLegRepository.deleteAllInBatch();
        torsoRepository.deleteAllInBatch();
        resultsRepository.deleteAllInBatch();
        measurementRepository.deleteAllInBatch();
        

        String s = "{0,16,~0,1,~1,1,~2,1,MO,\"MC-780\",ID,\"0000000000000000\",St,0,Da,\"18/10/2017\",TI,\"11:39\",Bt,0,GE,1,AG,26,Hm,189.0,Pt,1.5,Wk,112.1,FW,30.1,fW,33.7,MW,78.4,mW,74.6,sW,1,bW,3.8,wW,54.4,ww,48.5,wI,31.9,wO,22.5,wo,41.4,MI,31.4,Sw,78.6,OV,42.6,Sf,14.0,SM,64.3,IF,11,LP,84,rB,2392,rb,10008,rJ,3,rA,41,BA,0,BF,0,gF,0,gW,0.0,gf,0.0,gt,0.0,FR,25.2,fR,4.7,MR,13.9,mR,13.2,SR,0,sR,1,FL,24.5,fL,4.5,ML,13.9,mL,13.2,SL,0,sL,1,Fr,27.6,fr,1.8,Mr,4.7,mr,4.4,Sr,2,sr,0,Fl,29.2,fl,1.9,Ml,4.7,ml,4.4,Sl,3,sl,0,FT,33.5,fT,20.8,MT,41.2,mT,39.4,ST,2,sT,1,aH,0.0,cH,0.0,GH,640.1,HH,-32.0,RH,555.2,XH,-62.6,JH,495.0,KH,-54.1,LH,0.0,QH,0.0,iH,0.0,jH,0.0,aR,0.0,cR,0.0,GR,275.6,HR,-15.0,RR,234.6,XR,-27.4,JR,210.0,KR,-18.5,LR,0.0,QR,0.0,iR,0.0,jR,0.0,aL,0.0,cL,0.0,GL,273.4,HL,-14.8,RL,235.0,XL,-26.3,JL,210.6,KL,-18.6,LL,0.0,QL,0.0,iL,0.0,jL,0.0,ar,0.0,cr,0.0,Gr,342.6,Hr,-16.7,Rr,296.6,Xr,-35.7,Jr,263.0,Kr,-37.7,Lr,0.0,Qr,0.0,ir,0.0,jr,0.0,al,0.0,cl,0.0,Gl,342.6,Hl,-16.7,Rl,297.9,Xl,-34.3,Jl,265.4,Kl,-33.9,Ll,0.0,Ql,0.0,il,0.0,jl,0.0,aF,0.0,cF,0.0,GF,547.2,HF,-29.3,RF,468.1,XF,-54.2,JF,419.3,KF,-36.9,LF,0.0,QF,0.0,iF,0.0,jF,0.0,pH,-6.4,pR,-6.7,pL,-6.4,pr,-6.9,pl,-6.6,pF,-6.6,CS,E3";
        String ss = "Da,07/03/2018,TI,09:31,GE,1,AG,22,Hm,180.0,Wk,112.3,FW,34.1,fW,38.3,mW,70.4,bW,3.6,MI,34.7,IF,13,rA,37,BMR,2292,MW,74.0,wW,51.7,ww,46.0,wI,29.9,wO,21.8,wo,42.2,FR,34.8,fR,7.0,mR,12.5,FL,34.3,fL,6.9,mL,12.6,Fr,29.0,fr,2.0,mr,4.5,Fl,32.3,fl,2.2,ml,4.3,FT,34.6,fT,20.2,mT,36.5";
        String sss = "Da,17/04/2018,TI,09:31,GE,1,AG,22,Hm,180.0,Wk,109.2,FW,34.1,fW,38.3,mW,70.4,bW,3.6,MI,34.7,IF,13,rA,37,BMR,2292,MW,74.0,wW,51.7,ww,46.0,wI,29.9,wO,21.8,wo,42.2,FR,34.8,fR,7.0,mR,12.5,FL,34.3,fL,6.9,mL,12.6,Fr,29.0,fr,2.0,mr,4.5,Fl,32.3,fl,2.2,ml,4.3,FT,34.6,fT,20.2,mT,36.5";
        String ssss = "Da,25/05/2018,TI,09:31,GE,1,AG,22,Hm,180.0,Wk,110.0,FW,34.1,fW,38.3,mW,70.4,bW,3.6,MI,34.7,IF,13,rA,37,BMR,2292,MW,74.0,wW,51.7,ww,46.0,wI,29.9,wO,21.8,wo,42.2,FR,34.8,fR,7.0,mR,12.5,FL,34.3,fL,6.9,mL,12.6,Fr,29.0,fr,2.0,mr,4.5,Fl,32.3,fl,2.2,ml,4.3,FT,34.6,fT,20.2,mT,36.5";
        String sssss = "Da,15/06/2018,TI,09:31,GE,1,AG,22,Hm,180.0,Wk,106.6,FW,34.1,fW,38.3,mW,70.4,bW,3.6,MI,34.7,IF,13,rA,37,BMR,2292,MW,74.0,wW,51.7,ww,46.0,wI,29.9,wO,21.8,wo,42.2,FR,34.8,fR,7.0,mR,12.5,FL,34.3,fL,6.9,mL,12.6,Fr,29.0,fr,2.0,mr,4.5,Fl,32.3,fl,2.2,ml,4.3,FT,34.6,fT,20.2,mT,36.5";

        Measurement measurement = this.measurementBuilder.buildMeasurement(s);
        Measurement m = this.measurementBuilder.buildMeasurement(ss);
        Measurement mm = this.measurementBuilder.buildMeasurement(sss);
        Measurement mmm = this.measurementBuilder.buildMeasurement(ssss);
        Measurement mmmm = this.measurementBuilder.buildMeasurement(sssss);

        measurement.setUser(userRepository.findByUsername("Attegates"));
        m.setUser(userRepository.findByUsername("Attegates"));
        mm.setUser(userRepository.findByUsername("Attegates"));
        mmm.setUser(userRepository.findByUsername("Attegates"));
        mmmm.setUser(userRepository.findByUsername("Atte Gates"));

        measurementRepository.save(measurement);
        measurementRepository.save(m);
        measurementRepository.save(mm);
        measurementRepository.save(mmm);
        measurementRepository.save(mmmm);
        */
        //SpringApplication.exit(context);
    }
}
