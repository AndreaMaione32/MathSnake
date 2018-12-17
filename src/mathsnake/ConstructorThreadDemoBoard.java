package mathsnake;

public class ConstructorThreadDemoBoard extends ConstructorThread{

    private int blockRow;
    
    public ConstructorThreadDemoBoard(Board board, Object pauseLock) {
        super(board, pauseLock);
        this.blockRow = 0;
    }

    @Override
    protected void createBlocks() {
        int y=5;
        
        for(int i=0; i<7; i++){            

            if(blockRow == 0){
                if(i==0){

                }

                if(i==1){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==5){

                }

                if(i==6){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

            }

            if(blockRow == 1){
                if(i==0){

                }

                if(i==1){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==5){

                }

                if(i==6){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

            }

            if(blockRow == 2){
                if(i==0){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==1){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==4){

                }

                if(i==5){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==6){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

            }

            if(blockRow == 3){
                if(i==0){
                    elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                }

                if(i==1){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==5){

                }

                if(i==6){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

            }

            if(blockRow == 4){
                if(i==0){

                }

                if(i==1){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==5){

                }

                if(i==6){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

            }

            if(blockRow == 5){
                if(i==0){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==1){

                }

                if(i==2){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==5){

                }

                if(i==6){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

            }

            if(blockRow == 6){
                if(i==0){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==1){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==5){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==6){

                }

            }

            if(blockRow == 7){
                if(i==0){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==1){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==5){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==6){

                }

            }

            if(blockRow == 8){
                if(i==0){

                }

                if(i==1){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==5){

                }

                if(i==6){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

            }

            if(blockRow == 9){
                if(i==0){

                }

                if(i==1){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==3){

                }

                if(i==4){

                }

                if(i==5){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==6){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

            }

            if(blockRow == 10){
                if(i==0){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==1){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==2){

                }

                if(i==3){

                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==5){

                }

                if(i==6){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

            }

            if(blockRow == 11){
                if(i==0){

                }

                if(i==1){

                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==5){

                }

                if(i==6){

                }

            }

            if(blockRow == 12){
                if(i==0){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==1){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==3){

                }

                if(i==4){

                }

                if(i==5){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==6){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

            }

            if(blockRow == 13){
                if(i==0){

                }

                if(i==1){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==5){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==6){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

            }

            if(blockRow == 14){
                if(i==0){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==1){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                }

                if(i==5){
                    
                }

                if(i==6){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

            }
            
            if(blockRow == 15){
                if(i==0){
                    
                }

                if(i==1){
                    
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==5){
                    
                }

                if(i==6){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

            }
            
            if(blockRow == 16){
                if(i==0){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==1){
                    
                }

                if(i==2){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==5){
                    
                }

                if(i==6){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

            }
            
            if(blockRow == 17){
                if(i==0){
                    
                }

                if(i==1){
                    
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==5){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==6){
                    
                }

            }
            
            if(blockRow == 18){
                if(i==0){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==1){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==2){
                    
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==4){
                    
                }

                if(i==5){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==6){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

            }
            
            if(blockRow == 19){
                if(i==0){
                    elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                }

                if(i==1){
                    elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                }

                if(i==5){
                    elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                }

                if(i==6){
                    
                }

            }
            
            if(blockRow == 20){
                if(i==0){
                    
                }

                if(i==1){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                }

                if(i==4){
                    
                }

                if(i==5){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==6){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

            }
            
            if(blockRow == 21){
                if(i==0){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==1){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

                if(i==4){
                    
                }

                if(i==5){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==6){
                    elementManager.addElement(new Block(10,Operation.ADD,y+(70*i),-100));
                }

            }
            
            if(blockRow == 22){
                if(i==0){
                    
                }

                if(i==1){
                    
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==5){
                    
                }

                if(i==6){
                    
                }

            }
            
            if(blockRow == 23){
                if(i==0){
                    
                }

                if(i==1){
                    elementManager.addElement(new Block(0,Operation.DEA,y+(70*i),-100));
                }

                if(i==2){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==3){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

                if(i==4){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==5){
                    elementManager.addElement(new Block(10,Operation.SUB,y+(70*i),-100));
                }

                if(i==6){
                    elementManager.addElement(new Block(2,Operation.DIV,y+(70*i),-100));
                }

            }
        }
        blockRow++;
    }

    @Override
    protected void createPowerUps() {
        if(blockRow == 12)
            this.elementManager.addElement(new PowerUpsShield((int)board.snake.getX()[Environment.getInstance().DOT_NUM-1], -240));
        if(blockRow == 16)
            this.elementManager.addElement(new PowerUpsSpeedUp((int)board.snake.getX()[Environment.getInstance().DOT_NUM-1], -240));
        
    }

    @Override
    protected void createCoins() {
        if(blockRow == 6)
            this.elementManager.addElement(new Coin((int)board.snake.getX()[Environment.getInstance().DOT_NUM-1], -280));
        if(blockRow == 9)
            this.elementManager.addElement(new Coin((int)board.snake.getX()[Environment.getInstance().DOT_NUM-1], -280));
        if(blockRow == 16)
            this.elementManager.addElement(new Coin((int)board.snake.getX()[Environment.getInstance().DOT_NUM-1], -280));
        
    }
    
}
