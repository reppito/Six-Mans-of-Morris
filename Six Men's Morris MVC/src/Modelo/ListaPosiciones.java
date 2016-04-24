package Modelo;

public class ListaPosiciones {
     static int a[]= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
     private String pos[]={"q1","q2","q3","q4","q5","q6","q7","q8","q9","q10"
    		                ,"q11","q12","q13","q14","q15","q16"};
     


     public String toStringInt(){
    	 String s="[";
    	 for (int i=0;i<a.length;i++)
    		 if(a.length-i>1)
    			 s+=a[i]+",";
    		 else
    			 s+=a[i]+"";

    	 s+="]";
    	 return s;
     }
     public int getElementInt(int i)
     {
    	 
    	 return a[i];
     }
     public String toStrinString(){
    	 String s="[";
    	 for (int i=0;i<pos.length;i++)
    		 if(pos.length-i>1)
    			 s+="'"+pos[i]+"',";
    		 else
    			 s+="'"+pos[i]+"'";
    	 s+="]";
    	 return s;
     }
     public String getElementString(int i)
     {
    	 
    	 return pos[i];
     }
     public int getSizeInt(){
    	 return a.length;
     }
	 public void setInt(int i,int b)
     {
         a[i]=b;
     }
    public int getInt(int i)
    {
        return a[i];
    }
    public String [] ReturnListString()
    {
        return pos;
    }
    public int[] ReturnListInt()
    {
        return a;
    }
}
