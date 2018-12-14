package mathsnake;

import java.util.Random;

public class ConstructorThreadSnakeBoard extends ConstructorThread{
    private int counterPowerUps = 0;  //it's used to create power ups every x blocks creation
    private int counterCoins = 0; //it's used to create coins  every y blocks creation
    
    public ConstructorThreadSnakeBoard(Board board) {
        super(board);
    }
    
    @Override
    protected void createBlocks(){
        int y=5;
        boolean dea = false;
        boolean positive = false;

        for(int i=0; i<7; i++){
            Random random = new Random();
            int casuale = random.nextInt(99);
            int gameBest = board.getGameBest();


            if(gameBest <= 299){ //primo livello di difficoltà
                if (casuale >= 0 && casuale <= 29){ //30% BLOCCO VUOTO 
                    positive = true;
                }

                if (casuale >= 30 && casuale <= 59){ //30% BLOCCO ADD
                    int choise = random.nextInt(99);
                    if (choise >= 0 && choise <= 79){ //80%
                        elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                    } 
                    if (choise >= 80){ //20%
                        elementManager.addElement(new Block(20,Operation.ADD,y+(70*i),-100));
                    }
                    positive = true;
                }

                if (casuale >= 60 && casuale <= 79){ //20% BLOCCO SUB
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                    int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 79){ //80%
                            elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                        } 
                        if (choise >= 80){ //20%
                            elementManager.addElement(new Block(20,Operation.SUB,y+(70*i),-100));
                        }
                    }
                }

                if (casuale >= 80 && casuale <= 99){ //20% BLOCCO DIV
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                    }
                }
            }



            if(gameBest >= 299 && gameBest <= 599){ //secondo livello di difficoltà
                if (casuale >= 0 && casuale <= 24){ //25% BLOCCO VUOTO 
                    positive = true;
                }

                if (casuale >= 25 && casuale <= 44){ //20% BLOCCO ADD
                    int choise = random.nextInt(99);
                    if (choise >= 0 && choise <= 79){ //80%
                        elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                    } 
                    if (choise >= 80){ //20%
                        elementManager.addElement(new Block(20,Operation.ADD,y+(70*i),-100));
                    }
                    positive = true;
                }

                if (casuale >= 45 && casuale <= 64){ //20% BLOCCO SUB
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 79){ //80%
                            elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                        } 
                        if (choise >= 80){ //20%
                            elementManager.addElement(new Block(20,Operation.SUB,y+(70*i),-100));
                        }
                    }
                }

                if (casuale >= 65 && casuale <= 89){ //25% BLOCCO DIV
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                    }
                }

                if (casuale >= 90 && casuale <= 99){ //10% BLOCCO DEA
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        if (dea == false){
                            elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                            dea = true;
                        } else{
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                                positive = true;
                            } else{ //50%
                                elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                            }
                        }
                    }
                }
            }


            if(gameBest >= 600 && gameBest <= 899){ //terzo livello di difficoltà
                if (casuale >= 0 && casuale <= 19){ //20% BLOCCO VUOTO 
                    positive = true;
                }

                if (casuale >= 20 && casuale <= 39){ //20% BLOCCO ADD
                    int choise = random.nextInt(99);
                    if (choise >= 0 && choise <= 69){ //70%
                        elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                    } 
                    if (choise >= 70){ //30%
                        elementManager.addElement(new Block(20,Operation.ADD,y+(70*i),-100));
                    }
                    positive = true;
                }

                if (casuale >= 40 && casuale <= 59){ //20% BLOCCO SUB
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 69){ //70%
                            elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                        } 
                        if (choise >= 70){ //30%
                            elementManager.addElement(new Block(20,Operation.SUB,y+(70*i),-100));
                        }
                    }
                }

                if (casuale >= 60 && casuale <= 84){ //25% BLOCCO DIV
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 69){ //70%
                            elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                        } 
                        if (choise >= 70){ //30%
                            elementManager.addElement(new Block(4,Operation.DIV,y+(70*i),-100));
                        }
                    }
                }

                if (casuale >= 85 && casuale <= 99){ //15% BLOCCO DEA
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        if (dea == false){
                            elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                            dea = true;
                        } else{
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                                positive = true;
                            } else{ //50%
                                elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                            }
                        }
                    }
                }
            }



            if(gameBest >= 900 && gameBest <= 1199){ //quarto livello di difficoltà
                if (casuale >= 0 && casuale <= 14){ // 15% BLOCCO VUOTO 
                    positive = true;
                }

                if (casuale >= 15 && casuale <= 29){ //15% BLOCCO ADD
                    int choise = random.nextInt(99);
                    if (choise >= 0 && choise <= 39){ //40%
                        elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                    } 
                    if (choise >= 40 && choise <= 89){ //50%
                        elementManager.addElement(new Block(20,Operation.ADD,y+(70*i),-100));
                    } 
                    if (choise >= 90){ //10%
                        elementManager.addElement(new Block(30,Operation.ADD,y+(70*i),-100));
                    }
                    positive = true;
                }

                if (casuale >= 30 && casuale <= 44){ //15% BLOCCO SUB
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 39){ //40%
                            elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                        } 
                        if (choise >= 40 && choise <= 89){ //50%
                            elementManager.addElement(new Block(20,Operation.SUB,y+(70*i),-100));
                        } 
                        if (choise >= 90){ //10%
                            elementManager.addElement(new Block(30,Operation.SUB,y+(70*i),-100));
                        }
                    }
                }

                if (casuale >= 45 && casuale <= 74){ //30% BLOCCO DIV
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 29){ //30%
                            elementManager.addElement(new Block(4,Operation.DIV,y+(70*i),-100));
                        } 
                        if (choise >= 30 && choise <= 94){ //65%
                            elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                        } 
                        if (choise >= 95){ //5%
                            elementManager.addElement(new Block(6,Operation.DIV,y+(70*i),-100));
                        }
                    }
                }

                if (casuale >= 75 && casuale <= 99){ //25% BLOCCO DEA
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        if (dea == false){
                            elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                            dea = true;
                        } else{
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                                positive = true;
                            } else{ //50%
                                elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                            }
                        }
                    }
                }
            }



            if(gameBest >= 1200){ //quinto livello di difficoltà
                if (casuale >= 0 && casuale <= 9){ // 10% BLOCCO VUOTO 
                    positive = true;
                }

                if (casuale >= 10 && casuale <= 24){ //15% BLOCCO ADD
                    int choise = random.nextInt(99);
                    if (choise >= 0 && choise <= 29){ //30%
                        elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                    } 
                    if (choise >= 30 && choise <= 74){ //45%
                        elementManager.addElement(new Block(20,Operation.ADD,y+(70*i),-100));
                    } 
                    if (choise >= 75){ //25%
                        elementManager.addElement(new Block(30,Operation.ADD,y+(70*i),-100));
                    }
                    positive = true;
                }

                if (casuale >= 25 && casuale <= 39){ //15% BLOCCO SUB
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 29){ //30%
                            elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                        } 
                        if (choise >= 30 && choise <= 74){ //45%
                            elementManager.addElement(new Block(20,Operation.SUB,y+(70*i),-100));
                        } 
                        if (choise >= 75){ //25%
                            elementManager.addElement(new Block(30,Operation.SUB,y+(70*i),-100));
                        }
                    }
                }

                if (casuale >= 40 && casuale <= 69){ //30% BLOCCO DIV
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 39){ //40%
                            elementManager.addElement(new Block(4,Operation.DIV,y+(70*i),-100));
                        } 
                        if (choise >= 40 && choise <= 79){ //40%
                            elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                        }
                        if (choise >= 80 && choise <= 94){ //15%
                            elementManager.addElement(new Block(6,Operation.DIV,y+(70*i),-100));
                        } 
                        if (choise >= 95){ //5%
                            elementManager.addElement(new Block(8,Operation.DIV,y+(70*i),-100));
                        }
                    }
                }

                if (casuale >= 70 && casuale <= 99){ //30% BLOCCO DEA
                    if (i==6 && positive == false){
                        int choise = random.nextInt(1);
                        if(choise == 1){ //50%
                            elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                        } else{ //50%
                        }
                    } else{
                        elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                    }
                }
            }
        }
    }
    
    @Override
    protected void createPowerUps(){
        if(counterPowerUps == 4){   //create power ups every four block creations
        int rand = (int)(Math.random()*10);
        if(rand >= 6){ //powerups is created with probability of 40%
        }
        else{
            if(rand < 3){  //probability of 30%
                this.elementManager.addElement(new PowerUpsSpeedUp(this.randomXPU(), -240));
            }
            if(rand < 5 && rand >= 3){  //probability of 20%
                this.elementManager.addElement(new PowerUpsShield(this.randomXPU(), -240));
            }
            if(rand == 5){ //probability of 10% 
                this.elementManager.addElement(new PowerUpsIncreaseLife(this.randomXPU(), -240));
            }
        }
        counterPowerUps = 0;
        }
        else
            counterPowerUps ++;
    }
    
    @Override
    protected void createCoins(){
        if(counterCoins == 3){
            int rand = (int)(Math.random()*10);
            if(rand > 4){  //create coins with probability of 50%
                this.elementManager.addElement(new Coin(this.randomXC(), -280));
            }
            else{
            }
            counterCoins = 0;
        }
        else{
            counterCoins ++;
        }
    }
    
    private int randomXPU(){
        return (int)(Math.random()*(Environment.getInstance().JP_WIDTH -Environment.getInstance().POWERUPS_WIDTH - 10));
    }
    
    private int randomXC(){
        return (int)(Math.random()*(Environment.getInstance().JP_WIDTH -Environment.getInstance().COIN_WIDTH - 10));
    }
}
