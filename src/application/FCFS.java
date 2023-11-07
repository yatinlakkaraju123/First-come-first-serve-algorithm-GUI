package application;


public class FCFS {
	static int n;
	static float[][] input_table;
	FCFS(int n1,float[][] input_table1)
	{
		n = n1;
		input_table = input_table1;
	}
	  public  float[][] fcfs()
	  {
	  
	       sort(input_table,n);
	      float[][] ga = gantt(input_table,n);
	      // sorts according to arrival time
	     return ga;
	     
	  
	  
	  }
	  public static void sort(float[][] a,int n)
	  {
	    
	    
	    
	    for(int i=1;i<=n-1;i++)
	    {
	      for(int j=i+1;j<=n;j++)
	      {
	        if(a[i][3]>a[j][3])
	        {
	          float temp3 = a[i][3];
	          a[i][3] = a[j][3];
	          a[j][3] = temp3;
	          float temp2 = a[i][2];
	           a[i][2] = a[j][2];
	          a[j][2] = temp2;
	          float temp1 = a[i][1];
	           a[i][1] = a[j][1];
	          a[j][1] = temp1;
	          
	          
	        }
	      }
	    }
	  }
	 public static float[][] gantt(float[][] a,int n)
	 {
	    float[][] g = new float[20][20];
	    // first add the one which arrives first
	   g[1][1] = a[1][1];// g[][1] represents which process
	   g[1][2] = a[1][3];// g[][2] represents waiting time
	   g[1][3] = a[1][2];// g[][3] represents burst time
	   g[1][4] = a[1][3] + a[1][2] ; // g[][4] represents turnaround time
	    for(int i=2;i<=n;i++)
	    { // if burst time of previous process is greater than arrival time of current process then current waiting time is previous burst - current arrival
	      if(g[i-1][3]>a[i][3])
	      {
	        g[i][1] = a[i][1];
	        g[i][2] = g[i-1][3] - a[i][3];
	        g[i][3] = g[i-1][3] + a[i][2];
	        g[i][4] = g[i][2] + a[i][2];
	      }
	      
	    }
	    return g;
	 }
}
