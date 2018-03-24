/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testconnection;

import java.util.ArrayList;

/**
 *
 * @author Oleg
 */
public class TradingBasket {
    public ArrayList<TradingStrategy> tradingStrategy = new ArrayList<TradingStrategy>();

    private double lowerBoundBuy;
    private double upperBoundSell;
    
    int getTradingSignal(double[] data){
        double result=0;
        double tmp;
        for (TradingStrategy strategy: tradingStrategy) {
            tmp = strategy.evaluate(data);
            System.out.println("Strategy " + strategy.getName() + " generated signal " + tmp);
            result+= tmp;
        }
        
        if (result>this.lowerBoundBuy)
            {
                System.out.println("The final decision - buy position");
                return 1;
            }//buy
        else if (result<this.upperBoundSell)
            {
                System.out.println("The final decision - sell position");
                return -1;
            }
        else 
            {
                System.out.println("The final decision - stay neutral");
                return 0;
            }
    }
    
    public void addStrategy(TradingStrategy str)
    {
        tradingStrategy.add(str);
    }
    
    public void setBounds(double lowerBoundBuy, double upperBoundSell)
    {
        this.lowerBoundBuy=lowerBoundBuy;
        this.upperBoundSell = upperBoundSell;
    }
            
    
}
