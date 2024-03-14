
package components;
import java.util.Random;

public class Personaje {
    public String id;
    public String name;
    public int qAmount;
    public int level;
    public int valueSkills;
    public int valueStamina;
    public int valueStrengh;
    public int valueAgility;
    public boolean skills, stamina, strength, agility;
    public String company; 
    public String characterName;


    public String[] personajesAv = {"Aang","Katara","Sokka","Zuko","Iroh","Toph Beifong", "Momo","Appa","Azula", "Ty Lee", "Mai", "Ozai", "Zhao","Jeong", "Piandao", "Bumi","Hama","Pakku","La Princesa Yue","Hahn", "Jet","Long Feng", "Dai Li","Koh el Ladrón de Rostros","Wan Shi Tong", "Suki","Tyro", "Roku","Kyoshi","Kuruk","Yangchen","Jianzhu","Ukano","Amon" ,"Tarrlok"};
    public String[] personajesUS = {"Mordecai","Rigby","Benson","Skips","Papaleta","Fantasmano", "Musculoso","Margaret","Eileen", "Thomas", "Cj", "Don", "Gene","Señor Maellard", "Skips (Walks)", "Muerte","Thomas","Rigby (Niño)","Mordecai (Niño)","Benson (Niño)", "Skips (Niño)","Fantasmano (Niño)", "Musculoso (Niño)","Maellard (Niño)","El Intendente", "Starla", "Frankie", "Eileen (Niña)", "Margaret (Niña)", "CJ (Niña)", "Hector", "Mitch Soreinstein", "Gary", "Benson(Fantasma)", "El Guardian" };

    
    private Personaje next; 
    public int counter; 
    public int roundsCounter; 
    
    public Personaje(String company, int counter){
        this.skills = (defineQuality(60));
        this.stamina = (defineQuality(70));
        this.strength = (defineQuality(50));
        this.agility = (defineQuality(40));
        
        this.company = company;
        this.qAmount = 0; 
        this.counter = counter;
        this.name = "";
        
        this.id = defineId(company, counter);
        
        this.level = defineLevel(skills, stamina, strength, agility);
        
        this.roundsCounter = 0;
    }
    
    public boolean defineQuality(int percentage){
        Random r = new Random(); 
        int rInt = r.nextInt(100);
        return (rInt < percentage);
    }
    
    public int defineLevel(boolean skills, boolean stamina, boolean strength, boolean agility){
        
        if (skills){qAmount+=5;}
        if(skills) {valueSkills += 5;}
        if (stamina){qAmount+=4;}
        if(stamina) {valueStamina += 4;}
        if (strength){qAmount+=3;}
        if(strength) {valueStrengh += 3;}
        if (agility){qAmount+=3;}
        if(agility) {valueAgility += 3;}
        
        if (qAmount >= 11){return 1;}
        if (qAmount >=5  && qAmount < 10){return 2;}
        return 3;
    }
    
    public String defineId(String company, int counter){
        String id;
        String[] listToRun;
        String name = null;
        if(company.equals("Nick")){
            id = "AV";
            listToRun = this.personajesAv;
            this.name = listToRun[counter-1];
        }else{
            id = "US";
            listToRun = this.personajesUS;
            this.name = listToRun[counter-1];
        }
        
        String num = Integer.toString(counter);
        
        
        if (num.length()==1){num=("0"+num);}
        id += num;
        
        
        
        
        return id;
        
        
    }
    
    public Personaje getNext(){
        return next;
    }
    
    public void setNext(Personaje next){
        this.next = next;
    }
}
