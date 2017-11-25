package niki;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

/**
 *
 * @author Dhruv
 */
public class Niki
{
    HashMap<Long, BigInteger> fibdp;
    public Niki()
    {
        fibdp=new HashMap<>();
        fibdp.put(new Long(0), new BigInteger("0"));
        fibdp.put(new Long(1), new BigInteger("1"));
        fibdp.put(new Long(2), new BigInteger("1"));
    }
    
    public static void main(String args[]) throws IOException
    {
        String pathIn = "input.txt", pathOut="output.txt";
        if(args.length!=0)
        {
            pathIn=args[0];
            if(args.length>=2)
                pathOut=args[1];
        }        
        
        BufferedReader input = new BufferedReader(new FileReader(pathIn));
        FileWriter output = new FileWriter(pathOut);
        
        int t=Integer.parseInt(input.readLine());
        Niki obj=new Niki();
        for(int i=0; i<t; i++)
        {
            long n=Long.parseLong(input.readLine());
            output.write(obj.noConZero(n)+System.lineSeparator());
        }
        
        input.close();
        output.close();
    }
    
    public String noConZero(long n)
    {
        if(n<1)
            return "-1";
        final long modulo = Math.round(Math.pow(10, 9)+7);
        final BigInteger moder = new BigInteger(Long.toString(modulo));
        BigInteger mod=fib(n+2).mod(moder);
        return mod.toString();
    }
    
    public BigInteger fib(long n)
    {
        if(fibdp.containsKey(n))
            return fibdp.get(n);
        
        BigInteger calculated;
        if((n & 1) == 1)
        {
            /*
                If n is odd:
                k = (n + 1)/2
                F(n) = F(k)*F(k) + F(k-1)*F(k-1)
            */
            long k=(n+1)/2;
            BigInteger f_k=fib(k);
            BigInteger f_k1=fib(k-1);
            calculated=f_k.pow(2).add(f_k1.pow(2));
        }
        else
        {
            /*
                If n is odd:
                k = n/2
                F(n) = [2*F(k-1) + F(k)]*F(k)
            */
            long k=n/2;
            BigInteger f_k=fib(k);
            BigInteger f_k1=fib(k-1);
            calculated=f_k1.add(f_k1).add(f_k);
            calculated=calculated.multiply(f_k);
        }
        fibdp.put(n, calculated);
        return calculated;
    }
}
