/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coinexchangeanalyzer;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;




public class Trading_LinearRegression extends TradingStrategy {

private double angleToBuy; // trend cut-off value to buy
private double angleToSell; // trend cut-off value to sell
private int numberOfObservations; // number of points taking into account

    public Trading_LinearRegression(int numberOfObservations, double  angleToBuy ,double  angleToSell )
    {
        this.numberOfObservations = numberOfObservations;
        this.angleToBuy = angleToBuy;   
        this.angleToSell = angleToSell;     
    }    
    
    public double evaluate (double[] closingPrice) 
    {
        
        double statistics;
        
        try {
                Core c = new Core();
                RetCode retCode;
                double[] out = new double[closingPrice.length];

                MInteger begin = new MInteger();
                MInteger length = new MInteger();            

                retCode=c.linearRegAngle(0,closingPrice.length-1,closingPrice,this.numberOfObservations, begin,length,out);
                //c.movingAverage(0,closingPrice.length-1,closingPrice,this.numberOfPeriods, MAType.Ema,begin,length,out);  
      
        /*        System.out.println("Output Begin:" + begin.value);
                System.out.println("Output End:" + length.value);
            
            for (int i = 0;i<out.length;i++)
                System.out.println(out[i]);
        
            for (int i = begin.value; i <= length.value+begin.value-1; i++) {
                StringBuilder line = new StringBuilder();
                line.append("Period #");
                line.append(i);
                line.append(" close= ");
                line.append(closingPrice[i]);
                line.append(" LinearRegression angle=");
                line.append(out[i-begin.value]);
                System.out.println(line.toString());
            }
          */      
                if (retCode == RetCode.Success)
                {

                  statistics = out[length.value-1];//out[length.value - begin.value];
                
                  System.out.println("Price:" + closingPrice[closingPrice.length-1] + " , statistics" + statistics);
                  
                  if (statistics > this.angleToBuy)
                      return 1.0;
                  else if (statistics < this.angleToSell)
                      return -1.0;
                  else
                      return 0;
                }
             
             
            
        } catch (Throwable t) {
                  System.out.println("Error");
            return 0; //something went wrong - we dont issue any recomendations
        }
    
        return 0;
        
    } 
    
    
    public double backTest (double[] closingPrice) 
    {
        
        double statistics;
         System.out.println("Backtesting " + this.getName());       
        try {
                Core c = new Core();
                RetCode retCode;
                double[] out = new double[closingPrice.length];

                MInteger begin = new MInteger();
                MInteger length = new MInteger();            

                retCode=c.linearRegAngle(0,closingPrice.length-1,closingPrice,this.numberOfObservations, begin,length,out); 

   
                if (retCode == RetCode.Success)
                {
                    
                  double prevSignal=0;
                  double currentSignal;

                  double PnL = 0;
                  
                  for (int i =0; i<= length.value-1;i++)
                  {
                  //System.out.println(i);
                  double currentPrice = closingPrice[closingPrice.length-length.value+i];
                  double previousPrice = closingPrice[closingPrice.length-length.value+i-1];                      
                      
                      statistics = out[i];
                      PnL += prevSignal * (currentPrice - previousPrice);
                      
                      if (statistics > this.angleToBuy)
                            {
                                currentSignal = 1.0;
                            }
                            else if (statistics < this.angleToSell)
                            {
                                currentSignal = -1.0;
                            }
                                else
                            {
                                currentSignal = 0;
                            }
                     if (prevSignal != currentSignal)
                     {
                         System.out.println("Position change: " + currentSignal + " , price: " + currentPrice);
                     }
                      prevSignal = currentSignal;
                      
                  }

                  System.out.println("The strategy result is: " + PnL);
                  
                  return PnL;
                  
                }
                          
            
        } catch (Throwable t) {
                  System.out.println("Error");
            return 0; //something went wrong - we dont issue any recomendations
        }
    
        return 0;
        
    }
 
    
    
    public String getName()
    {
        return "LinearRegressionAngle(" + this.numberOfObservations + ", " + this.angleToBuy + ", " + this.angleToSell + ")";
    }
    
}
