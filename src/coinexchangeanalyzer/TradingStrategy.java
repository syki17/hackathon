/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coinexchangeanalyzer;

/**
 *
 * @author Oleg
 */
abstract class TradingStrategy {
    
    public double evaluate(double[] data)
    {
    return 1;
    }
    
    public String getName()
    {
    return "";
    }
    
}
