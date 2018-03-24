/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testconnection;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;
import com.tictactec.ta.lib.MAType;

/**
 *
 * @author Oleg
 */
public class Trading_SMA  extends TradingStrategy{
    
    private int numberOfPeriods; // model param - number of analized periods
    private double prctDivThreshhold; // model param - if cur price > sma * prctDivThreshhold, if  price < sma * prctDivThreshhold 
    
    public Trading_SMA(int numberOfPeriods, double  prctDivThreshhold)
    {
        this.numberOfPeriods = numberOfPeriods;
        this.prctDivThreshhold = prctDivThreshhold;               
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

                retCode=c.movingAverage(0,closingPrice.length-1,closingPrice,this.numberOfPeriods, MAType.Sma,begin,length,out);  

   /*     System.out.println(out.length);        
        System.out.println("Output Begin:" + begin.value);
        System.out.println("Output End:" + length.value);
            
            for (int i = 0;i<out.length;i++)
                System.out.println(out[i]);
        
            for (int i = begin.value; i <= length.value+begin.value-1; i++) {
                StringBuilder line = new StringBuilder();
                line.append("Period #");
                line.append(i);
                line.append(" close= ");
                line.append(closingPrice[i]);
                line.append(" SMA=");
                line.append(out[i-begin.value]);
                System.out.println(line.toString());
            }
     */           
                if (retCode == RetCode.Success)
                {
                    
                    
                  statistics = out[length.value-1];//out[length.value - begin.value];
                  
                  System.out.println("Price:" + closingPrice[closingPrice.length-1] + " , statistics" + statistics);
                  
                  if (closingPrice[closingPrice.length-1] > statistics   * (1.+this.prctDivThreshhold))
                  {return 1.0;}
                  else if (closingPrice[closingPrice.length-1] < statistics / (1.+this.prctDivThreshhold))
                  {return -1.0;}
                  else
                  {return 0;}
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

                retCode=c.movingAverage(0,closingPrice.length-1,closingPrice,this.numberOfPeriods, MAType.Sma,begin,length,out);  

   
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
                      
                      if (currentPrice > statistics   * (1.+this.prctDivThreshhold))
                            {
                                currentSignal = 1.0;
                            }
                            else if (currentPrice < statistics / (1.+this.prctDivThreshhold))
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
        return "SMA(" + this.numberOfPeriods + ", " + this.prctDivThreshhold  + ")";
    }      
    
}
