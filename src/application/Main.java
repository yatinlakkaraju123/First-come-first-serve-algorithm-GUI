package application;
	


import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	static int no_of_processes;
	static float[][] input_table = new float[20][20];
	Label[] label_WT = new Label[20];
    Label[] WT = new Label[20];
    Label[] label_TAT = new Label[20];
    Label[] TAT = new Label[20];
    static float avg_WT=0;
    static float avg_TAT=0;
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			root.setMinSize(500, 500); 
		       
		      //Setting the padding  
			root.setPadding(new Insets(10, 10, 10, 10)); 
		      
		      //Setting the vertical and horizontal gaps between the columns 
			root.setVgap(5); 
			root.setHgap(5);       
		      
		      //Setting the Grid alignment 
			root.setAlignment(Pos.CENTER); 
			Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// nodes
			TextField n = new TextField();
			Label label_n = new Label("Enter no of processses:");
			Label[] burst_inputs_labels = new Label[20];
			TextField[] burst_inputs = new TextField[20];
			Label[] arrival_inputs_labels = new Label[20];
			TextField[] arrival_inputs = new TextField[20];
			Button[] submit_array = new Button[20];
			Button btn = new Button("Submit");
			
			// events
			  EventHandler<ActionEvent> show_gantt= new EventHandler<ActionEvent>() {
		        	public void handle(ActionEvent e)
		        	{
		        		for(int i=1;i<=no_of_processes;i++)
		        		{
		        			 final int processIndex = i;
		        			  float f = Float.parseFloat(burst_inputs[processIndex].getText());
               			   float g = Float.parseFloat(arrival_inputs[processIndex].getText());
               			   input_table[processIndex][2] = f;
               	            input_table[processIndex][3] = g;
		        		
		        		}
		        		FCFS f = new FCFS(no_of_processes,input_table);
				        float[][] ga = f.fcfs();
				        
				        for(int i=1;i<=no_of_processes;i++)
				        {
				        	label_WT[i] = new Label();
				        	WT[i] = new Label();
				        	label_TAT[i] = new Label();
				        	TAT[i] = new Label();
				        	label_WT[i].setText("Waiting time of process P"+i);
				        	WT[i].setText(Float.toString(ga[i][2]));
				        	avg_WT += ga[i][2];
				        	label_TAT[i].setText("Turn around time of process P"+i);
				        	TAT[i].setText(Float.toString(ga[i][4]));
				        	avg_TAT += ga[i][4];
				        	
				        	
				        	
				        }
				        for(int i=1;i<=no_of_processes;i++)
				        {
				        	 final int processIndex = i;
				     		root.add(label_WT[processIndex], 0, 3*no_of_processes+2+2*processIndex);
				        	root.add(WT[processIndex], 1, 3*no_of_processes+2+2*processIndex);
				        	root.add(label_TAT[processIndex], 0, 3*no_of_processes+2+2*processIndex+1);
				        	root.add(TAT[processIndex], 1, 3*no_of_processes+2+2*processIndex+1);
				        	 
				        }
				        root.add(new Label("AVerage Waiting time"),0, 5*no_of_processes+4);
				        root.add(new Label(""+avg_WT/(float)no_of_processes), 1, 5*no_of_processes+4);
				        root.add(new Label("AVerage Turn Around time"),0, 5*no_of_processes+4+1);
				        root.add(new Label(""+avg_TAT/(float)no_of_processes), 1, 5*no_of_processes+4+1);
		        	}
		        };
			  EventHandler<ActionEvent> input_n = new EventHandler<ActionEvent>() {
		            public void handle(ActionEvent e)
		            {
		                no_of_processes =Integer.parseInt(n.getText());
		                root.add(new Label("Enter Processes Details"), 0,2);
		                for(int i=1;i<=no_of_processes;i++)
		                {
		                	  input_table[i][1] = i;
		                	  burst_inputs_labels[i] = new Label();
		                	  burst_inputs[i] = new TextField();
		                	  arrival_inputs_labels[i] = new Label();
		                	  arrival_inputs[i] = new TextField();
		                	  submit_array[i] = new Button("submit");
		                	  burst_inputs_labels[i].setText("Enter the burst time of P"+i);
		                	  arrival_inputs_labels[i].setText("Enter the arrival time of P"+i);
		                	  final int processIndex = i;
		                	  EventHandler<ActionEvent> eventi = new EventHandler<ActionEvent>() {
		                		  public void handle(ActionEvent e)
		                		  {
		                			  float f = Float.parseFloat(burst_inputs[processIndex].getText());
		                			   float g = Float.parseFloat(arrival_inputs[processIndex].getText());
		                			   input_table[processIndex][2] = f;
		                	            input_table[processIndex][3] = g;
		                		  }
		                	  };
		                	  submit_array[i].setOnAction(eventi);
		                	 
		                	  root.add(burst_inputs_labels[i], 0,3*i);
		                	  root.add(burst_inputs[i], 1,3*i);
		                	  root.add(arrival_inputs_labels[i], 0,3*i+1 );
		                	  root.add(arrival_inputs[i], 1,3*i+1 );
		                	//  root.add(submit_array[i],0, 3*i+2);
		                	  
		          	       
		                }
		             
		                
				        Button btn2 = new Button("Display");
				        root.add(btn2, 0, 3*no_of_processes+3);
				        btn2.setOnAction(show_gantt);
		            
		            }
		        };
		        btn.setOnAction(input_n);
		        root.add(label_n,0,0); // column = 0 and row = 0
		        root.add(n,1,0);
		        root.add(btn,0,1);
		       
	        	
		     
		        
		      
		        
		        
			primaryStage.setScene(scene);
			primaryStage.setTitle("First Come First Serve BY Lakkaraju Yatin");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
