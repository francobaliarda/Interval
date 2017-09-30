package interval;
import java.math.BigDecimal;

public class Interval {
	private double beginning;
	private double end;
	private double increment;
	
	public Interval(double beginning, double end, double increment) throws Exception{
		if(Double.compare(beginning,end)>0 || Double.compare(increment,0.0)==0)
			throw new Exception();
		this.beginning=beginning;
		this.end=end;
		this.increment=increment;
	}
	public Interval(double beginning,double end) throws Exception{
		this(beginning,end,1);
	}
	public long size(){
		return (long)((end-beginning)/increment + 1);
	}
	public double at(long position) throws Exception{
		if(position==0 || position>size())
			throw new Exception();
		return (beginning + ((position - 1)*increment));
	}
	public long indexOf(double value){
		if(Double.compare(beginning,value)>0 || Double.compare(end,value)<0)
			return 0;
		return (long)((value-beginning)/increment + 1);
	}
	public boolean includes(double value){
		//Use BigDecimal to avoid rounding errors.
		/**********************/
		BigDecimal auxValue = new BigDecimal(((Double)value).toString());
		BigDecimal auxBeginning = new BigDecimal(((Double)beginning).toString());
		BigDecimal auxIncrement = new BigDecimal(((Double)increment).toString());
		auxValue = auxValue.subtract(auxBeginning);
		BigDecimal modulus = auxValue.remainder(auxIncrement);
		/*********************/
		
		if(indexOf(value)==0 || modulus.doubleValue() != 0)
			return false;
		else
			return true;
	}
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		double value;
		for(value=beginning;Double.compare(end,value)>=0;value+=increment)
			str.append(value + "\n");
		return str.toString();
	}
	@Override
	public boolean equals(Object obj){
		if(this==obj)
			return true;
		else if(!(obj instanceof Interval))
			return false;
		Interval other = (Interval)obj;
		return (Double.compare(beginning,other.beginning)==0
				&& Double.compare(end, other.end)==0
				&& Double.compare(increment,other.increment)==0);
	}
	@Override
	public int hashCode(){
		int hash=17;
		long begHash = Double.doubleToLongBits(beginning);
		long endHash = Double.doubleToLongBits(end);
		long incHash = Double.doubleToLongBits(increment);
		hash = hash * 31 + (int)(begHash ^ (begHash >>> 32));
		hash = hash * 31 + (int)(endHash ^ (endHash >>> 32));
		hash = hash * 31 + (int)(incHash ^ (incHash >>> 32));
		return hash;
	}
	
}
