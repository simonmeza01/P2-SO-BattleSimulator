
package components;

import static java.lang.Math.abs;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AI extends Thread {
    public Semaphore mutex; 
    public Personaje pAV;
    public Personaje pUS;
    public Admin admin;
    public int winsAV;
    public int winsUS;
    
    public AI() {
        this.mutex = Main.mutex;
        this.admin = Main.admin;
        this.winsAV = 0;
        this.winsUS = 0;
        
    }

    
    public int resultado(){
        // 1 para Winner
        // 2 para Empate
        // 3 para Refuerzo

        
        int probsResultado = random();
        if (probsResultado <= 40){
            return 1;
        } else if (probsResultado>40 && probsResultado<=67){
            return 2;
        } else {
            return 3;
        }
    }
    
    public Personaje ganador(Personaje AV, Personaje US){
        //Si los qAmount son iguales se define por un random
        if ( AV.qAmount == US.qAmount ){
            if (random()<50){
                return AV;
            }
            return US;
        }
        
        Personaje higherQ;
        Personaje lowerQ;
        //Si el personaje de Avatar tiene mayor qAmount entonces el mayor es Avatar y viceversa
        if (AV.qAmount>US.qAmount){
            higherQ = AV;
            lowerQ = US;
        }else{
            higherQ = US;
            lowerQ = AV;
        }
        
        int diferencia = abs(AV.qAmount-US.qAmount);
        
        if (diferencia > 0 && diferencia <=2){
            if (random()<=60){
                return higherQ; 
            } else {
                return lowerQ;
            }
        } else if (diferencia > 2 && diferencia <=6 ){
            if (random()<=70){
                return higherQ; 
            } else {
                return lowerQ;
            }
        }else if (diferencia > 6 && diferencia <=10 ){
            if (random()<=80){
                return higherQ; 
            } else {
                return lowerQ;
            }
        } else { 
            if (random()<=90){
                return higherQ; 
            } else {
                return lowerQ;
            }
        }
    }
    
    public int random(){
        Random random = new Random();
        int randomInt = random.nextInt(100);
        return randomInt;
    }
    
    
    @Override
    public void run(){
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true){
            try { 
                this.mutex.acquire();
                
                
                System.out.println("\n Ronda: " + Integer.toString(this.admin.roundsN) + "\nAI -- Selected:   " + pAV.id + " "+ pAV.name + "nivel:"+ pAV.level+ pAV.qAmount+"  y  " + pUS.id+ " "+ pUS.name+"nivel:"+ pUS.level + pUS.qAmount);
                
                int resultado = resultado();
                String resultadoStr;
                sleep(Main.duration);
                Main.interfaz.state("");
                switch (resultado) {
                    case 1:
                        resultadoStr = "Ganador: ";
                        Main.interfaz.printResultado(resultadoStr);
                        Personaje winner = ganador(pAV, pUS);
                        if (winner==pAV){
                            this.winsAV++;
                            Main.interfaz.updateWins(true, Integer.toString(this.winsAV));
                        }else{
                            this.winsUS++;
                            Main.interfaz.updateWins(false, Integer.toString(this.winsUS));
                        }
                        System.out.println("\tGanador: " + winner.id); 
                        Main.interfaz.printWinner(winner);
                        break;
                    case 2:
                        resultadoStr = "Empate";
                        Main.interfaz.printResultado(resultadoStr);
                        System.out.println("Empate ");
                        pAV.level = pUS.level = 1;
                        admin.queuePersonaje(pAV, admin.qAV1, admin.qAV2, admin.qAV3);
                        admin.queuePersonaje(pUS, admin.qUS1, admin.qUS2, admin.qUS3);
                        break;
                    
                    case 3: 
                        resultadoStr = "Suspendida";
                        Main.interfaz.printResultado(resultadoStr);
                        System.out.println("Refuerzo ");
                        admin.sendRefuerzo(pAV, admin.qAV4);
                        admin.sendRefuerzo(pUS, admin.qUS4);
                        break;
                }
                Main.interfaz.updateQueues(admin.qAV1,admin.qAV2,admin.qAV3,admin.qAV4,admin.qUS1,admin.qUS2,admin.qUS3,admin.qUS4);
                sleep(Main.duration/2);
                Main.interfaz.cleanFields();
                this.mutex.release();
                sleep(1000);
                
                
            }catch (InterruptedException ex) {      
                Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
